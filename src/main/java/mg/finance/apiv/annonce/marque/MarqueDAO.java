package mg.finance.apiv.annonce.marque;

import mg.finance.apiv.annonce.annonce.Annonce;

import java.util.List;

public interface MarqueDAO {
    List<Annonce> getAll();
}
