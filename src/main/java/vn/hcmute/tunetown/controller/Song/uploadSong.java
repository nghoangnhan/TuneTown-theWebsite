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
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import vn.hcmute.tunetown.DAO.GenreDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.connection.GGDriveConnection;
import vn.hcmute.tunetown.model.Genre;
import vn.hcmute.tunetown.model.Song;
import com.google.cloud.storage.Storage;
import vn.hcmute.tunetown.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class uploadSong extends HttpServlet {
    private Song song;
    private String downloadUrlData;
    private String downloadUrlImage;
    private String songName;
    private User user;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        songName = req.getParameter("songName");
        String url = "/loadSong";
        UserDAO userDAO = new UserDAO();

        InputStream serviceAccount;

        {
            try {
                // Check if FirebaseApp is already initialized
                FirebaseApp app;
                if (FirebaseApp.getApps().isEmpty()) {
                    serviceAccount = getServletContext().getResourceAsStream("/WEB-INF/tunetowntest-e968a-firebase-adminsdk-vu7bk-37fd0625ea.json");
                    FirebaseOptions options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .setDatabaseUrl("https://tunetowntest-e968a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                            .setStorageBucket("tunetowntest-e968a.appspot.com")
                            .build();
                    app = FirebaseApp.initializeApp(options);
                } else {
                    app = FirebaseApp.getInstance();
                }

                String appCheckToken = Arrays.toString(FirebaseAuth.getInstance(app)
                        .createCustomToken("tunetowntest-e968a")
                        .getBytes());

                // Upload Image to Firebase
                Part filePart = req.getPart("songImage");
                String fileName = filePart.getSubmittedFileName();
                InputStream fileContent = filePart.getInputStream();
                try {
                    Storage storage = StorageClient.getInstance(app).bucket("tunetowntest-e968a.appspot.com").getStorage();

                    BlobInfo blobInfo = BlobInfo.newBuilder("tunetowntest-e968a.appspot.com", "images/" + fileName)
                            .setContentType(filePart.getContentType())
                            .setMetadata(ImmutableMap.of("firebaseStorageDownloadTokens", appCheckToken))
                            .build();

                    // Upload the file to Firebase Storage
                    storage.create(blobInfo, fileContent,
                            Storage.BlobWriteOption.userProject("tunetowntest-e968a"),
                            Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));

                    // Construct the download URL manually
                    downloadUrlImage = "https://firebasestorage.googleapis.com/v0/b/" +
                            "tunetowntest-e968a.appspot.com" +
                            "/o/" +
                            "images%2F" + fileName +
                            "?alt=media" ;


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
                    Storage storage = StorageClient.getInstance(app).bucket("tunetowntest-e968a.appspot.com").getStorage();

                    BlobInfo blobInfo = BlobInfo.newBuilder("tunetowntest-e968a.appspot.com", "audios/" + fileName2)
                            .setContentType(filePart2.getContentType())
                            .setMetadata(ImmutableMap.of("firebaseStorageDownloadTokens", appCheckToken))
                            .build();

                    // Upload the file to Firebase Storage
                    storage.create(blobInfo, fileContent2,
                            Storage.BlobWriteOption.userProject("tunetowntest-e968a"),
                            Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));

                    // Encode the token using Base64 encoding
                    String encodedFilePath = URLEncoder.encode("audios/" + fileName2, "UTF-8");
                    downloadUrlData = "https://firebasestorage.googleapis.com/v0/b/" +
                            "tunetowntest-e968a.appspot.com" +
                            "/o/" +
                            encodedFilePath +
                            "?alt=media";

                    String token = UUID.randomUUID().toString();
                    String encodedToken = URLEncoder.encode(token, "UTF-8");
                    downloadUrlData = downloadUrlData + "&token=" + encodedToken;

                    System.out.println(downloadUrlData);


                } catch (Exception e) {
                    // Handle any other exception
                    throw new RuntimeException(e);
                } finally {
                    fileContent2.close();
                }
                GenreDAO genreDAO = new GenreDAO();
                String genreName = req.getParameter("genreName");
                Genre genre = genreDAO.getGenreByName(genreName);
                // Add to SQL
                song = new Song(songName, userDAO.getUserById(GlobalUser.globalUserId), downloadUrlImage, downloadUrlData, 0, genre, 0);
                SongDAO songDAO = new SongDAO();
                songDAO.uploadSong(song);

            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + url);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
