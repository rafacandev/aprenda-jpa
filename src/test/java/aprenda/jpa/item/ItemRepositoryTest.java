package aprenda.jpa.item;

import aprenda.jpa.qrcode.QrCode;
import aprenda.jpa.qrcode.QrCodeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemRepositoryTest {
    private static final String ITEM_NOME = "nome do item";
    private static final String ITEM_DESCRICAO = "descricao do item";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Test
    void save_NovoItem_Entao_VerificarNoBanco() {
        Item item = novoItem();

        itemRepository.save(item);

        itemRepository
                .findById(item.getId())
                .ifPresentOrElse(ItemRepositoryTest::verificarItem, Assertions::fail);
    }

    @Test
    void save_NovaItemFaltandoNome_Entao_ExcecaoAoSalvar() {
        var item = new Item();
        item.setDescricao(ITEM_DESCRICAO);

        var excecao = assertThrows(DataIntegrityViolationException.class, () -> itemRepository.save(item));

        assertEquals("not-null property references a null or transient value : aprenda.jpa.item.Item.nome", excecao.getMessage());
    }


    @Test
    void save_NovaItemComQrCode_Entao_VerificarNoBanco() {
        var qrCode = new QrCode();
        qrCode.setCodigo("Meu codigo");
        qrCodeRepository.save(qrCode);

        var item = novoItem();
        item.setQrCode(qrCode);

        itemRepository.save(item);

        itemRepository.findById(item.getId())
                .ifPresentOrElse(ItemRepositoryTest::verificarItemEQrCode, Assertions::fail);
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
        assertEquals("Meu codigo", qrCode.getCodigo());
    }

    private static void verificarItemEQrCode(Item item) {
        verificarItem(item);
        verificarQrCode(item.getQrCode());
    }
}
