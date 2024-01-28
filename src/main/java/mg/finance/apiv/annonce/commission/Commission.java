package mg.finance.apiv.annonce.commission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.annonce.categorie.Categorie;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="commission")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_categorie")
    private Integer idCategorie;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categorie", insertable = false, updatable = false)
    private Categorie categorie;
    private Double taux;
}
