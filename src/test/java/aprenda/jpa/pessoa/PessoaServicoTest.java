package aprenda.jpa.pessoa;

import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static aprenda.jpa.item.FabricaDeItem.novoItem;
import static aprenda.jpa.pessoa.FabricaDePessoa.novaPessoa;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PessoaServicoTest {
    static final String PESSOA_NOME_ALTERADO = "Nome alterado";

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PessoaServico pessoaServico;

    @BeforeEach
    void antesDeCadaTeste() {
        pessoaRepository.deleteAll();
    }

    @Test
    void salvarPessoaComItemsSemTransacao_NovaPessoa_Entao_VerificarNoBanco() {
        var pessoa = novaPessoa();
        var item = novoItem();
        pessoa.getItems().add(item);
        pessoaServico.salvarPessoaComItemsSemTransacao(pessoa);
        assertTrue(pessoaRepository.findById(pessoa.getId()).isPresent());
    }

    @Test
    void salvarPessoaComItemsSemTransacao_NovaPessoaEmailDuplicado_Entao_VerificarNoBanco() {
        var pessoa1 = novaPessoa();
        var item1 = novoItem();
        pessoa1.getItems().add(item1);
        pessoaServico.salvarPessoaComItemsSemTransacao(pessoa1);
        assertTrue(pessoaRepository.findById(pessoa1.getId()).isPresent());

        var pessoa2 = novaPessoa();
        var item2 = novoItem();
        pessoa2.getItems().add(item2);

        var excecao = assertThrows(DataIntegrityViolationException.class,
                () -> pessoaServico.salvarPessoaComItemsSemTransacao(pessoa2));
        assertTrue(excecao.getMessage().contains("EMAIL_UNICO"));
        assertFalse(pessoaRepository.findById(pessoa2.getId()).isPresent());
        assertTrue(itemRepository.findById(item2.getId()).isPresent());
    }

    @Test
    void salvarPessoaComItemsComTransacao_NovaPessoaEmailDuplicado_Entao_VerificarNoBanco() {
        var pessoa1 = novaPessoa();
        var item1 = novoItem();
        pessoa1.getItems().add(item1);
        pessoaServico.salvarPessoaComItemsComTransacao(pessoa1);
        assertTrue(pessoaRepository.findById(pessoa1.getId()).isPresent());

        var pessoa2 = novaPessoa();
        var item2 = novoItem();
        pessoa2.getItems().add(item2);

        var excecao = assertThrows(DataIntegrityViolationException.class,
                () -> pessoaServico.salvarPessoaComItemsComTransacao(pessoa2));
        assertTrue(excecao.getMessage().contains("EMAIL_UNICO"));
        assertFalse(pessoaRepository.findById(pessoa2.getId()).isPresent());
        assertFalse(itemRepository.findById(item2.getId()).isPresent());
    }

    @Test
    void atualizarNomeSemTransacao_NovaPessoa_Entao_VerificarNoBanco() {
        var pessoa = novaPessoa();
        pessoaRepository.save(pessoa);
        pessoaServico.atualizarNomeSemTransacao(pessoa.getId(), PESSOA_NOME_ALTERADO);
        pessoaRepository.findById(pessoa.getId())
                .ifPresentOrElse(PessoaServicoTest::verificarNome, Assertions::fail);
    }

    @Test
    void atualizarNomeComTransacao_NovaPessoa_Entao_VerificarNoBanco() {
        var pessoa = novaPessoa();
        pessoaRepository.save(pessoa);
        pessoaServico.atualizarNomeComTransacao(pessoa.getId(), PESSOA_NOME_ALTERADO);
        pessoaRepository.findById(pessoa.getId())
                .ifPresentOrElse(PessoaServicoTest::verificarNome, Assertions::fail);
    }

    static void verificarNome(Pessoa p) {
        assertEquals(PESSOA_NOME_ALTERADO, p.getNome());
    }
}