package aprenda.jpa.pessoa;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PessoaRepositoryTest {
    private static final String PESSOA_NOME = "nome da pessoa";
    private static final String PESSOA_EMAIL = "test@test.com";
    private static final Vinculo PESSOA_VINCULO = Vinculo.VISITANTE;
    private static final String ITEM_NOME = "nome do item";
    private static final String ITEM_DESCRICAO = "descricao do item";

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void deleteAll() {
        pessoaRepository.deleteAll();
    }

    @Test
    void save_NovaPessoa_Entao_VerificarNoBanco() {
        Pessoa pessoa = novaPessoa();

        pessoaRepository.save(pessoa);

        pessoaRepository.findById(pessoa.getId())
                .ifPresentOrElse(PessoaRepositoryTest::verificarPessoa, Assertions::fail);
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

        assertTrue(excecao.getMessage().contains("UNIQUE_EMAIL"));
    }

    @Test
    void save_NovaPessoaComItems_Entao_VerificarNoBanco() {
        int QUANTIDADE_DE_ITEMS = 2;

        var items = IntStream
                .range(1, QUANTIDADE_DE_ITEMS)
                .mapToObj(i -> novoItem())
                .map(item -> itemRepository.save(item))
                .collect(Collectors.toSet());

        Pessoa pessoa = novaPessoa();
        pessoa.setItems(items);
        pessoaRepository.save(pessoa);

        pessoaRepository.findById(pessoa.getId())
                .ifPresentOrElse(PessoaRepositoryTest::verificarPessoaComItems, Assertions::fail);
    }

    private static Item novoItem() {
        var item1 = new Item();
        item1.setNome(ITEM_NOME);
        item1.setDescricao(ITEM_DESCRICAO);
        return item1;
    }

    private static Pessoa novaPessoa() {
        var pessoa = new Pessoa();
        pessoa.setNome(PESSOA_NOME);
        pessoa.setEmail(PESSOA_EMAIL);
        return pessoa;
    }

    private static void verificarPessoa(Pessoa p) {
        assertNotNull(p.getId());
        assertEquals(PESSOA_NOME, p.getNome());
        assertEquals(PESSOA_EMAIL, p.getEmail());
        assertEquals(PESSOA_VINCULO, p.getVinculo());
    }

    private static void verificarItem(Item i) {
        assertEquals(ITEM_NOME, i.getNome());
        assertEquals(ITEM_DESCRICAO, i.getDescricao());
    }

    private static void verificarPessoaComItems(Pessoa p) {
        verificarPessoa(p);
        p.getItems().forEach(PessoaRepositoryTest::verificarItem);
    }
}
