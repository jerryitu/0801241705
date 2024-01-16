package mg.finance.apiv.annonce.photo;

import java.util.List;

public interface PhotoDAO {
    List<Photo> getAll();
    List<Photo> getByVoiture(Integer idVoiture);
}
