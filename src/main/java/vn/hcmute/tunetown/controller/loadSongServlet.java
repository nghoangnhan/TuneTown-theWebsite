package vn.hcmute.tunetown.controller;

import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Playlist;
import vn.hcmute.tunetown.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/loadSong"})
public class loadSongServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/home.jsp";

        try {
            SongDAO songDAO = new SongDAO();
            List<Song> listSong = songDAO.getAllSongs();
            req.setAttribute("listSong", listSong);

            PlaylistDAO playlistDAO = new PlaylistDAO();
            List<Playlist> listPlaylist = playlistDAO.getAllPlaylistByUserId(GlobalUser.globalUserId);
            req.setAttribute("listPlaylist", listPlaylist);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
