package aprenda.jpa.pessoa;

import aprenda.jpa.item.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServico {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    public void salvarPessoaComItemsSemTransacao(Pessoa pessoa) {
        itemRepository.saveAll(pessoa.getItems());
        pessoaRepository.save(pessoa);
    }

    @Transactional
    public void salvarPessoaComItemsComTransacao(Pessoa pessoa) {
        itemRepository.saveAll(pessoa.getItems());
        pessoaRepository.save(pessoa);
    }

    public void atualizarNomeSemTransacao(Integer pessoaId, final String nome) {
        pessoaRepository.findById(pessoaId)
                .map(p -> {
                    p.setNome(nome);
                    return p;
                })
                .ifPresent(pessoaRepository::save);
    }

    @Transactional
    public void atualizarNomeComTransacao(Integer pessoaId, final String nome) {
        pessoaRepository.findById(pessoaId)
                .map(p -> {
                    p.setNome(nome);
                    return p;
                });
        // pessoaRepository.save(pessoa); Mas funciona mesmo sem salvar?
    }
}
