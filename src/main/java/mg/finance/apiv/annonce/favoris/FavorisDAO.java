package mg.finance.apiv.annonce.favoris;

import mg.finance.apiv.annonce.annonce.Annonce;

import java.util.List;

public interface FavorisDAO {
    List<Favoris> getAll();
    List<Favoris> getMine(Long idUser);
}
