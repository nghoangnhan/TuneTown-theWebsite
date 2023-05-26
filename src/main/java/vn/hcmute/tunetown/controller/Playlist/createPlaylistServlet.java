package vn.hcmute.tunetown.controller.Playlist;

import com.google.gson.Gson;
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

@WebServlet(urlPatterns = {"/createPlaylist"})
public class createPlaylistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "/view/home.jsp";

        PlaylistDAO playlistDAO = new PlaylistDAO();
        Playlist playlist = new Playlist();

        playlist.setPlaylistName("New playlist");
        playlist.setUserId(GlobalUser.globalUserId);
        playlist.setPlaylistType("Personal");

        playlistDAO.addPlaylist(playlist);

        getServletContext().getRequestDispatcher("/loadPlaylists").forward(req, resp);
    }
}
