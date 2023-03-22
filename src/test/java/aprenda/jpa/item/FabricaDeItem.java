package aprenda.jpa.item;

import static aprenda.jpa.item.ConstantesDeItem.ITEM_DESCRICAO;
import static aprenda.jpa.item.ConstantesDeItem.ITEM_NOME;

public class FabricaDeItem {
    public static Item novoItem() {
        var item1 = new Item();
        item1.setNome(ITEM_NOME);
        item1.setDescricao(ITEM_DESCRICAO);
        return item1;
    }
}
