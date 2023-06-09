package aprenda.jpa.busca;

import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.Vinculo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuscaCriteriaApi {
    @Autowired
    private EntityManager em;

    public List<Pessoa> buscaPorNome(String nome) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Pessoa.class);

        // SELECT * FROM pessoa p
        var pessoa = query.from(Pessoa.class);
        // WHERE p.nome = :nome
        var igualNome = cb.equal(pessoa.get("nome"), nome);
        query.where(igualNome);

        return em.createQuery(query).getResultList();
    }

    public List<Pessoa> buscaPorNomeEVinculo(String nome, Vinculo vinculo) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Pessoa.class);

        // SELECT * FROM pessoa p
        var pessoa = query.from(Pessoa.class);
        // WHERE p.nome = :nome
        var igualNome = cb.equal(pessoa.get("nome"), nome);
        var igualVinculo = cb.equal(pessoa.get("vinculo"), vinculo);
        query.where(igualNome, igualVinculo);

        return em.createQuery(query).getResultList();
    }

    public List<Pessoa> buscaPorNomeDoItem(String nomeDoItem) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Pessoa.class);

        // SELECT * FROM pessoa
        var pessoa = query.from(Pessoa.class);
        var items = pessoa.join("items");

        // WHERE item.nome = :nomeDoItem
        var iqualNomeDoItem = cb.equal(items.get("nome"), nomeDoItem);
        query.where(iqualNomeDoItem);

        return em.createQuery(query).getResultList();
    }
}
