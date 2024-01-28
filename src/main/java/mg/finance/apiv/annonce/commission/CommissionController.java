package mg.finance.apiv.annonce.commission;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.categorie.CategorieDAO;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/commission")
@RequiredArgsConstructor
public class CommissionController {
    private final CommissionRepo commissionRepo;

    @GetMapping("")
    public ResponseEntity<?> getAll1(){
        try{
            return ResponseEntity.ok().body(commissionRepo.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/by-categorie/{id}")
    public ResponseEntity<?> getByIdCategorie(@PathVariable("id") String idCategorie){
        try{
            return ResponseEntity.ok().body(commissionRepo.findCommissionByIdCategorie(Integer.valueOf(idCategorie)));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Commission commission){
        try {
            if(commission.getId()!=null && !commission.getId().equals("")) {
                Optional<Commission> categorieOptional = commissionRepo.findById(Integer.valueOf(commission.getId()));
                Commission commissionUpdate = categorieOptional.get();
                commissionUpdate.setTaux(commission.getTaux());
                return ResponseEntity.ok().body(commissionRepo.save(commissionUpdate));
            } else {
                return ResponseEntity.ok().body(commissionRepo.save(commission));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String idToDelete){
        try{
            commissionRepo.deleteById(Integer.valueOf(idToDelete));
            return ResponseEntity.ok().body(idToDelete+" deleted");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
