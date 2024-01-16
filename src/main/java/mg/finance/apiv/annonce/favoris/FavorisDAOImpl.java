package mg.finance.apiv.annonce.favoris;

import mg.finance.utils.FonctionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FavorisDAOImpl implements FavorisDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Favoris> getAll() {
        String query = "Select a.* " +
                "from Favoris a " ;
        return entityManager.createNativeQuery(query, Favoris.class).getResultList();
    }
    @Override
    public List<Favoris> getMine(Long idUser) {
        String query = "Select a.* " +
                "from Favoris a " +
                "where a.etat = 1 " +
                "and a.id_user = "+ FonctionUtils.toStringBase(idUser.toString())  ;
        return entityManager.createNativeQuery(query, Favoris.class).getResultList();
    }
}
