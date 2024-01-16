package mg.finance.apiv.annonce.carburant;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.categorie.Categorie;
import mg.finance.apiv.annonce.couleur.Couleur;
import mg.finance.apiv.annonce.couleur.CouleurDAO;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carburant")
@RequiredArgsConstructor
public class CarburantController {
    private final CarburantDAO carburantDAO;
    private final CarburantRepo carburantRepo;
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(carburantDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Carburant carburant){
        try {
            if(carburant.getId()!=null && !carburant.getId().equals("")) {
                Optional<Carburant> carburantOptional = carburantRepo.findById(Integer.valueOf(carburant.getId()));
                Carburant carburantUpdate = carburantOptional.get();
                carburantUpdate.setNom(carburant.getNom());
                return ResponseEntity.ok().body(carburantRepo.save(carburantUpdate));
            } else {
                return ResponseEntity.ok().body(carburantRepo.save(carburant));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
