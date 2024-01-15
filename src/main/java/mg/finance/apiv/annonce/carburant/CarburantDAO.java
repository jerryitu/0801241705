package mg.finance.apiv.annonce.carburant;

import mg.finance.apiv.annonce.annonce.Annonce;

import java.util.List;

public interface CarburantDAO {
    List<Annonce> getAll();
}
