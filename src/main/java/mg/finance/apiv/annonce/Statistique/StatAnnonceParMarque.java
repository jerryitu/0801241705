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
@Table(name="v_stat_marque_annonce")
public class StatAnnonceParMarque {
    @Id
    private Integer id;
    private String marque;
    private Integer nombre;
}
