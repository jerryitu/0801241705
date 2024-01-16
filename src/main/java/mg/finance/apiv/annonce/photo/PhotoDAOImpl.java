package mg.finance.apiv.annonce.photo;

import mg.finance.utils.FonctionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PhotoDAOImpl implements PhotoDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Photo> getAll() {
        String query = "Select a.* " +
                "from Photo a " ;
        return entityManager.createNativeQuery(query, Photo.class).getResultList();
    }

    @Override
    public List<Photo> getByVoiture(Integer idVoiture) {
        String query = "Select a.* " +
                "from Photo a " +
                "where a.id_voiture ="+ FonctionUtils.toStringBase(idVoiture.toString())  ;
        return entityManager.createNativeQuery(query, Photo.class).getResultList();
    }

}
