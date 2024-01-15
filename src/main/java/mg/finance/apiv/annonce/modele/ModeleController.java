package mg.finance.apiv.annonce.modele;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.modele.Modele;
import mg.finance.apiv.annonce.modele.ModeleDAO;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modele")
@RequiredArgsConstructor
public class ModeleController {
    private final ModeleDAO modeleDAO;
    private final ModeleRepo modeleRepo;
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(modeleDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Modele modele){
        try {
            return ResponseEntity.ok().body(modeleRepo.save(modele));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
