package mg.finance.apiv.annonce.etat;

import mg.finance.apiv.annonce.annonce.Annonce;

import java.util.List;

public interface EtatDAO {
    List<Etat> getAll();
}
