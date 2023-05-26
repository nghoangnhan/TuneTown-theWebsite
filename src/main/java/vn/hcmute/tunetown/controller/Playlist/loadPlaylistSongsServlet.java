package vn.hcmute.tunetown.controller.Playlist;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = {"/loadPlaylistSongs"})

public class loadPlaylistSongsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String playlistId = req.getParameter("playlistId");
        Integer iPlaylistId = Integer.parseInt(playlistId);

        UserDAO userDAO = new UserDAO();

        PlaylistDAO playlistDAO = new PlaylistDAO();
        Playlist playlist = playlistDAO.getPlaylistById(iPlaylistId);

        PrintWriter out = resp.getWriter();

        for (Song song : playlist.getPlaylistSongs()) {
            out.println(
                    "        <div class=\"song-item\">\n" +
                            "          <div class=\"song-edit\">\n" +
                            "            <input type=\"checkbox\" class=\"delete-checkbox\" />\n" +
                            "          </div>\n" +
                            "          <div class=\"song-ranking\">1</div>\n" +
                            "          <div class=\"song-img\">\n" +
                            "            <img src=\"" + song.getSongPoster() + "\" alt=\"\" />\n" +
                            "              <div >" + song.getSongData() + "</div>" +

                            "          </div>\n" +
                            "          <div class=\"song-info\">\n" +
                            "            <div class=\"song-info-title\">" + song.getSongName() + "</div>\n" +
                            "            <div class=\"song-info-author\">" + song.getArtists() + "</div>\n" +
                            "          </div>\n" +
                            "          <div class=\"song-genre\">Pop</div>\n" +
                            "          <div class=\"song-view\">1,234,567</div>\n" +
                            "        </div>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playlistId = req.getParameter("playlistId");
        Integer iPlaylistId = Integer.parseInt(playlistId);

        UserDAO userDAO = new UserDAO();

        PlaylistDAO playlistDAO = new PlaylistDAO();
        Playlist playlist = playlistDAO.getPlaylistById(iPlaylistId);

        JSONArray jsonArray = new JSONArray();

        for(Song song : playlist.getPlaylistSongs()) {

            JSONObject jsonSong = new JSONObject();
            jsonSong.put("songId", song.getSongId());
            jsonSong.put("songName", song.getSongName());
            jsonSong.put("songPoster", song.getSongPoster());
            jsonSong.put("songData", song.getSongData());
            jsonSong.put("songArtists", song.getArtists());

            jsonArray.put(jsonSong);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonArray.toString());
    }
}
