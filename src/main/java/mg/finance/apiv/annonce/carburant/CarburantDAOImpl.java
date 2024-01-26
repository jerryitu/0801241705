package mg.finance.apiv.annonce.carburant;

import mg.finance.apiv.annonce.annonce.Annonce;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CarburantDAOImpl implements CarburantDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Carburant> getAll() {
        String query = "Select a.* " +
                "from Carburant a " ;
        return entityManager.createNativeQuery(query, Carburant.class).getResultList();
    }

}
