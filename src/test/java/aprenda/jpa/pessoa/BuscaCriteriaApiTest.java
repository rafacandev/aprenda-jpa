package aprenda.jpa.pessoa;

import aprenda.jpa.item.ConstantesDeItem;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.util.ApagadorDeRepositorios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static aprenda.jpa.item.FabricaDeItem.novoItem;
import static aprenda.jpa.pessoa.FabricaDePessoa.novaPessoa;

@SpringBootTest
public class BuscaCriteriaApiTest {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BuscaCriteriaApi buscaCriteriaApi;
    @Autowired
    private ApagadorDeRepositorios apagadorDeRepositorios;

    @BeforeEach
    void antesDeCadaTeste() {
        apagadorDeRepositorios.apagarTudo();
    }

    @Test
    void findPessoaByItemNome_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        pessoa.getItems().add(itemRepository.save(novoItem()));
        pessoaRepository.save(pessoa);

        buscaCriteriaApi.buscaPorNomeDoItem(ConstantesDeItem.ITEM_NOME)
                .ifPresentOrElse(VerificadorDePessoa::verificarPessoaComItems, Assertions::fail);
    }
}
