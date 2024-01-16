package mg.finance.apiv.annonce.favoris;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.annonce.annonce.Annonce;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="favoris")
public class Favoris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long idUser;
    @Column(name = "id_annonce")
    private Integer idAnnonce;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_annonce", insertable = false, updatable = false)
    private Annonce annonce;
    private Integer etat;
}
