package mg.finance.apiv.annonce.marque;

import lombok.RequiredArgsConstructor;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/marque")
@RequiredArgsConstructor
public class MarqueController {
    private final MarqueDAO marqueDAO;
    private final MarqueRepo marqueRepo;
    @GetMapping("")
    public ResponseEntity<?> getAll1(){
        try{
            return ResponseEntity.ok().body(marqueDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(marqueDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Marque marque){
        try {
            if(marque.getId()!=null && !marque.getId().equals("")) {
                Optional<Marque> marqueOptional = marqueRepo.findById(Integer.valueOf(marque.getId()));
                Marque marqueUpdate = marqueOptional.get();
                marqueUpdate.setNom(marque.getNom());
                return ResponseEntity.ok().body(marqueRepo.save(marqueUpdate));
            } else {
                return ResponseEntity.ok().body(marqueRepo.save(marque));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save1(@RequestBody Marque marque){
        try {
            if(marque.getId()!=null && !marque.getId().equals("")) {
                Optional<Marque> marqueOptional = marqueRepo.findById(Integer.valueOf(marque.getId()));
                Marque marqueUpdate = marqueOptional.get();
                marqueUpdate.setNom(marque.getNom());
                return ResponseEntity.ok().body(marqueRepo.save(marqueUpdate));
            } else {
                return ResponseEntity.ok().body(marqueRepo.save(marque));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
