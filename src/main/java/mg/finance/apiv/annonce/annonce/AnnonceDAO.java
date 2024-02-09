package mg.finance.apiv.annonce.annonce;

import mg.finance.apiv.annonce.Statistique.StatAnnonceParCategorie;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AnnonceDAO {
    List<Annonce> getAll();
    String uploadFile(MultipartFile file, String encoded, String filName) throws IOException;
    List<Annonce> getMine(Long idUser);
    List<Annonce> getByFilter(Annonce annonce);
    List<Object> getStatParCategorie();
}
