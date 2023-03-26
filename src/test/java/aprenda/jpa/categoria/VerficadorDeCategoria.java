package aprenda.jpa.categoria;

import org.junit.jupiter.api.Assertions;

import java.util.Set;

import static aprenda.jpa.categoria.ConstantesDeCategoria.CATEGORIA_NOME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerficadorDeCategoria {
    public static void verificarCategoria(Categoria categoria) {
        assertEquals(CATEGORIA_NOME, categoria.getNome());
    }

    public static void verificarCategorias(Set<Categoria> categorias) {
        assertEquals(1, categorias.size());
        categorias
                .stream()
                .findFirst()
                .ifPresentOrElse(VerficadorDeCategoria::verificarCategoria, Assertions::fail);
    }
}
