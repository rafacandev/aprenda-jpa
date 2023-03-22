package aprenda.jpa.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByNome(String nome);
    Optional<Pessoa> findByNomeAndVinculo(String pessoaNome, Vinculo pessoaVinculo);
    Optional<Pessoa> findByItems_Nome(String itemNome);
}
