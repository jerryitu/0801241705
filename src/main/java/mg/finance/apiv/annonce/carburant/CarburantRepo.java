package mg.finance.apiv.annonce.carburant;

import mg.finance.apiv.annonce.couleur.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarburantRepo extends JpaRepository<Carburant,Integer> {
}
