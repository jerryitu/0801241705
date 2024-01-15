package mg.finance.apiv.annonce.modele;

import mg.finance.apiv.annonce.annonce.Annonce;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ModeleDAOImpl implements ModeleDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Modele> getAll() {
        String query = "Select a.* " +
                "from Modele a " ;
        return entityManager.createNativeQuery(query, Modele.class).getResultList();
    }

}
