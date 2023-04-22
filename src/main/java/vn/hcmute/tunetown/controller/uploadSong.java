package vn.hcmute.tunetown.controller;



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
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
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
import java.util.Base64;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class uploadSong extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String url = "/view/upSong.html";

        String songName = req.getParameter("songName");

        Part imagePart = req.getPart("songImage");
        String imageName = songName;
        InputStream imageInputStream = imagePart.getInputStream();
        byte[] imageByte = imageInputStream.readAllBytes();
        String songPoster = Base64.getEncoder().encodeToString(imageByte);


        String songData = "https://www.youtube.com/watch?v=HGsEq3dp0l8&list=RDHGsEq3dp0l8&start_radio=1";

        Song newSong = new Song(songName, songPoster, songData, 0, 0);
        SongDAO songDAO = new SongDAO();
        songDAO.uploadSong(newSong);


//        try {
//            GGDriveConnection.main();
//        } catch (GeneralSecurityException e) {
//            throw new RuntimeException(e);
//        }

        FileInputStream serviceAccount;

        {
            try {
                String path = "D:\\Hocki2_Nam3\\OOSE\\Project\\Main\\TuneTown\\src\\main\\webapp\\tunetowntest-e968a-firebase-adminsdk-vu7bk-37fd0625ea.json";
                serviceAccount = new FileInputStream(path);
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://tunetowntest-e968a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .setStorageBucket("tunetowntest-e968a.appspot.com")
                        .build();

                FirebaseApp.initializeApp(options);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference().child("users");

                // Add user data to the database
//                String email = req.getParameter("email");
//                String name = req.getParameter("name");
//                String email = "lhlam.lehoang@gmail.com";
//                String name = "HoangLam";
//                User user = new User(email,name);
//                Map<String, String> userData = new HashMap<>();
//                userData.put("email", email);
//                userData.put("name", name);
//                ref.push().setValueAsync(userData);
//                req.setAttribute("user",user);


                // Upload Image to Firebase Storage
                Part filePart = req.getPart("songImage");
                String fileName = filePart.getSubmittedFileName();
                InputStream fileContent = filePart.getInputStream();
                try {
                    FirebaseApp app = FirebaseApp.getInstance();
                    Storage storage = StorageClient.getInstance(app).bucket("tunetowntest-e968a.appspot.com").getStorage();

                    BlobInfo blobInfo = BlobInfo.newBuilder("tunetowntest-e968a.appspot.com", "images/"+fileName)
                            .setContentType(filePart.getContentType())
                            .build();
                    // Upload the file to Firebase Storage
                    storage.create(blobInfo, fileContent,
                            Storage.BlobWriteOption.userProject("tunetowntest-e968a"),
                            Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));
                    // Close the input stream
                    fileContent.close();
                } catch (IOException e) {
                    // Handle IO exception
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    // Handle any other exception
                    throw new RuntimeException(e);
                }

                // Upload MP3 file to storage
                Part filePart2 = req.getPart("songData");
                String fileName2 = filePart2.getSubmittedFileName();
                InputStream fileContent2 = filePart2.getInputStream();
                try {
                    FirebaseApp app = FirebaseApp.getInstance();
                    Storage storage = StorageClient.getInstance(app).bucket("tunetowntest-e968a.appspot.com").getStorage();

                    BlobInfo blobInfo = BlobInfo.newBuilder("tunetowntest-e968a.appspot.com", "audios/"+fileName2)
                            .setContentType(filePart2.getContentType())
                            .build();
                    // Upload the file to Firebase Storage
                    storage.create(blobInfo, fileContent2,
                            Storage.BlobWriteOption.userProject("tunetowntest-e968a"),
                            Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));
                    // Close the input stream
                    fileContent2.close();
                } catch (IOException e) {
                    // Handle IO exception
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    // Handle any other exception
                    throw new RuntimeException(e);
                }



            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
