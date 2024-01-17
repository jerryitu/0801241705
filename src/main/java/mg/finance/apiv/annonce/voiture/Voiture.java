package mg.finance.apiv.annonce.voiture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.finance.apiv.annonce.categorie.Categorie;
import mg.finance.apiv.annonce.etat.Etat;
import mg.finance.apiv.annonce.marque.Marque;
import mg.finance.apiv.annonce.modele.Modele;
import mg.finance.apiv.annonce.photo.Photo;
import mg.finance.apiv.annonce.transmission.Transmission;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="voiture")
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String description;
    @Column(name = "id_marque")
    private Integer idMarque;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marque", insertable = false, updatable = false)
    private Marque marque;
    @Column(name = "id_categorie")
    private Integer idCategorie;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categorie", insertable = false, updatable = false)
    private Categorie categorie;
    @Column(name = "id_modele")
    private Integer idModele;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modele", insertable = false, updatable = false)
    private Modele modele;
    @Column(name = "id_transmission")
    private Integer idTransmission;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transmission", insertable = false, updatable = false)
    private Transmission transmission;
    @Column(name = "id_etat")
    private Integer idEtat;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_etat", insertable = false, updatable = false)
    private Etat etat;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idVoiture", insertable = false, updatable = false)
    private List<Photo> photo=new ArrayList<>();
    private Double kilometrage;
    private Double puissance;
    private Integer annee;
    private Integer portes;
    private Integer sieges;
}
