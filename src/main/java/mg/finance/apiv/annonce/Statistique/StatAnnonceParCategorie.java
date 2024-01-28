package mg.finance.apiv.annonce.Statistique;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.annonce.categorie.Categorie;
import mg.finance.apiv.annonce.marque.Marque;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="v_stat_categorie_annonce")
public class StatAnnonceParCategorie {
    @Id
    private Integer id;
    private String categorie;
    private Integer nombre;
}
