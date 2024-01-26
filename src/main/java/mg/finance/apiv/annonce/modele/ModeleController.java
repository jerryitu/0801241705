package mg.finance.apiv.annonce.modele;

import lombok.RequiredArgsConstructor;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/modele")
@RequiredArgsConstructor
public class ModeleController {
    private final ModeleDAO modeleDAO;
    private final ModeleRepo modeleRepo;
    @GetMapping("")
    public ResponseEntity<?> getAll1(){
        try{
            return ResponseEntity.ok().body(modeleDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
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
            if(modele.getId()!=null && !modele.getId().equals("")) {
                Optional<Modele> modeleOptional = modeleRepo.findById(Integer.valueOf(modele.getId()));
                Modele modeleUpdate = modeleOptional.get();
                modeleUpdate.setNom(modele.getNom());
                return ResponseEntity.ok().body(modeleRepo.save(modeleUpdate));
            } else {
                return ResponseEntity.ok().body(modeleRepo.save(modele));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save1(@RequestBody Modele modele){
        try {
            if(modele.getId()!=null && !modele.getId().equals("")) {
                Optional<Modele> modeleOptional = modeleRepo.findById(Integer.valueOf(modele.getId()));
                Modele modeleUpdate = modeleOptional.get();
                modeleUpdate.setNom(modele.getNom());
                return ResponseEntity.ok().body(modeleRepo.save(modeleUpdate));
            } else {
                return ResponseEntity.ok().body(modeleRepo.save(modele));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
