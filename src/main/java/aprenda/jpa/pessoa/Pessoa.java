package aprenda.jpa.pessoa;

import aprenda.jpa.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "EMAIL_UNICO", columnNames = "email"))
public class Pessoa {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Vinculo vinculo = Vinculo.VISITANTE;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Item> items = new HashSet<>();
}
