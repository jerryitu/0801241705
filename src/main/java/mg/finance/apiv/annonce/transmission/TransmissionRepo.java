package mg.finance.apiv.annonce.transmission;

import mg.finance.apiv.annonce.marque.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepo extends JpaRepository<Transmission,Integer> {
}
