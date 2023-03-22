package aprenda.jpa.item;

import static aprenda.jpa.item.ConstantesDeItem.ITEM_DESCRICAO;
import static aprenda.jpa.item.ConstantesDeItem.ITEM_NOME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerificadorDeItem {
    public static void verificarItem(Item i) {
        assertEquals(ITEM_NOME, i.getNome());
        assertEquals(ITEM_DESCRICAO, i.getDescricao());
    }
}
