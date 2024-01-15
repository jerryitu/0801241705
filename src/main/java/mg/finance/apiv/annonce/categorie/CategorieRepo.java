package mg.finance.apiv.annonce.categorie;

import mg.finance.apiv.annonce.carburant.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepo extends JpaRepository<Categorie,Integer> {
}
