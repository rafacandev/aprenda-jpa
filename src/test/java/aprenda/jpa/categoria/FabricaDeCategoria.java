package aprenda.jpa.categoria;

public class FabricaDeCategoria {
    private static final String CATEGORIA_NOME = "Nome da categoria";

    public static Categoria novaCategoria() {
        var categoria = new Categoria();
        categoria.setNome(CATEGORIA_NOME);
        return categoria;
    }
}
