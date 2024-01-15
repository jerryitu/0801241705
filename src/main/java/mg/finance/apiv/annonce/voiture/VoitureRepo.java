package mg.finance.apiv.annonce.voiture;

import mg.finance.apiv.annonce.modele.Modele;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepo extends JpaRepository<Voiture,Integer> {
}
