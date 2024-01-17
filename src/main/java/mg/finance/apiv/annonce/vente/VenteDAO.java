package mg.finance.apiv.annonce.vente;

import java.util.List;

public interface VenteDAO {
    List<Vente> getAll();
    List<Vente> getMine(Long idUser);
    List<Vente> getByAcheteur(Long idUser);
}
