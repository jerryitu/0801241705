package mg.finance.apiv.annonce.transmission;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.transmission.Transmission;
import mg.finance.apiv.annonce.transmission.TransmissionDAO;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transmission")
@RequiredArgsConstructor
public class TransmissionController {
    private final TransmissionDAO transmissionDAO;
    private final TransmissionRepo transmissionRepo;
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(transmissionDAO.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Transmission transmission){
        try {
            return ResponseEntity.ok().body(transmissionRepo.save(transmission));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
