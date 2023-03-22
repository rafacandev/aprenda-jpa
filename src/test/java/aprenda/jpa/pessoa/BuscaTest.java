package aprenda.jpa.pessoa;

import aprenda.jpa.item.ConstantesDeItem;
import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static aprenda.jpa.item.FabricaDeItem.novoItem;
import static aprenda.jpa.pessoa.ConstantesDePessoa.PESSOA_NOME;
import static aprenda.jpa.pessoa.ConstantesDePessoa.PESSOA_VINCULO;
import static aprenda.jpa.pessoa.FabricaDePessoa.novaPessoa;

@SpringBootTest
public class BuscaTest {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void antesDeCadaTeste() {
        pessoaRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    void findPessoaByName_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        pessoaRepository.save(pessoa);
        pessoaRepository.findByNome(PESSOA_NOME)
                .ifPresentOrElse(VerificadorDePessoa::verificarPessoa, Assertions::fail);
    }

    @Test
    void findPessoaByNameAndVinculo_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        pessoaRepository.save(pessoa);
        pessoaRepository.findByNomeAndVinculo(PESSOA_NOME, PESSOA_VINCULO)
                .ifPresentOrElse(VerificadorDePessoa::verificarPessoaComItems, Assertions::fail);
    }

    @Test
    void findPessoaByItemNome_PessoaExisteNoBanco_RetornaPessoa() {
        var pessoa = novaPessoa();
        pessoa.getItems().add(itemRepository.save(novoItem()));
        pessoaRepository.save(pessoa);

        pessoaRepository.findByItems_Nome(ConstantesDeItem.ITEM_NOME)
                .ifPresentOrElse(VerificadorDePessoa::verificarPessoaComItems, Assertions::fail);
    }
}
