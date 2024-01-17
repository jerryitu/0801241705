package mg.finance.apiv.annonce.vente;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.annonce.Annonce;
import mg.finance.apiv.annonce.annonce.AnnonceRepo;
import mg.finance.apiv.security.utilisateur.UtilisateurAPIService;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vente")
@RequiredArgsConstructor
public class VenteController {
    private final VenteDAO venteDAO;
    private final VenteRepo venteRepo;
    private final UtilisateurAPIService utilisateurAPIService;
    private final AnnonceRepo annonceRepo;
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(venteRepo.getAllRepo().stream()
                    .distinct()
                    .collect(Collectors.toList()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/get-by-vendeur")
    public ResponseEntity<?> getMine(){
        try{
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            return ResponseEntity.ok().body(venteDAO.getMine(userConnected.getId()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/confirm-vente/{idAnnonce}")
    public ResponseEntity<?> confirmVente(@PathVariable("idAnnonce") String idAnnonce){
        try{
            Optional<Annonce> annonceOptional = annonceRepo.findById(Integer.valueOf(idAnnonce));
            if(!annonceOptional.isPresent()){
                throw new Exception("L'annonce à valider est introuvable");
            }
            Annonce annonceUpdate = annonceOptional.get();
            annonceUpdate.setEtatVendu("1");
            annonceRepo.save(annonceUpdate);
            return ResponseEntity.ok().body(annonceUpdate);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/get-by-acheteur")
    public ResponseEntity<?> getByAcheteur(){
        try{
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            return ResponseEntity.ok().body(venteDAO.getByAcheteur(userConnected.getId()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Vente vente){
        try {
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            vente.setDateVente(LocalDate.now());
            vente.setIdUserAcheteur(userConnected.getId());
            return ResponseEntity.ok().body(venteRepo.save(vente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
