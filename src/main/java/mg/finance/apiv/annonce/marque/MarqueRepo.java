package mg.finance.apiv.annonce.marque;

import mg.finance.apiv.annonce.carburant.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueRepo extends JpaRepository<Marque,Integer> {
}
