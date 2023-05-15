package vn.hcmute.tunetown.controller.Song;



//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.googleapis.media.MediaHttpUploader;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.JsonObjectParser;
//import com.google.api.client.json.JsonParser;
//import com.google.api.client.json.JsonParserFactory;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.DriveScopes;
//import com.google.api.services.drive.model.File;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.connection.GGDriveConnection;
import vn.hcmute.tunetown.model.Song;
import com.google.cloud.storage.Storage;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class uploadSong extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        InputStream serviceAccount;

        {
            try {
                serviceAccount = getServletContext().getResourceAsStream("./WEB-INF/tunetowntest-e968a-firebase-adminsdk-vu7bk-37fd0625ea.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://tunetowntest-e968a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .setStorageBucket("tunetowntest-e968a.appspot.com")
                        .build();

                FirebaseApp.initializeApp(options);

                String appCheckToken = Arrays.toString(FirebaseAuth.getInstance()
                        .createCustomToken("tunetowntest-e968a")
                        .getBytes());


                // Upload Image to Firebase
                Part filePart = req.getPart("songImage");
                String fileName = filePart.getSubmittedFileName();
                InputStream fileContent = filePart.getInputStream();
                try {
                    FirebaseApp app = FirebaseApp.getInstance();
                    Storage storage = StorageClient.getInstance(app).bucket("tunetowntest-e968a.appspot.com").getStorage();

                    BlobInfo blobInfo = BlobInfo.newBuilder("tunetowntest-e968a.appspot.com", "images/" + fileName)
                            .setContentType(filePart.getContentType())
                            .setMetadata(ImmutableMap.of("firebaseStorageDownloadTokens", appCheckToken))
                            .build();
                    // Upload the file to Firebase Storage
                    storage.create(blobInfo, fileContent,
                            Storage.BlobWriteOption.userProject("tunetowntest-e968a"),
                            Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));
                } catch (Exception e) {
                    // Handle any other exception
                    throw new RuntimeException(e);
                } finally {
                    fileContent.close();
                }


                // Upload MP3 file to storage
                Part filePart2 = req.getPart("songData");
                String fileName2 = filePart2.getSubmittedFileName();
                InputStream fileContent2 = filePart2.getInputStream();
                try {
                    FirebaseApp app = FirebaseApp.getInstance();
                    Storage storage = StorageClient.getInstance(app).bucket("tunetowntest-e968a.appspot.com").getStorage();

                    BlobInfo blobInfo = BlobInfo.newBuilder("tunetowntest-e968a.appspot.com", "audios/" + fileName2)
                            .setContentType(filePart2.getContentType())
                            .setMetadata(ImmutableMap.of("firebaseStorageDownloadTokens", appCheckToken))
                            .build();
                    // Upload the file to Firebase Storage
                    storage.create(blobInfo, fileContent2,
                            Storage.BlobWriteOption.userProject("tunetowntest-e968a"),
                            Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));
                } catch (Exception e) {
                    // Handle any other exception
                    throw new RuntimeException(e);
                } finally {
                    fileContent2.close();
                }

            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
