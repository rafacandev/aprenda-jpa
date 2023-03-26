package aprenda.jpa.busca;

import aprenda.jpa.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuscaQueryNativa extends JpaRepository<Pessoa, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT p.* FROM Pessoa p WHERE p.nome = :nome")
    List<Pessoa> buscaPorNome(String nome);

    @Query(nativeQuery = true,
            value = "SELECT" +
                    " p.*" +
                    " FROM Pessoa p" +
                    " WHERE p.nome = :nome")
    List<Pessoa> buscaPorNomeEVinculo(String nome);
}
