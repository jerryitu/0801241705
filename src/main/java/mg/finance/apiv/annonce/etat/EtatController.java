package mg.finance.apiv.annonce.etat;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.annonce.AnnonceDAO;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etat")
@RequiredArgsConstructor
public class EtatController {
    private final EtatDAO etatDAO;
    private final EtatRepo etatRepo;
    @GetMapping("")
    //@PreAuthorize("hasAuthority('GET_AGENT_BY_MATRICULE')")
    public ResponseEntity<?> getAll1(){
        try{
            return ResponseEntity.ok().body(etatDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/get-all")
    //@PreAuthorize("hasAuthority('GET_AGENT_BY_MATRICULE')")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(etatDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Etat etat){
        try {
            System.out.println(etat.getNom());
            return ResponseEntity.ok().body(etatRepo.save(etat));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @PostMapping("")
    public ResponseEntity<?> save1(@RequestBody Etat etat){
        try {
            System.out.println(etat.getNom());
            return ResponseEntity.ok().body(etatRepo.save(etat));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String idToDelete){
        try{
            etatRepo.deleteById(Integer.valueOf(idToDelete));
            return ResponseEntity.ok().body(idToDelete+" deleted");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
}
