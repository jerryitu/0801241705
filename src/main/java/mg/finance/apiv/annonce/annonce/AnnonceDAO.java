package mg.finance.apiv.annonce.annonce;

import java.util.List;

public interface AnnonceDAO {
    List<Annonce> getAll();
    List<Annonce> getMine(Long idUser);
    List<Annonce> getByFilter(Annonce annonce);
}
