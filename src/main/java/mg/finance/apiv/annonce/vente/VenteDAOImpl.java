package mg.finance.apiv.annonce.vente;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VenteDAOImpl implements VenteDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Vente> getAll() {
        String query = "Select a.* " +
                "from Vente a " ;
        return entityManager.createNativeQuery(query, Vente.class).getResultList();
    }

}
