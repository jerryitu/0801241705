package mg.finance.apiv.annonce.etat;

import mg.finance.apiv.annonce.annonce.Annonce;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EtatDAOImpl implements EtatDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Etat> getAll() {
        String query = "Select a.* " +
                "from Etat a " ;
        return entityManager.createNativeQuery(query, Etat.class).getResultList();
    }

}
