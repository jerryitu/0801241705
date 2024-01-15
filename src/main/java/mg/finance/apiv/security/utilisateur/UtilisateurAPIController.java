package mg.finance.apiv.security.utilisateur;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.finance.apiv.security.configuration.utils.JwtGenerator;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import mg.finance.apiv.security.utilisateur.viewObject.UtilisateurAPIPasswordVO;
import mg.finance.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utilisateur")
@Slf4j
//@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
public class UtilisateurAPIController {

    private final UtilisateurAPIService utilisateurService;

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('AJOUTER_UTILISATEUR')")
    public ResponseEntity<?> saveUtilisateur(@RequestBody UtilisateurAPI utilisateur){
        try{
            URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/utilisateur/save").toUriString());
            UtilisateurAPI utilisateurSaved = utilisateurService.saveUtilisateur(utilisateur);
            return ResponseEntity.created(uri).body(utilisateurSaved);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("L'identifiant "+ utilisateur.getUsername() +" existe déjà!"));
        }catch (Exception e){
            e.printStackTrace();
            log.error("Erreur lors de l'enregistrement d'un nouvel utilisateur: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Erreur de l'enregistrement"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?>updateUtilisateur(@RequestBody UtilisateurAPI utilisateur){
        try {
            return ResponseEntity.ok().body(utilisateurService.updateUtilisateur(utilisateur));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }

    }

    @PutMapping("/update/password")
    public ResponseEntity<?> updateUtilisateurPassword(@RequestBody UtilisateurAPIPasswordVO utilisateur){
        try {
            return ResponseEntity.ok().body(utilisateurService.updateUtilisateurPassword(utilisateur));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

//    @PutMapping("/activer")
//    @PreAuthorize("hasAuthority('ACTIVER_UTILISATEUR')")
//    public ResponseEntity<?>activerUtilisateur(@RequestBody UtilisateurAPIPasswordVO utilisateur){
//        try {
//            return ResponseEntity.ok().body(utilisateurService.activerUtilisateur(utilisateur));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
//        }
//
//    }
//
//    @PutMapping("/desactiver")
//    @PreAuthorize("hasAuthority('DESACTIVER_UTILISATEUR')")
//    public ResponseEntity<?>desactiverUtilisateur(@RequestBody UtilisateurVO utilisateur){
//        try {
//            return ResponseEntity.ok().body(utilisateurService.desactiverUtilisateur(utilisateur, utilisateur.getMotif()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
//        }
//
//    }


}
