package mg.finance.apiv.annonce.annonce;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.annonce.photo.Photo;
import mg.finance.apiv.annonce.voiture.Voiture;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="annonce")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double prix;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateAnnonce;
    @Column(name = "id_voiture")
    private Integer idVoiture;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_voiture", insertable = false, updatable = false)
    private Voiture voiture;
    private Long idUser;
    private String etatValidation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateValidation;
    private String etatVendu;
}
