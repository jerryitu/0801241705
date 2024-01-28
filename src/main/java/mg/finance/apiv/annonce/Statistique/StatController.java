package mg.finance.apiv.annonce.Statistique;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.annonce.Annonce;
import mg.finance.apiv.annonce.annonce.AnnonceDAO;
import mg.finance.apiv.annonce.annonce.AnnonceRepo;
import mg.finance.apiv.annonce.photo.PhotoRepo;
import mg.finance.apiv.annonce.voiture.Voiture;
import mg.finance.apiv.annonce.voiture.VoitureRepo;
import mg.finance.apiv.security.utilisateur.UtilisateurAPIService;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stat")
@RequiredArgsConstructor
@Transactional
public class StatController {
    private final StatAnnonceParCategorieRepo statAnnonceParCategorieRepo;
    private final StatAnnonceParMarqueRepo statAnnonceParMarqueRepo;
    private final StatVenteParMoisRepo statVenteParMoisRepo;
    @GetMapping("/annonce-par-categorie")
    public ResponseEntity<?> getAll1(){
        try{
            List<StatAnnonceParCategorie> stat = statAnnonceParCategorieRepo.findAll();
            return ResponseEntity.ok().body(stat);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/annonce-par-marque")
    public ResponseEntity<?> getAnnonceMarque(){
        try{
            List<StatAnnonceParMarque> stat = statAnnonceParMarqueRepo.findAll();
            return ResponseEntity.ok().body(stat);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/vente-par-mois")
    public ResponseEntity<?> getVenteParMois(){
        try{
            List<StatVenteParMois> stat = statVenteParMoisRepo.findAll();
            return ResponseEntity.ok().body(stat);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
