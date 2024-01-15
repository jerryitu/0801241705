package mg.finance.apiv.annonce.couleur;

import mg.finance.apiv.annonce.annonce.Annonce;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CouleurDAOImpl implements CouleurDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Couleur> getAll() {
        String query = "Select a.* " +
                "from Couleur a " ;
        return entityManager.createNativeQuery(query, Couleur.class).getResultList();
    }

}
