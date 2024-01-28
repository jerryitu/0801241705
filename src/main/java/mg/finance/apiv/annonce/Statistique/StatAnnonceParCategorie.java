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
public class StatAnnonceParCategorie {
    private Integer idMarque;
    private Categorie categorie;
    private Integer nombre;
}
