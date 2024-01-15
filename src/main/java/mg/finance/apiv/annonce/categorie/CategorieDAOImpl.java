package mg.finance.apiv.annonce.categorie;

import mg.finance.apiv.annonce.annonce.Annonce;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Categorie> getAll() {
        String query = "Select a.* " +
                "from Categorie a " ;
        return entityManager.createNativeQuery(query, Categorie.class).getResultList();
    }

}
