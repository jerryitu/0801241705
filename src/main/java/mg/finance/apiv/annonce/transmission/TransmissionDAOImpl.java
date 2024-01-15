package mg.finance.apiv.annonce.transmission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TransmissionDAOImpl implements TransmissionDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Transmission> getAll() {
        String query = "Select a.* " +
                "from Transmission a " ;
        return entityManager.createNativeQuery(query, Transmission.class).getResultList();
    }

}
