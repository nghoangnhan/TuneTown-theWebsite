package vn.hcmute.tunetown.controller.Playlist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Playlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.*;

@WebServlet(urlPatterns = {"/loadPlaylists"})
public class loadPlaylistsServlet extends HttpServlet {
    PlaylistDAO playlistDAO = new PlaylistDAO();
    List<Playlist> listPlaylist;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        listPlaylist = playlistDAO.getAllPlaylistByUserId(GlobalUser.globalUserId);

        PrintWriter out = resp.getWriter();

        for(Playlist p : listPlaylist) {
            out.println("          <div class=\"my-playlist-item\"\n" +
                    "               id=\"playlist-id-" + p.getPlaylistId()+ "\""+ "onclick=\"loadPlaylistSongs()\">\n" +
                    "\n" + p.getPlaylistName() +
                    "          </div>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listPlaylist = playlistDAO.getAllPlaylistByUserId(GlobalUser.globalUserId);

        JSONArray jsonArray = new JSONArray();

        for(Playlist playlist : listPlaylist) {
            JSONObject jsonPlaylist = new JSONObject();
            jsonPlaylist.put("playlistId", playlist.getPlaylistId());
            jsonPlaylist.put("playlistName", playlist.getPlaylistName());

            jsonArray.put(jsonPlaylist);
        }

        System.out.println(jsonArray);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(jsonArray.toString());
    }
}
