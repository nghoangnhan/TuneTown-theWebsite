package vn.hcmute.tunetown.controller.Playlist;


import org.json.JSONArray;
import org.json.JSONObject;
import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.model.Playlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/editNamePlaylist"})
public class editNamePlaylist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playlistId = req.getParameter("playlistId");

        PlaylistDAO playlistDAO = new PlaylistDAO();
        Playlist playlist = playlistDAO.getPlaylistById(Integer.valueOf(playlistId));

        String playlistName = req.getParameter("playlistName");

        playlist.setPlaylistName(playlistName);

        playlistDAO.modifyPlaylist(playlist);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
