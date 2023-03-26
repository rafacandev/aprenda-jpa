package aprenda.jpa.busca;

import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuscaNomeDoMetodo extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findByNome(String nome);

    List<Pessoa> findByNomeAndVinculo(String pessoaNome, Vinculo pessoaVinculo);

    List<Pessoa> findByItems_Nome(String itemNome);
}
