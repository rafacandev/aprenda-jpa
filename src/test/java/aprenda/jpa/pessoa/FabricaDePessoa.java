package aprenda.jpa.pessoa;

import static aprenda.jpa.pessoa.ConstantesDePessoa.*;

public class FabricaDePessoa {
    public static Pessoa novaPessoa() {
        var pessoa = new Pessoa();
        pessoa.setNome(PESSOA_NOME);
        pessoa.setEmail(PESSOA_EMAIL);
        return pessoa;
    }
}
