package vn.hcmute.tunetown.controller.Playlist.SuggestPlaylist;

import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.model.Playlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/setPersonal"})
public class setToPersonal extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer playlistId = Integer.parseInt(req.getParameter("playlistId"));

        PlaylistDAO playlistDAO = new PlaylistDAO();
        Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);

        currentPlaylist.setPlaylistType("Personal");
        currentPlaylist.setPlaylistName("Personal Playlist");
        playlistDAO.modifyPlaylist(currentPlaylist);

    }
}
