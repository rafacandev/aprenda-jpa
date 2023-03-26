package aprenda.jpa.pessoa;

import aprenda.jpa.item.FabricaDeItem;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.util.ApagadorDeRepositorios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static aprenda.jpa.pessoa.ConstantesDePessoa.*;
import static aprenda.jpa.pessoa.FabricaDePessoa.novaPessoa;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PessoaRepositoryTest {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ApagadorDeRepositorios apagadorDeRepositorios;

    @BeforeEach
    void antesDeCadaTeste() {
        apagadorDeRepositorios.apagarTudo();
    }

    @Test
    void save_NovaPessoa_Entao_VerificarNoBanco() {
        Pessoa pessoa = novaPessoa();
        pessoaRepository.save(pessoa);
        pessoaRepository.findById(pessoa.getId())
                .ifPresentOrElse(VerificadorDePessoa::verificarPessoa, Assertions::fail);
    }

    @Test
    void save_NovaPessoaFaltandoNome_Entao_ExcecaoAoSalvar() {
        var pessoa = new Pessoa();
        pessoa.setEmail(PESSOA_EMAIL);
        var excecao = assertThrows(DataIntegrityViolationException.class, () -> pessoaRepository.save(pessoa));
        assertEquals("not-null property references a null or transient value : aprenda.jpa.pessoa.Pessoa.nome", excecao.getMessage());
    }

    @Test
    void save_NovaPessoaComEmailDuplicado_Entao_ExcecaoAoSalvar() {
        pessoaRepository.save(novaPessoa());
        var excecao = assertThrows(DataIntegrityViolationException.class, () -> pessoaRepository.save(novaPessoa()));
        assertTrue(excecao.getMessage().contains("EMAIL_UNICO"));
    }

    @Test
    void save_NovaPessoaComItems_Entao_VerificarNoBanco() {
        int QUANTIDADE_DE_ITEMS = 2;

        var items = IntStream
                .range(1, QUANTIDADE_DE_ITEMS)
                .mapToObj(i -> FabricaDeItem.novoItem())
                .map(item -> itemRepository.save(item))
                .collect(Collectors.toSet());

        Pessoa pessoa = novaPessoa();
        pessoa.setItems(items);
        pessoaRepository.save(pessoa);

        pessoaRepository.findById(pessoa.getId())
                .ifPresentOrElse(VerificadorDePessoa::verificarPessoaComItems, Assertions::fail);
    }

}
