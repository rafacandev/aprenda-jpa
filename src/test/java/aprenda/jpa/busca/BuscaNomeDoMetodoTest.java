package aprenda.jpa.busca;

import aprenda.jpa.item.ConstantesDeItem;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.VerificadorDePessoa;
import aprenda.jpa.util.ApagadorDeRepositorios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static aprenda.jpa.item.FabricaDeItem.novoItem;
import static aprenda.jpa.pessoa.ConstantesDePessoa.PESSOA_NOME;
import static aprenda.jpa.pessoa.ConstantesDePessoa.PESSOA_VINCULO;
import static aprenda.jpa.pessoa.FabricaDePessoa.novaPessoa;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class BuscaNomeDoMetodoTest {
    @Autowired
    private BuscaNomeDoMetodo buscaNomeDoMetodo;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ApagadorDeRepositorios apagadorDeRepositorios;

    @BeforeEach
    void antesDeCadaTeste() {
        apagadorDeRepositorios.apagarTudo();
    }

    @Test
    void findPessoaByName_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        buscaNomeDoMetodo.save(pessoa);
        var pessoas = buscaNomeDoMetodo.findByNome(PESSOA_NOME);
        assertFalse(pessoas.isEmpty());
        pessoas.forEach(VerificadorDePessoa::verificarPessoa);
    }

    @Test
    void findPessoaByNameAndVinculo_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        buscaNomeDoMetodo.save(pessoa);
        var pessoas = buscaNomeDoMetodo.findByNomeAndVinculo(PESSOA_NOME, PESSOA_VINCULO);
        assertFalse(pessoas.isEmpty());
        pessoas.forEach(VerificadorDePessoa::verificarPessoa);
    }

    @Test
    void findPessoaByItemNome_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        pessoa.getItems().add(itemRepository.save(novoItem()));
        buscaNomeDoMetodo.save(pessoa);
        var pessoas = buscaNomeDoMetodo.findByItems_Nome(ConstantesDeItem.ITEM_NOME);
        assertFalse(pessoas.isEmpty());
        pessoas.forEach(VerificadorDePessoa::verificarPessoaComItems);
    }
}
