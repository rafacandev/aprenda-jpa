package aprenda.jpa.item;

import aprenda.jpa.categoria.Categoria;
import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.qrcode.QrCode;
import aprenda.jpa.qrcode.QrCodeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemRepositoryTest {
    private static final String ITEM_NOME = "Nome do item";
    private static final String ITEM_DESCRICAO = "Descricao do item";
    private static final String CATEGORIA_NOME = "Nome da categoria";
    private static final String QR_CODE_CODIGO = "Meu codigo";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private QrCodeRepository qrCodeRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void save_NovoItem_Entao_VerificarNoBanco() {
        Item item = novoItem();

        itemRepository.save(item);

        itemRepository
                .findById(item.getId())
                .ifPresentOrElse(ItemRepositoryTest::verificarItem, Assertions::fail);
    }

    @Test
    void save_NovoItemFaltandoNome_Entao_ExcecaoAoSalvar() {
        var item = new Item();
        item.setDescricao(ITEM_DESCRICAO);

        var excecao = assertThrows(DataIntegrityViolationException.class, () -> itemRepository.save(item));

        assertEquals("not-null property references a null or transient value : aprenda.jpa.item.Item.nome", excecao.getMessage());
    }

    @Test
    void save_NovoItemComQrCode_Entao_VerificarNoBanco() {
        var qrCode = new QrCode();
        qrCode.setCodigo(QR_CODE_CODIGO);
        qrCodeRepository.save(qrCode);

        var item = novoItem();
        item.setQrCode(qrCode);

        itemRepository.save(item);

        itemRepository.findById(item.getId())
                .ifPresentOrElse(ItemRepositoryTest::verificarItemEQrCode, Assertions::fail);
    }

    @Test
    void save_NovoItemComCategoria_Entao_VerificarNoBanco() {
        var categoria = new Categoria();
        categoria.setNome(CATEGORIA_NOME);
        categoriaRepository.save(categoria);

        var item = novoItem();
        item.getCategorias().add(categoria);

        itemRepository.save(item);

        itemRepository.findById(item.getId())
                .ifPresentOrElse(ItemRepositoryTest::verificarItemECategoria, Assertions::fail);
    }

    private static Item novoItem() {
        var item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        return item;
    }

    private static void verificarItem(Item item) {
        assertEquals(ITEM_NOME, item.getNome());
        assertEquals(ITEM_DESCRICAO, item.getDescricao());
    }

    private static void verificarQrCode(QrCode qrCode) {
        assertEquals(QR_CODE_CODIGO, qrCode.getCodigo());
    }

    private static void verificarCategorias(Set<Categoria> categorias) {
        assertEquals(1, categorias.size());
        categorias
                .stream()
                .findFirst()
                .ifPresentOrElse(ItemRepositoryTest::verificarCategoria, Assertions::fail);
    }

    private static void verificarCategoria(Categoria categoria) {
        assertEquals(CATEGORIA_NOME, categoria.getNome());
    }

    private static void verificarItemEQrCode(Item item) {
        verificarItem(item);
        verificarQrCode(item.getQrCode());
    }

    private static void verificarItemECategoria(Item item) {
        verificarItem(item);
        verificarCategorias(item.getCategorias());
    }
}
