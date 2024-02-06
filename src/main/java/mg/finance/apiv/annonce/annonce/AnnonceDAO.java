package mg.finance.apiv.annonce.annonce;

import mg.finance.apiv.annonce.Statistique.StatAnnonceParCategorie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AnnonceDAO {
    List<Annonce> getAll();
    String uploadFile(String encoded, String filName) throws IOException;
    List<Annonce> getMine(Long idUser);
    List<Annonce> getByFilter(Annonce annonce);
    List<Object> getStatParCategorie();
}
