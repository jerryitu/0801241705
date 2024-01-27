package mg.finance.apiv.annonce.categorie;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.categorie.Categorie;
import mg.finance.apiv.annonce.categorie.CategorieDAO;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categorie")
@RequiredArgsConstructor
public class CategorieController {
    private final CategorieDAO categorieDAO;
    private final CategorieRepo categorieRepo;

    @GetMapping("")
    public ResponseEntity<?> getAll1(){
        try{
            return ResponseEntity.ok().body(categorieDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(categorieDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Categorie categorie){
        try {
            if(categorie.getId()!=null && !categorie.getId().equals("")) {
                Optional<Categorie> categorieOptional = categorieRepo.findById(Integer.valueOf(categorie.getId()));
                Categorie categorieUpdate = categorieOptional.get();
                categorieUpdate.setNom(categorie.getNom());
                return ResponseEntity.ok().body(categorieRepo.save(categorieUpdate));
            } else {
                return ResponseEntity.ok().body(categorieRepo.save(categorie));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @PostMapping("")
    public ResponseEntity<?> save1(@RequestBody Categorie categorie){
        try {
            if(categorie.getId()!=null && !categorie.getId().equals("")) {
                Optional<Categorie> categorieOptional = categorieRepo.findById(Integer.valueOf(categorie.getId()));
                Categorie categorieUpdate = categorieOptional.get();
                categorieUpdate.setNom(categorie.getNom());
                return ResponseEntity.ok().body(categorieRepo.save(categorieUpdate));
            } else {
                return ResponseEntity.ok().body(categorieRepo.save(categorie));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String idToDelete){
        try{
            categorieRepo.deleteById(Integer.valueOf(idToDelete));
            return ResponseEntity.ok().body(idToDelete+" deleted");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
