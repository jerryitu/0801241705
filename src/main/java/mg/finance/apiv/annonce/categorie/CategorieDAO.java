package mg.finance.apiv.annonce.categorie;

import mg.finance.apiv.annonce.annonce.Annonce;

import java.util.List;

public interface CategorieDAO {
    List<Categorie> getAll();
}
