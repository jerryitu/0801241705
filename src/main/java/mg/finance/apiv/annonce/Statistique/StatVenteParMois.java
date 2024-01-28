package mg.finance.apiv.annonce.Statistique;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="v_vente_par_mois")
public class StatVenteParMois {
    @Id
    private Integer id;
    private String periode;
    private Integer nombre;
}
