package mg.finance.apiv.annonce.favoris;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.security.utilisateur.UtilisateurAPIService;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/favoris")
@RequiredArgsConstructor
public class FavorisController {
    private final FavorisDAO favorisDAO;
    private final FavorisRepo favorisRepo;
    private final UtilisateurAPIService utilisateurAPIService;
    @GetMapping("/get-mine")
    public ResponseEntity<?> getMine(){
        try{
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            return ResponseEntity.ok().body(favorisDAO.getMine(userConnected.getId()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/save/{idAnnonce}")
    public ResponseEntity<?> save(@PathVariable("idAnnonce") String idAnnonce){
        try {
            Favoris favoris = new Favoris();
            favoris.setIdAnnonce(Integer.valueOf(idAnnonce));
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            favoris.setIdUser(userConnected.getId());
            favoris.setEtat(1);
            return ResponseEntity.ok().body(favorisRepo.save(favoris));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/remove/{idFavoris}")
    public ResponseEntity<?> remove(@PathVariable("idFavoris") String idFavoris){
        try {
            Optional<Favoris> favorisOptional = favorisRepo.findById(Integer.valueOf(idFavoris));
            if(!favorisOptional.isPresent()){
                throw new Exception("Favoris introuvable");
            }
            Favoris favorisUpdate = favorisOptional.get();
            favorisUpdate.setEtat(0);
            return ResponseEntity.ok().body(favorisRepo.save(favorisUpdate));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
