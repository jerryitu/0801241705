package mg.finance.apiv.message;

import lombok.RequiredArgsConstructor;
import mg.finance.apiv.message.collection.Conversation;
import mg.finance.apiv.message.collection.Message;
import mg.finance.apiv.message.sequence.dao.SequenceDao;
import mg.finance.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepo messageRepo;
    private final ConversationRepo conversationRepo;
    private final SequenceDao sequenceDao;
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok().body(conversationRepo.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Message message){
        try {
            if(message.getId()!=null && !message.getId().equals("")) {
//                Optional<Conversation> conversationOptional = conversationRepo.findById(message.getId());
//                Message messageUpdate = messageOptional.get();
//                messageUpdate.setNom(message.getNom());
                return ResponseEntity.ok().body(messageRepo.save(message));
            } else {
//                conversationRepo.save(message);
                return ResponseEntity.ok().body(messageRepo.save(message));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/envoyer")
    public ResponseEntity<?> envoyer(@RequestBody Conversation conversation){
        try {
            if(conversation.getId()!=null && !conversation.getId().equals("")) {
                Optional<Conversation> conversationOptional = conversationRepo.findById(conversation.getId());
                Conversation conversationUpdate = conversationOptional.get();
                conversation.getMessages().get(0).setId(String.valueOf(sequenceDao.getNextSequenceId(Message.SEQUENCE_NAME)));
                conversation.getMessages().get(0).setDateEnvoie(LocalDate.now());
                conversationUpdate.getMessages().add(conversation.getMessages().get(0));
                return ResponseEntity.ok().body(conversationRepo.save(conversationUpdate));
            } else {
                conversation.setId(String.valueOf(sequenceDao.getNextSequenceId(Conversation.SEQUENCE_NAME)));
                conversation.getMessages().get(0).setId(String.valueOf(sequenceDao.getNextSequenceId(Message.SEQUENCE_NAME)));
                conversation.getMessages().get(0).setDateEnvoie(LocalDate.now());
                conversation.setDateCreation(LocalDate.now());
                conversationRepo.save(conversation);
                return ResponseEntity.ok().body(conversationRepo.save(conversation));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }

}
