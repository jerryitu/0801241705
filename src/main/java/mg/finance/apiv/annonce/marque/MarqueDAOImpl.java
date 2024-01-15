package mg.finance.apiv.annonce.marque;

import mg.finance.apiv.annonce.annonce.Annonce;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MarqueDAOImpl implements MarqueDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Annonce> getAll() {
        String query = "Select a.* " +
                "from Marque a " ;
        return entityManager.createNativeQuery(query, Marque.class).getResultList();
    }

}
