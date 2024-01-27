package mg.finance.apiv.message.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.security.utilisateur.entity.UtilisateurAPI;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "message")
public class UserVu {
    @Id
    private String id;
    private Integer idUser;
    private LocalDateTime dateVue;
}
