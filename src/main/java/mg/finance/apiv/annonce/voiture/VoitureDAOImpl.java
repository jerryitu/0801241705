package mg.finance.apiv.annonce.voiture;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VoitureDAOImpl implements VoitureDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Voiture> getAll() {
        String query = "Select a.* " +
                "from Voiture a " ;
        return entityManager.createNativeQuery(query, Voiture.class).getResultList();
    }

}
