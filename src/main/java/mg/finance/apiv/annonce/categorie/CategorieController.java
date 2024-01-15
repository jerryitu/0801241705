package mg.finance.apiv.annonce.categorie;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.carburant.Carburant;
import mg.finance.apiv.annonce.carburant.CarburantDAO;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorie")
@RequiredArgsConstructor
public class CategorieController {
    private final CategorieDAO categorieDAO;
    private final CategorieRepo categorieRepo;
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
            return ResponseEntity.ok().body(categorieRepo.save(categorie));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
