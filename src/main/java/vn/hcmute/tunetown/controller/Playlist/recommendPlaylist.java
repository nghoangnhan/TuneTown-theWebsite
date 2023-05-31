package vn.hcmute.tunetown.controller.Playlist;

import org.json.JSONArray;
import org.json.JSONObject;
import vn.hcmute.tunetown.DAO.GenreDAO;
import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.model.Genre;
import vn.hcmute.tunetown.model.Playlist;
import vn.hcmute.tunetown.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = {"/recommendPlaylist"})
public class recommendPlaylist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genreId = req.getParameter("genreId");
        Integer iGenreId = Integer.parseInt(genreId);

        GenreDAO genreDAO = new GenreDAO();
        Genre genres = genreDAO.getGenreById(iGenreId);

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonPlaylist = new JSONObject();
        jsonPlaylist.put("playlistName", "Recommend Playlist For "+genres.getGenreName());
        jsonArray.put(jsonPlaylist);

        List<Song> listSong;
        SongDAO songDAO = new SongDAO();
        listSong = songDAO.getTop10SongByGenreId(genres);
        for (Song song : listSong){
            JSONObject jsonSong = new JSONObject(); // Create a new object for each song

            jsonSong.put("songId", song.getSongId());
            jsonSong.put("songName", song.getSongName());
            jsonSong.put("songPoster", song.getSongPoster());
            jsonSong.put("songData", song.getSongData());
            jsonSong.put("songArtists", song.getArtists().getUserName());
            jsonSong.put("songGenre", song.getGenre().getGenreName());
            jsonSong.put("songAmoutOfListens", song.getAmountOfListens());


            jsonArray.put(jsonSong);
        }


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonArray.toString());

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String genreId = req.getParameter("genreId");
        Integer iGenreId = Integer.parseInt(genreId);

        GenreDAO genreDAO = new GenreDAO();


        Genre genres = genreDAO.getGenreById(iGenreId);

        List<Song> listSong = new ArrayList<>();
        SongDAO songDAO = new SongDAO();
        listSong = songDAO.getTop10SongByGenreId(genres);


        PrintWriter out = resp.getWriter();

        for (Song song : listSong) {
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
                            "            <div class=\"song-info-author\">" + song.getArtists().getUserName() + "</div>\n" +
                            "          </div>\n" +
                            "          <div class=\"song-genre\">Pop</div>\n" +
                            "          <div class=\"song-view\">1,234,567</div>\n" +
                            "        </div>");
        }
    }

}
