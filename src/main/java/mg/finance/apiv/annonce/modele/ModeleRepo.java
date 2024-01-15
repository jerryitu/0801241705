package mg.finance.apiv.annonce.modele;

import mg.finance.apiv.annonce.carburant.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeleRepo extends JpaRepository<Modele,Integer> {
}
