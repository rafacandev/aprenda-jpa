package aprenda.jpa.busca;

import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.VerificadorDePessoa;
import aprenda.jpa.util.ApagadorDeRepositorios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static aprenda.jpa.pessoa.ConstantesDePessoa.PESSOA_NOME;
import static aprenda.jpa.pessoa.FabricaDePessoa.novaPessoa;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class BuscaQueryNativaTest {
    @Autowired
    private BuscaQueryNativa buscaQueryNativa;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ApagadorDeRepositorios apagadorDeRepositorios;

    @BeforeEach
    void antesDeCadaTeste() {
        apagadorDeRepositorios.apagarTudo();
    }

    @Test
    void buscaPorNome_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        buscaQueryNativa.save(pessoa);
        var pessoas = buscaQueryNativa.buscaPorNome(PESSOA_NOME);
        assertFalse(pessoas.isEmpty());
        pessoas.forEach(VerificadorDePessoa::verificarPessoa);
    }

    @Test
    void buscaPorNomeEVinculo_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        buscaQueryNativa.save(pessoa);
        var pessoas = buscaQueryNativa.buscaPorNomeEVinculo(PESSOA_NOME);
        assertFalse(pessoas.isEmpty());
        pessoas.forEach(VerificadorDePessoa::verificarPessoa);
    }
}
