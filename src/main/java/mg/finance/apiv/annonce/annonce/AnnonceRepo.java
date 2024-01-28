package mg.finance.apiv.annonce.annonce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnonceRepo extends JpaRepository<Annonce,Integer> {
    @Query("select a from Annonce a join fetch a.voiture v join fetch v.categorie join fetch v.etat join fetch v.marque join fetch v.modele join fetch v.transmission left join fetch v.photo ")
    List<Annonce> getAllRepo();

    @Query("select a from Annonce a join fetch a.voiture v join fetch v.categorie join fetch v.etat join fetch v.marque join fetch v.modele join fetch v.transmission left join fetch v.photo where a.etatValidation='1' and a.etatVendu='0' ")
    List<Annonce> getAllValidRepo();
}
