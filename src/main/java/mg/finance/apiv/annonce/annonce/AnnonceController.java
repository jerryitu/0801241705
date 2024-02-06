package mg.finance.apiv.annonce.annonce;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.annonce.AnnonceService;
import mg.finance.apiv.annonce.carburant.Carburant;
import mg.finance.apiv.annonce.photo.PhotoRepo;
import mg.finance.apiv.annonce.voiture.Voiture;
import mg.finance.apiv.annonce.voiture.VoitureRepo;
import mg.finance.apiv.security.utilisateur.UtilisateurAPIService;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import mg.finance.utils.ErrorResponse;
import mg.finance.utils.FonctionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/annonce")
@RequiredArgsConstructor
@Transactional
public class AnnonceController {
    private final AnnonceDAO annonceService;
    private final AnnonceRepo annonceRepo;
    private final VoitureRepo voitureRepo;
    private final UtilisateurAPIService utilisateurAPIService;
    private final PhotoRepo photoRepo;
    @GetMapping("")
    public ResponseEntity<?> getAll1(){
        try{
            return ResponseEntity.ok().body(annonceRepo.getAllRepo().stream()
                    .distinct()
                    .collect(Collectors.toList()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(annonceRepo.getAllRepo().stream()
                    .distinct()
                    .collect(Collectors.toList()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/valide")
    public ResponseEntity<?> getAllValide(){
        try{
            return ResponseEntity.ok().body(annonceRepo.getAllValidRepo().stream()
                    .distinct()
                    .collect(Collectors.toList()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/mine")
    public ResponseEntity<?> getMine(){
        try{
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            return ResponseEntity.ok().body(annonceService.getMine(userConnected.getId()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @PostMapping("/by-filter")
    public ResponseEntity<?> getByFiltre(@RequestBody Annonce annonce){
        try{
            return ResponseEntity.ok().body(annonceService.getByFilter(annonce));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Annonce annonce){
        Voiture voiture = annonce.getVoiture();
        if(annonce.getId()!=null && !annonce.getId().equals("")){
            Optional<Annonce> annonceOptional = annonceRepo.findById(Integer.valueOf(annonce.getId()));
            Annonce annonceUpdate = annonceOptional.get();
            annonceUpdate.setPrix(annonce.getPrix());
            annonceRepo.save(annonceUpdate);
            Optional<Voiture> voitureOptional = voitureRepo.findById(Integer.valueOf(annonceUpdate.getIdVoiture()));
            Voiture voitureUpdate = voitureOptional.get();
            voitureUpdate.setAnnee(annonce.getVoiture().getAnnee());
            voitureUpdate.setDescription(annonce.getVoiture().getDescription());
            voitureUpdate.setNom(annonce.getVoiture().getNom());
            voitureUpdate.setKilometrage(annonce.getVoiture().getKilometrage());
            voitureUpdate.setPortes(annonce.getVoiture().getPortes());
            voitureUpdate.setSieges(annonce.getVoiture().getSieges());
            voitureUpdate.setPuissance(annonce.getVoiture().getPuissance());
            voitureUpdate.setIdCategorie(annonce.getVoiture().getIdCategorie());
            voitureUpdate.setIdMarque(annonce.getVoiture().getIdMarque());
            voitureUpdate.setIdModele(annonce.getVoiture().getIdModele());
            voitureUpdate.setIdEtat(annonce.getVoiture().getIdEtat());
            voitureUpdate.setIdTransmission(annonce.getVoiture().getIdTransmission());
            voitureRepo.save(voitureUpdate);
            return ResponseEntity.ok().body(annonceUpdate);
        }else if(voiture!=null) {
            try {
                UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
                annonce.setIdVoiture(voitureRepo.save(voiture).getId());
                annonce.setDateAnnonce(LocalDate.now());
                annonce.setIdUser(userConnected.getId());
                annonce.setEtatValidation("0");
                annonce.setEtatVendu("0");
                System.out.println(annonce.getVoiture().getPhoto().size());
                annonce.getVoiture().getPhoto().stream()
                        .forEach(s -> {
                            s.setIdVoiture(annonce.getIdVoiture());
                            photoRepo.save(s);
                            try {
                                annonceService.uploadFile(s.getEncoded(),"images/"+s.getIdVoiture()+"/"+s.getId().toString()+".jpg");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                return ResponseEntity.ok().body(annonceRepo.save(annonce));
            } catch (Exception e) {
                voitureRepo.delete(voiture);
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Erreur de véhicule"));
    }
    @PostMapping("/save-image")
    public ResponseEntity<?> saveImage(@RequestBody Annonce annonce){

        System.out.println(annonce.getVoiture().getPhoto().size());
        annonce.getVoiture().getPhoto().stream()
                .forEach(s -> {
                    //s.setIdVoiture(annonce.getIdVoiture());

                    try {
                        annonceService.uploadFile(s.getEncoded(),"images/"+s.getIdVoiture()+"/"+s.getId().toString()+".jpg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return ResponseEntity.ok().body("annonceRepo.save(annonce)");

    }
    @PostMapping("")
    public ResponseEntity<?> save1(@RequestBody Annonce annonce){
        Voiture voiture = annonce.getVoiture();
        if(annonce.getId()!=null && !annonce.getId().equals("")){
            Optional<Annonce> annonceOptional = annonceRepo.findById(Integer.valueOf(annonce.getId()));
            Annonce annonceUpdate = annonceOptional.get();
            annonceUpdate.setPrix(annonce.getPrix());
            annonceRepo.save(annonceUpdate);
            Optional<Voiture> voitureOptional = voitureRepo.findById(Integer.valueOf(annonceUpdate.getIdVoiture()));
            Voiture voitureUpdate = voitureOptional.get();
            voitureUpdate.setAnnee(annonce.getVoiture().getAnnee());
            voitureUpdate.setDescription(annonce.getVoiture().getDescription());
            voitureUpdate.setNom(annonce.getVoiture().getNom());
            voitureUpdate.setKilometrage(annonce.getVoiture().getKilometrage());
            voitureUpdate.setPortes(annonce.getVoiture().getPortes());
            voitureUpdate.setSieges(annonce.getVoiture().getSieges());
            voitureUpdate.setPuissance(annonce.getVoiture().getPuissance());
            voitureUpdate.setIdCategorie(annonce.getVoiture().getIdCategorie());
            voitureUpdate.setIdMarque(annonce.getVoiture().getIdMarque());
            voitureUpdate.setIdModele(annonce.getVoiture().getIdModele());
            voitureUpdate.setIdEtat(annonce.getVoiture().getIdEtat());
            voitureUpdate.setIdTransmission(annonce.getVoiture().getIdTransmission());
            voitureRepo.save(voitureUpdate);
            return ResponseEntity.ok().body(annonceUpdate);
        }else if(voiture!=null) {
            try {
                UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
                annonce.setIdVoiture(voitureRepo.save(voiture).getId());
                annonce.setDateAnnonce(LocalDate.now());
                annonce.setIdUser(userConnected.getId());
                annonce.setEtatValidation("0");
                annonce.setEtatVendu("0");
                annonce.getVoiture().getPhoto().stream()
                        .forEach(s -> {
                            s.setIdVoiture(annonce.getIdVoiture());
                            photoRepo.save(s);
                            try {
                                annonceService.uploadFile(s.getEncoded(),"images/"+s.getIdVoiture()+"/"+s.getId().toString()+".jpg");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                return ResponseEntity.ok().body(annonceRepo.save(annonce));
            } catch (Exception e) {
                annonce.getVoiture().getPhoto().stream()
                        .forEach(s -> {
                            photoRepo.delete(s);
                        });
                voitureRepo.delete(voiture);
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Erreur de véhicule"));
    }
    @GetMapping("/valider/{id}")
    public ResponseEntity<?> valider(@PathVariable("id") String idAnnonce){
        try{
            Optional<Annonce> annonceOptional = annonceRepo.findById(Integer.valueOf(idAnnonce));
            if(!annonceOptional.isPresent()){
                throw new Exception("L'annonce à valider est introuvable");
            }
            Annonce annonceUpdate = annonceOptional.get();
            annonceUpdate.setEtatValidation("1");
            annonceUpdate.setDateValidation(LocalDate.now());
            annonceRepo.save(annonceUpdate);
            return ResponseEntity.ok().body(annonceUpdate);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
}
