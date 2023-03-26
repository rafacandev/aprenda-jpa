package aprenda.jpa.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BuscaQueryNativa extends JpaRepository<Pessoa, Integer> {
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
