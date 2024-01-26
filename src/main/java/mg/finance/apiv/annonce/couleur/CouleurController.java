package mg.finance.apiv.annonce.couleur;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.couleur.Couleur;
import mg.finance.apiv.annonce.etat.Etat;
import mg.finance.apiv.annonce.etat.EtatDAO;
import mg.finance.apiv.annonce.etat.EtatRepo;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/couleur")
@RequiredArgsConstructor
public class CouleurController {
    private final CouleurDAO couleurDAO;
    private final CouleurRepo couleurRepo;
    @GetMapping("")
    public ResponseEntity<?> getAll1(){
        try{
            return ResponseEntity.ok().body(couleurDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(couleurDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Couleur couleur){
        try {
            if(couleur.getId()!=null && !couleur.getId().equals("")) {
                Optional<Couleur> couleurOptional = couleurRepo.findById(Integer.valueOf(couleur.getId()));
                Couleur couleurUpdate = couleurOptional.get();
                couleurUpdate.setNom(couleur.getNom());
                return ResponseEntity.ok().body(couleurRepo.save(couleurUpdate));
            } else {
                return ResponseEntity.ok().body(couleurRepo.save(couleur));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @PostMapping("")
    public ResponseEntity<?> save1(@RequestBody Couleur couleur){
        try {
            if(couleur.getId()!=null && !couleur.getId().equals("")) {
                Optional<Couleur> couleurOptional = couleurRepo.findById(Integer.valueOf(couleur.getId()));
                Couleur couleurUpdate = couleurOptional.get();
                couleurUpdate.setNom(couleur.getNom());
                return ResponseEntity.ok().body(couleurRepo.save(couleurUpdate));
            } else {
                return ResponseEntity.ok().body(couleurRepo.save(couleur));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
