package mg.finance.apiv.annonce.annonce;

import mg.finance.apiv.annonce.Statistique.StatAnnonceParCategorie;

import java.util.List;

public interface AnnonceDAO {
    List<Annonce> getAll();
    List<Annonce> getMine(Long idUser);
    List<Annonce> getByFilter(Annonce annonce);
    List<StatAnnonceParCategorie> getStatParCategorie();
}
