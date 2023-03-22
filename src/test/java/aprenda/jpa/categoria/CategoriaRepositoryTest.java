package aprenda.jpa.categoria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CategoriaRepositoryTest {
    private static final String CATEGORIA_NOME = "Computador pessoal";

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void save_NovaCategoria_Entao_VerificarNoBanco() {
        var categoria = new Categoria();
        categoria.setNome(CATEGORIA_NOME);

        categoriaRepository.save(categoria);

        categoriaRepository.findById(categoria.getId())
                .ifPresentOrElse(CategoriaRepositoryTest::verificarCategoria, Assertions::fail);
    }

    private static void verificarCategoria(Categoria categoria) {
        assertEquals(CATEGORIA_NOME, categoria.getNome());
    }
}
