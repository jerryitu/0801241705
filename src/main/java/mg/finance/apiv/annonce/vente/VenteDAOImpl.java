package mg.finance.apiv.annonce.vente;

import mg.finance.apiv.annonce.annonce.Annonce;
import mg.finance.utils.FonctionUtils;
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

    @Override
    public List<Vente> getMine(Long idUser) {
        String query = "Select a.* " +
                "from Vente a " +
                "where a.id_annonce in "
                +"(Select id from Annonce where idUser="+ FonctionUtils.toStringBase(idUser.toString())+")"  ;
        return entityManager.createNativeQuery(query, Vente.class).getResultList();
    }

    @Override
    public List<Vente> getByAcheteur(Long idUser) {
        String query = "Select a.* " +
                "from Vente a " +
                "where a.id_user_acheteur = "+ FonctionUtils.toStringBase(idUser.toString())  ;
        return entityManager.createNativeQuery(query, Vente.class).getResultList();
    }
}
