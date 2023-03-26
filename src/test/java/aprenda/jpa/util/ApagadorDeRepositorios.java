package aprenda.jpa.util;

import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.PessoaRepository;
import aprenda.jpa.qrcode.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApagadorDeRepositorios {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private QrCodeRepository qrCodeRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public void apagarTudo() {
        pessoaRepository.deleteAll();
        itemRepository.deleteAll();
        categoriaRepository.deleteAll();
        qrCodeRepository.deleteAll();
    }
}
