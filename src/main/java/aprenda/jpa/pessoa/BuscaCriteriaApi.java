package aprenda.jpa.pessoa;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static java.util.Optional.of;

@Repository
public class BuscaCriteriaApi {
    @Autowired
    private EntityManager em;

    public Optional<Pessoa> buscaPorNomeDoItem(String nomeDoItem) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Pessoa.class);

        // SELECT * FROM
        var pessoa = query.from(Pessoa.class);
        var items = pessoa.join("items");

        // WHERE item.nome = :nomeDoItem
        var iqualNomeDoItem = cb.equal(items.get("nome"), nomeDoItem);
        query.where(iqualNomeDoItem);

        return of(em.createQuery(query).getSingleResult());
    }
}
