package mg.finance.apiv.annonce.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.annonce.marque.Marque;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="modele")
public class Modele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_marque")
    private Integer idMarque;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marque", insertable = false, updatable = false)
    @JsonIgnore
    private Marque marque;
    private String nom;
}
