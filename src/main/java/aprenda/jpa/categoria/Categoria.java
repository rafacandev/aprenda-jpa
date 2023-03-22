package aprenda.jpa.categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private Integer id;

    private String nome;
}
