package mg.finance.apiv.annonce.annonce;

import mg.finance.apiv.annonce.carburant.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceRepo extends JpaRepository<Annonce,Integer> {
}
