package mg.finance.apiv.message.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "conversation")
public class Conversation {
    @Transient
    public static final String SEQUENCE_NAME = "conversation_sequence";
    @Id
    private String id;
    private List<Integer> participants;
    private List<Message> messages;
    private LocalDate dateCreation;
}
