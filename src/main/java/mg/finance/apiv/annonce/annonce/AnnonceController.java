package mg.finance.apiv.annonce.annonce;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.AnnonceService;
import mg.finance.apiv.annonce.carburant.Carburant;
import mg.finance.apiv.annonce.voiture.Voiture;
import mg.finance.apiv.annonce.voiture.VoitureRepo;
import mg.finance.utils.ErrorResponse;
import mg.finance.utils.FonctionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/annonce")
@RequiredArgsConstructor
public class AnnonceController {
    private final AnnonceDAO annonceService;
    private final AnnonceRepo annonceRepo;
    private final VoitureRepo voitureRepo;

    @GetMapping("/get-all")
    //@PreAuthorize("hasAuthority('GET_AGENT_BY_MATRICULE')")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(annonceService.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Annonce annonce){
        Voiture voiture = annonce.getVoiture();
        if(voiture!=null) {
            try {
                annonce.setIdVoiture(voitureRepo.save(voiture).getId());
                annonce.setDateAnnonce(LocalDate.now());
                return ResponseEntity.ok().body(annonceRepo.save(annonce));
            } catch (Exception e) {
                voitureRepo.delete(voiture);
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Erreur de véhicule"));
    }
}
