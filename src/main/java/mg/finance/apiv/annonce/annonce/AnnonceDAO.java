package mg.finance.apiv.annonce.annonce;

import mg.finance.apiv.annonce.Statistique.StatAnnonceParCategorie;

import java.util.List;
import java.util.Map;

public interface AnnonceDAO {
    List<Annonce> getAll();
    List<Annonce> getMine(Long idUser);
    List<Annonce> getByFilter(Annonce annonce);
    List<Object> getStatParCategorie();
}
