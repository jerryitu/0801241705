package mg.finance.apiv.annonce.annonce;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import mg.finance.utils.FonctionUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Repository
public class AnnonceDAOImpl implements AnnonceDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public String uploadFile(MultipartFile file, String encoded, String fileName) throws IOException {
        BlobId blobId = BlobId.of("project-050224.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();

        Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource("project-050224-firebase-adminsdk-2yt78-94f1b1ac6a.json").getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        if(encoded!=null&&!encoded.equals("")){
            System.out.println("type base64");
            System.out.println(encoded);
            //byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            byte[] decodedBytes = Base64.getDecoder().decode(encoded.trim().substring("data:image/jpeg;base64,".length()));
            storage.create(blobInfo, decodedBytes);
        } else if(file!=null){
            System.out.println("type file");
            storage.create(blobInfo, file.getBytes());
        }
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
