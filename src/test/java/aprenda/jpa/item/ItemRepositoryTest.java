package aprenda.jpa.item;

import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.qrcode.QrCode;
import aprenda.jpa.qrcode.QrCodeRepository;
import aprenda.jpa.util.ApagadorDeRepositorios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static aprenda.jpa.categoria.FabricaDeCategoria.novaCategoria;
import static aprenda.jpa.item.ConstantesDeItem.ITEM_DESCRICAO;
import static aprenda.jpa.item.FabricaDeItem.novoItem;
import static aprenda.jpa.qrcode.ConstantesDeQrCode.QR_CODE_CODIGO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private QrCodeRepository qrCodeRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ApagadorDeRepositorios apagadorDeRepositorios;

    @BeforeEach
    void antesDeCadaTest() {
        apagadorDeRepositorios.apagarTudo();
    }

    @Test
    void save_NovoItem_Entao_VerificarNoBanco() {
        Item item = novoItem();
        itemRepository.save(item);
        itemRepository
                .findById(item.getId())
                .ifPresentOrElse(VerificadorDeItem::verificarItem, Assertions::fail);
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
                .ifPresentOrElse(VerificadorDeItem::verificarItemComQrCode, Assertions::fail);
    }

    @Test
    void save_NovoItemComCategoria_Entao_VerificarNoBanco() {
        var categoria = novaCategoria();
        categoriaRepository.save(categoria);

        var item = novoItem();
        item.getCategorias().add(categoria);
        itemRepository.save(item);

        itemRepository.findById(item.getId())
                .ifPresentOrElse(VerificadorDeItem::verificarItemComCategoria, Assertions::fail);
    }
}
