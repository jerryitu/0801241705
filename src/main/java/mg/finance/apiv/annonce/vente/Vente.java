package mg.finance.apiv.annonce.vente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.annonce.annonce.Annonce;
import mg.finance.apiv.annonce.marque.Marque;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="vente")
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_annonce")
    private Integer idAnnonce;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_annonce", insertable = false, updatable = false)
    private Annonce annopnce;
    private Long idUserAcheteur;
    private Double prixVente;
    private Double commission;
    private LocalDate dateVente;
}
