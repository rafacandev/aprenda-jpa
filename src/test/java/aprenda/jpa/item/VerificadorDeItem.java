package aprenda.jpa.item;

import static aprenda.jpa.categoria.VerficadorDeCategoria.verificarCategorias;
import static aprenda.jpa.item.ConstantesDeItem.ITEM_DESCRICAO;
import static aprenda.jpa.item.ConstantesDeItem.ITEM_NOME;
import static aprenda.jpa.qrcode.VerificadorDeQrCode.verificarQrCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerificadorDeItem {
    public static void verificarItem(Item item) {
        assertEquals(ITEM_NOME, item.getNome());
        assertEquals(ITEM_DESCRICAO, item.getDescricao());
    }

    public static void verificarItemComCategoria(Item item) {
        verificarItem(item);
        verificarCategorias(item.getCategorias());
    }

    public static void verificarItemComQrCode(Item item) {
        verificarItem(item);
        verificarQrCode(item.getQrCode());
    }
}
