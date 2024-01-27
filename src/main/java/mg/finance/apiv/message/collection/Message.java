package mg.finance.apiv.message.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.message.collection.UserVu;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "message")
public class Message {
    @Transient
    public static final String SEQUENCE_NAME = "message";
    @Id
    private String id;
    private Integer idUserEnvoi;
    private String contenu;
    private List<UserVu> userVus;
    private LocalDateTime dateEnvoie;
}
