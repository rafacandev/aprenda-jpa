package aprenda.jpa.item;

import aprenda.jpa.categoria.Categoria;
import aprenda.jpa.qrcode.QrCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @OneToOne
    private QrCode qrCode;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Categoria> categorias = new HashSet<>();
}
