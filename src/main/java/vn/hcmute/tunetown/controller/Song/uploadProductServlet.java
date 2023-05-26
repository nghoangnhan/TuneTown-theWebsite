package vn.hcmute.tunetown.controller.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/uploadProduct"})
public class uploadProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        out.println(
                "<div class=\"content-form\">\n"+
                        "<!-- title form  -->\n"+
                        "<div class=\"wrap-header-form\">\n"+
                        "<h1 class=\"header-form\">Upload Song</h1>\n"+
                        "</div>\n"+
                        "<div class=\"wrap-form\">\n"+
                        "<form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">\n"+
                        "<h1 class=\"input_title\">Song Name</h1>\n"+
                        "<input class =\"input-song-name\" name=\"songName\" type=\"text\" placeholder=\"Your song name\" required/>\n"+
                        "<h1 class=\"input_title\">Song Image</h1>\n"+
                        "<div class=\"input-file-container\">\n"+
                        "<input class=\"input-btn\" name=\"songImage\" type=\"file\" placeholder=\"Input PNG,JPG,...\" accept=\"image/png, image/gif, image/jpeg\" onclick=\"uploadImage()\" required/>\n"+
                        "</div>\n"+
                        "<div class=\"input_title\">Song</div>\n"+
                        "<div class=\"input-file-container\">   \n"+

                        "<input class=\"input-btn\" name=\"songData\" type=\"file\" placeholder=\"Input mp3,mp4...\" accept=\".mp4,.mp3,audio/*\" onclick=\"uploadImage()\" required/>\n"+
                        "</div>\n"+
                        "<div class=\"submit-btn\">\n"+
                        "<input type=\"submit\"  />\n"+
                        "</div>\n"+
                        "</form>\n"+
                        "</div>\n"+
                        "</div>"

        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}