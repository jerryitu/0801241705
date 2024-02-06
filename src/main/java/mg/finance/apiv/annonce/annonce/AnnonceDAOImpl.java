package mg.finance.apiv.annonce.annonce;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import lombok.extern.java.Log;
import mg.finance.apiv.annonce.Statistique.StatAnnonceParCategorie;
import mg.finance.utils.FonctionUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Repository
public class AnnonceDAOImpl implements AnnonceDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public String uploadFile(String encoded, String fileName) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded.trim().substring("data:image/jpeg;base64,".length()));

        BlobId blobId = BlobId.of("project-050224.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource("project-050224-firebase-adminsdk-2yt78-94f1b1ac6a.json").getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, decodedBytes);

        return String.format("DOWNLOAD_URL", URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    @Override
    public List<Annonce> getAll() {
        String query = "Select a.* " +
                "from Annonce a " ;
        return entityManager.createNativeQuery(query, Annonce.class).getResultList();
    }

    @Override
    public List<Annonce> getMine(Long idUser) {
        String query = "Select a.* " +
                "from Annonce a " +
                "where a.id_user = "+ FonctionUtils.toStringBase(idUser.toString())  ;
        return entityManager.createNativeQuery(query, Annonce.class).getResultList();
    }

    @Override
    public List<Annonce> getByFilter(Annonce annonce) {

        String query = "Select a.* " +
                "from Annonce a " +
                "where id_voiture in "+
                "(select id from voiture where 1=1 "
                + (annonce.getVoiture().getIdMarque() != null ? (!annonce.getVoiture().getIdMarque().equals("") ? " and id_marque ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdMarque().toString()): "") : "")
                + (annonce.getVoiture().getIdCategorie() != null ? (!annonce.getVoiture().getIdCategorie().equals("") ? " and id_categorie ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdCategorie().toString()): "") : "")
                + (annonce.getVoiture().getIdModele() != null ? (!annonce.getVoiture().getIdModele().equals("") ? " and id_modele ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdModele().toString()): "") : "")
                + (annonce.getVoiture().getIdTransmission() != null ? (!annonce.getVoiture().getIdTransmission().equals("") ? " and id_transmission ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdTransmission().toString()): "") : "")
                + (annonce.getVoiture().getIdEtat() != null ? (!annonce.getVoiture().getIdEtat().equals("") ? " and id_etat ="+ FonctionUtils.toStringBase(annonce.getVoiture().getIdEtat().toString()): "") : "")
                + (annonce.getVoiture().getNom() != null ? (!annonce.getVoiture().getNom().equals("") ? " and upper(nom) like upper("+ FonctionUtils.toStringBase("%"+annonce.getVoiture().getNom()+"%")+")": "") :"")
                + (annonce.getVoiture().getDescription() != null ? (!annonce.getVoiture().getDescription().equals("") ? " and upper(description) like upper("+ FonctionUtils.toStringBase("%"+annonce.getVoiture().getDescription()+"%")+")": "") :"")
                +")"
                ;
        System.out.println(query);
        return entityManager.createNativeQuery(query, Annonce.class).getResultList();
    }

    @Override
    public List<Object> getStatParCategorie() {
        String query = "Select v.id_categorie, c.nom as categorie, count(a.id) as nombre " +
                "from Annonce a " +
                " join voiture v on a.id_voiture=v.id " +
                " join categorie c on c.id=v.id_categorie " +
                " where " +
                " group by v.id_categorie, c.nom " ;
        return entityManager.createNativeQuery(query).getResultList();
    }
}
