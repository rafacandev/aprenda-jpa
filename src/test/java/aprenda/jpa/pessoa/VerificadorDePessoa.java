package aprenda.jpa.pessoa;

import aprenda.jpa.item.VerificadorDeItem;

import static aprenda.jpa.pessoa.ConstantesDePessoa.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VerificadorDePessoa {
    public static void verificarPessoa(Pessoa p) {
        assertNotNull(p.getId());
        assertEquals(PESSOA_NOME, p.getNome());
        assertEquals(PESSOA_EMAIL, p.getEmail());
        assertEquals(PESSOA_VINCULO, p.getVinculo());
    }
    public static void verificarPessoaComItems(Pessoa p) {
        VerificadorDePessoa.verificarPessoa(p);
        p.getItems().forEach(VerificadorDeItem::verificarItem);
    }
}
