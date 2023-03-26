package aprenda.jpa.busca;

import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.VerificadorDePessoa;
import aprenda.jpa.util.ApagadorDeRepositorios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static aprenda.jpa.pessoa.ConstantesDePessoa.PESSOA_NOME;
import static aprenda.jpa.pessoa.FabricaDePessoa.novaPessoa;

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
        buscaQueryNativa.buscaPorNome(PESSOA_NOME)
                .ifPresentOrElse(VerificadorDePessoa::verificarPessoa, Assertions::fail);
    }

    @Test
    void buscaPorNomeEVinculo_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        buscaQueryNativa.save(pessoa);
        buscaQueryNativa.buscaPorNomeEVinculo(PESSOA_NOME)
                .ifPresentOrElse(VerificadorDePessoa::verificarPessoa, Assertions::fail);
    }
}
