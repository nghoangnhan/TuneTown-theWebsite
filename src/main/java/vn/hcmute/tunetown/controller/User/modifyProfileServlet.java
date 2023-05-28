package vn.hcmute.tunetown.controller.User;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@WebServlet(urlPatterns = {"/modifyProfile"})
@MultipartConfig
public class modifyProfileServlet extends HttpServlet {
    private String downloadUrlAvatar;
    private User userUpdate;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        String message = null;

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(GlobalUser.globalUserId);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String birthdate = req.getParameter("birthdate");

        int gender = Integer.parseInt(req.getParameter("gender"));
        String email  = req.getParameter("email");
        String userBio = req.getParameter("userBio");

        System.out.println(username);

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
                Part filePart = req.getPart("userAvatar");
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
                    downloadUrlAvatar = "https://firebasestorage.googleapis.com/v0/b/" +
                            "tunetowntest-e968a.appspot.com" +
                            "/o/" +
                            "images%2F" + fileName +
                            "?alt=media";

                    // Check if user has an existing avatar
                    if (user.getUserAvatar() != null && !user.getUserAvatar().isEmpty()) {
                        // Get the file name of the existing avatar
                        String existingFileName = user.getUserAvatar().substring(user.getUserAvatar().lastIndexOf("/") + 1);

                        // URL-decode the file name
                        existingFileName = URLDecoder.decode(existingFileName, "UTF-8");

                        // Extract the file name from the decoded string
                        String fileName2 = existingFileName.substring(existingFileName.lastIndexOf("/") + 1);
                        String fileName3 = fileName2.substring(0, fileName2.indexOf("?"));
                        // Delete the existing avatar from Firebase Storage
                        try {
                            if(!fileName3.equals("avatar.png") && !downloadUrlAvatar.equals("https://firebasestorage.googleapis.com/v0/b/tunetowntest-e968a.appspot.com/o/images%2F?alt=media"))
                            {
                                storage = StorageClient.getInstance(app).bucket().getStorage();
                                BlobId blobId = BlobId.of("tunetowntest-e968a.appspot.com", "images/" + fileName3);
                                storage.delete(blobId);
                            }
                        } catch (Exception e) {
                            // Handle any exception during deletion
                            throw new RuntimeException(e);
                        }
                    }

                } catch (Exception e) {
                    // Handle any other exception
                    throw new RuntimeException(e);
                } finally {
                    fileContent.close();
                }

                // Update user
                userUpdate = new User(user.getUserID(), username, birthdate, email, password, gender, user.getRole(), userBio, downloadUrlAvatar);
                if (downloadUrlAvatar.equals("https://firebasestorage.googleapis.com/v0/b/tunetowntest-e968a.appspot.com/o/images%2F?alt=media"))
                {
                    userUpdate.setUserAvatar(user.getUserAvatar());
                }
                String emailCheck = UserDAO.checkUserByEmail(email);
                String usernameCheck = UserDAO.checkUserByUsername(username);

                System.out.println(userUpdate.getUserBio());
                UserDAO.update(userUpdate);
                System.out.println("updated");

            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            }
        }
//        req.setAttribute("user", userUpdate);
        // Redirect to a different page to prevent continuous updates
        req.setAttribute("message", message);
        resp.sendRedirect(req.getContextPath() + url);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
