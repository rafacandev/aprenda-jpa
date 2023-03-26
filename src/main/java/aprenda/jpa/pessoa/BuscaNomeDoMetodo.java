package aprenda.jpa.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BuscaNomeDoMetodo extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByNome(String nome);

    Optional<Pessoa> findByNomeAndVinculo(String pessoaNome, Vinculo pessoaVinculo);

    Optional<Pessoa> findByItems_Nome(String itemNome);

    @Query("FROM Pessoa p WHERE p.nome = :nome")
    Optional<Pessoa> buscaPorNome(String nome);

    @Query("FROM Pessoa p WHERE p.nome = :nome AND p.vinculo = :vinculo")
    Optional<Pessoa> buscaPorNomeEVinculo(String nome, Vinculo vinculo);

    @Query("FROM Pessoa p JOIN p.items i WHERE i.nome = :nome")
    Optional<Pessoa> buscaPorNomeDoItem(String nome);

    @Query(nativeQuery = true,
            value = "SELECT p.* FROM Pessoa p WHERE p.nome = :nome")
    Optional<Pessoa> buscaNativaPorNome(String nome);

    @Query(nativeQuery = true,
            value = "SELECT" +
                   " p.*" +
                   " FROM Pessoa p" +
                   " WHERE p.nome = :nome")
    Optional<Pessoa> buscaNativaPorNomeEVinculo(String nome);
}
