package mg.finance.apiv.annonce.annonce;

import mg.finance.utils.FonctionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AnnonceDAOImpl implements AnnonceDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Annonce> getAll() {
        String query = "Select a.* " +
                "from Annonce a " ;
        return entityManager.createNativeQuery(query, Annonce.class).getResultList();
    }

    @Override
    public List<Annonce> getMine(Long idUser) {
        String query = "Select a.* " +
                "from Annonce a " +
                "where a.id_user = "+ FonctionUtils.toStringBase(idUser.toString())  ;
        return entityManager.createNativeQuery(query, Annonce.class).getResultList();
    }

    @Override
    public List<Annonce> getByFilter(Annonce annonce) {

        String query = "Select a.* " +
                "from Annonce a " +
                "where id_voiture in "+
                "(select id from voiture where 1=1 "
                + (annonce.getVoiture().getIdMarque() != null ? (!annonce.getVoiture().getIdMarque().equals("") ? " and id_marque ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdMarque().toString()): "") : "")
                + (annonce.getVoiture().getIdCategorie() != null ? (!annonce.getVoiture().getIdCategorie().equals("") ? " and id_categorie ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdCategorie().toString()): "") : "")
                + (annonce.getVoiture().getIdModele() != null ? (!annonce.getVoiture().getIdModele().equals("") ? " and id_modele ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdModele().toString()): "") : "")
                + (annonce.getVoiture().getIdTransmission() != null ? (!annonce.getVoiture().getIdTransmission().equals("") ? " and id_transmission ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdTransmission().toString()): "") : "")
                + (annonce.getVoiture().getIdEtat() != null ? (!annonce.getVoiture().getIdEtat().equals("") ? " and id_etat ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdEtat().toString()): "") : "")
                + (annonce.getVoiture().getNom() != null ? (!annonce.getVoiture().getNom().equals("") ? " and upper(nom) like upper("+ FonctionUtils.toStringBase("%"+annonce.getVoiture().getNom()+"%")+")": "") :"")
                + (annonce.getVoiture().getDescription() != null ? (!annonce.getVoiture().getDescription().equals("") ? " and upper(description) like upper("+ FonctionUtils.toStringBase("%"+annonce.getVoiture().getDescription()+"%")+")": "") :"")
                +")"
                ;
        System.out.println(query);
        return entityManager.createNativeQuery(query, Annonce.class).getResultList();
    }
}
