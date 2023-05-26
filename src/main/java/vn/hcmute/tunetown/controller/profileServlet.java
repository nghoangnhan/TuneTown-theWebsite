package vn.hcmute.tunetown.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import org.json.JSONArray;
import org.json.JSONObject;
import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Playlist;
import vn.hcmute.tunetown.model.Song;
import vn.hcmute.tunetown.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@WebServlet(urlPatterns = {"/loadProfile"})
public class profileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(GlobalUser.globalUserId);
//        req.setAttribute("user", user);
//        getServletContext().getRequestDispatcher(url).forward(req,resp);


        PrintWriter out = resp.getWriter();
        out.println(
                "<div class=\"updateInfor-form\">\n"+
                  "<div class=\"wrapper\">\n"+
                    "<div class=\"title\">Update Informations</div>\n"+
                    "<div class=\"form\">\n"+
                      "<div class=\"inputfield image\">\n"+
                        "<div id=\"wrapper-image-input\">\n"+
                          "<img id=\"image-preview\"  src=\""+ user.getUserAvatar() +"\" onclick=\"updateAvatar()\"/>\n"+
                          "<input id=\"image-input\" type=\"file\" name=\"userAvatar\" accept=\"image/*\" class=\"\" />\n"+
                        "</div>\n"+
                      "</div>\n"+
                      "<div class=\"inputfield\">\n"+
                        "<label>Username</label>\n"+
                        "<input value=\""+user.getUserName()+"\" name=\"username\" type=\"text\" class=\"input\" />\n"+
                      "</div>\n"+
                      "<div class=\"inputfield\">\n"+
                        "<label>Password</label>\n"+
                        "<input value=\""+user.getUserPassword()+"\" name=\"password\" type=\"password\" class=\"input\" />\n"+
                      "</div>\n"+
                      "<div class=\"inputfield\">\n"+
                        "<label>Birthday</label>\n"+
                        "<input value=\""+user.getBirthDate()+"\" name=\"birthdate\" type=\"date\" class=\"input\" />\n"+
                      "</div>\n"+
                      "<div class=\"inputfield\">\n"+
                        "<label>Gender</label>\n"+
                        "<div class=\"custom_select\">\n"+
                          "<select name=\"gender\">\n"+
                            "<option value=\"0\">Male</option>\n"+
                            "<option value=\"1\">Female</option>\n"+
                          "</select>\n"+
                        "</div>\n"+
                      "</div>\n"+
                      "<div class=\"inputfield\">\n"+
                        "<label>Email Address</label>\n"+
                        "<input value=\""+user.getEmail()+"\" name=\"email\" type=\"text\" class=\"input\" />\n"+
                      "</div>\n"+
                      "<div class=\"inputfield\">\n"+
                        "<label>Bio</label>\n"+
                        "<input value=\""+user.getUserBio()+"\" name=\"userBio\" class=\"textarea\" />\n"+
                      "</div>\n"+
                        "<input type=\"hidden\" value=\""+user.getRole()+"\" name=\"roles\" class=\"textarea\" />\n"+
                        "<div class=\"inputfield\">\n"+
                        "<input type=\"submit\" value=\"Update\" class=\"btn\" />\n"+
                      "</div>\n"+
                    "</div>\n"+
                  "</div>\n"+
                "</div>"




        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(GlobalUser.globalUserId);
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonUser = new JSONObject();
        jsonUser.put("userId", user.getUserID());
        jsonUser.put("userAvatar", user.getUserAvatar());
        jsonUser.put("userName", user.getUserName());
        jsonUser.put("userPassword", user.getUserPassword());
        jsonUser.put("birthDate", user.getBirthDate());
        jsonUser.put("email", user.getEmail());
        jsonUser.put("userBio", user.getUserBio());
        jsonUser.put("roles", user.getRole());

        jsonArray.put(jsonUser);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonArray.toString());
    }
}
