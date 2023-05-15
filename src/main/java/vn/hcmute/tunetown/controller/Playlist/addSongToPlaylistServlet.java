package vn.hcmute.tunetown.controller.Playlist;

import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.model.Playlist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class addSongToPlaylistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "view/home.jsp";

        Integer playlistId = (Integer) req.getAttribute("playlist-id-");  // playlist-id-${playlistId}

        PlaylistDAO playlistDAO = new PlaylistDAO();
        Playlist currentPlayist = playlistDAO.getPlaylistById(playlistId);


    }
}
