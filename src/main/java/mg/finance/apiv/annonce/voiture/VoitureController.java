package mg.finance.apiv.annonce.voiture;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.voiture.Voiture;
import mg.finance.apiv.annonce.voiture.VoitureDAO;
import mg.finance.apiv.annonce.voiture.VoitureRepo;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/voiture")
@RequiredArgsConstructor
public class VoitureController {
    private final VoitureDAO voitureDAO;
    private final VoitureRepo voitureRepo;
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(voitureRepo.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Voiture voiture){
        try {
            if(voiture.getId()!=null && !voiture.getId().equals("")) {
                Optional<Voiture> voitureOptional = voitureRepo.findById(Integer.valueOf(voiture.getId()));
                Voiture voitureUpdate = voitureOptional.get();
                voitureUpdate.setNom(voiture.getNom());
                return ResponseEntity.ok().body(voitureRepo.save(voitureUpdate));
            } else {
                return ResponseEntity.ok().body(voitureRepo.save(voiture));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
