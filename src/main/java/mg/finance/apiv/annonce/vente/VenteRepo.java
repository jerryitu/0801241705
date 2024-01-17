package mg.finance.apiv.annonce.vente;

import mg.finance.apiv.annonce.annonce.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VenteRepo extends JpaRepository<Vente,Integer> {
    @Query("select a from Vente a join fetch a.annopnce b join fetch b.voiture v join fetch v.categorie join fetch v.etat join fetch v.marque join fetch v.modele join fetch v.transmission left join fetch v.photo")
    List<Vente> getAllRepo();
}
