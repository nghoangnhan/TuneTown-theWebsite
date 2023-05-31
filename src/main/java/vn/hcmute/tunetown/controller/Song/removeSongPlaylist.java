package vn.hcmute.tunetown.controller.Song;

import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.model.Playlist;
import vn.hcmute.tunetown.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/removeSongPlaylist"})
public class removeSongPlaylist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String songId = req.getParameter("songId");
        Integer iSongId = Integer.parseInt(songId);

        SongDAO songDAO = new SongDAO();
        Song song = songDAO.getSong(iSongId);

        song.setSongStatus(1);
        songDAO.updateSong(song);

    }
}
