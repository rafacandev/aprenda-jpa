package aprenda.jpa.busca;

import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuscaQueryJpql extends JpaRepository<Pessoa, Integer> {
    @Query("FROM Pessoa p WHERE p.nome = :nome")
    List<Pessoa> buscaPorNome(String nome);

    @Query("FROM Pessoa p WHERE p.nome = :nome AND p.vinculo = :vinculo")
    List<Pessoa> buscaPorNomeEVinculo(String nome, Vinculo vinculo);

    @Query("FROM Pessoa p JOIN p.items i WHERE i.nome = :nome")
    List<Pessoa> buscaPorNomeDoItem(String nome);
}
