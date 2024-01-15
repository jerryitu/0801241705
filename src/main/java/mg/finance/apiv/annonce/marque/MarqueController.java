package mg.finance.apiv.annonce.marque;

import lombok.RequiredArgsConstructor;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marque")
@RequiredArgsConstructor
public class MarqueController {
    private final MarqueDAO marqueDAO;
    private final MarqueRepo marqueRepo;
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
            return ResponseEntity.ok().body(marqueRepo.save(marque));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
