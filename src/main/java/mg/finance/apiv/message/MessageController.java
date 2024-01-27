package mg.finance.apiv.message;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.message.collection.Conversation;
import mg.finance.apiv.message.collection.Message;
import mg.finance.apiv.message.collection.UserVu;
import mg.finance.apiv.message.sequence.dao.SequenceDao;
import mg.finance.apiv.security.utilisateur.UtilisateurAPIService;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import mg.finance.utils.ErrorResponse;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.query.Query;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final ConversationRepo conversationRepo;
    private final MongoOperations mongoOperations;
    private final UtilisateurAPIService utilisateurAPIService;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            Query query = new Query();
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            query.addCriteria(Criteria.where("participants").is(userConnected.getId()));
            return ResponseEntity.ok().body(mongoOperations.find(query, Conversation.class));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> envoyer(@RequestBody Conversation conversation){
        try {
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            UserVu uservu = new UserVu();
            uservu.setIdUser(userConnected.getId().intValue());
            uservu.setDateVue(LocalDateTime.now());
            if(conversation.getId()!=null && !conversation.getId().equals("")) {
                Optional<Conversation> conversationOptional = conversationRepo.findById(conversation.getId());
                Conversation conversationUpdate = conversationOptional.get();
                //conversation.getMessages().get(0).setId(String.valueOf(sequenceDao.getNextSequenceId(Message.SEQUENCE_NAME)));
                conversation.getMessages().get(0).setDateEnvoie(LocalDateTime.now());
                conversation.getMessages().get(0).setIdUserEnvoi(Integer.valueOf(userConnected.getId().toString()));
                if(conversation.getMessages().get(0).getUserVus()!=null){
                    conversation.getMessages().get(0).getUserVus().add(uservu);
                }else{
                    conversation.getMessages().get(0).setUserVus(new ArrayList<>());
                    conversation.getMessages().get(0).getUserVus().add(uservu);
                }
                conversationUpdate.getMessages().add(conversation.getMessages().get(0));
                return ResponseEntity.ok().body(conversationRepo.save(conversationUpdate));
            } else {
                //conversation.setId(String.valueOf(sequenceDao.getNextSequenceId(Conversation.SEQUENCE_NAME)));
                //conversation.getMessages().get(0).setId(String.valueOf(sequenceDao.getNextSequenceId(Message.SEQUENCE_NAME)));
                conversation.getParticipants().add(Integer.parseInt(userConnected.getId().toString()));
                conversation.getMessages().get(0).setDateEnvoie(LocalDateTime.now());
                conversation.getMessages().get(0).setIdUserEnvoi(Integer.valueOf(userConnected.getId().toString()));
                if(conversation.getMessages().get(0).getUserVus()!=null){
                    conversation.getMessages().get(0).getUserVus().add(uservu);
                }else{
                    conversation.getMessages().get(0).setUserVus(new ArrayList<>());
                    conversation.getMessages().get(0).getUserVus().add(uservu);
                }
                conversation.setDateCreation(LocalDateTime.now());
                conversationRepo.save(conversation);
                return ResponseEntity.ok().body(conversationRepo.save(conversation));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    @GetMapping("/read/{id}")
    public ResponseEntity<?> vu(@PathVariable("id") String idConversation){
        try {
            UtilisateurAPI userConnected = utilisateurAPIService.getActiveUser();
            Optional<Conversation> conversationOptional = conversationRepo.findById(idConversation);
            Conversation conversationUpdate = conversationOptional.get();
            UserVu uservu = new UserVu();
            uservu.setIdUser(userConnected.getId().intValue());
            uservu.setDateVue(LocalDateTime.now());
            List<UserVu> listUserVu =new ArrayList<>();
            if(conversationUpdate.getMessages().get(conversationUpdate.getMessages().size()-1).getUserVus()==null){
                conversationUpdate.getMessages().get(conversationUpdate.getMessages().size()-1).setUserVus(listUserVu);
            }
            if(!dejaVu(conversationUpdate.getMessages().get(conversationUpdate.getMessages().size()-1).getUserVus(),uservu)) {
                conversationUpdate.getMessages().get(conversationUpdate.getMessages().size() - 1).getUserVus().add(uservu);
            }
            return ResponseEntity.ok().body(conversationRepo.save(conversationUpdate));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
    boolean dejaVu (List<UserVu> userVus, UserVu user){
        for (UserVu userVu : userVus) {
            if (userVu.getIdUser().equals(user.getIdUser())) {
                return true;
            }
        }
        return false;
    }
}
