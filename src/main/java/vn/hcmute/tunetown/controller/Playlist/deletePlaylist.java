package vn.hcmute.tunetown.controller.Playlist;

import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.model.Playlist;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deletePlaylist"})
public class deletePlaylist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer playlistId = Integer.parseInt(req.getParameter("playlistId"));
        PlaylistDAO playlistDAO = new PlaylistDAO();
        Playlist currentPlaylist = playlistDAO.getPlaylistById(playlistId);

        playlistDAO.deletePLaylist(currentPlaylist);
    }
}
