package vn.hcmute.tunetown.controller.Playlist;

import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.model.Playlist;
import vn.hcmute.tunetown.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addSongToPlaylist"})
public class addSongToPlaylistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer songId = Integer.parseInt(req.getParameter("songId"));
        Integer playlistId = Integer.parseInt(req.getParameter("playlistId"));

        PlaylistDAO playlistDAO = new PlaylistDAO();
        SongDAO songDAO = new SongDAO();

        Song song = songDAO.getSong(songId);

        Playlist playlist =  playlistDAO.getPlaylistById(playlistId);
        playlist.getPlaylistSongs().add(song);

        playlistDAO.modifyPlaylist(playlist);
        playlistDAO.modifyPlaylist(playlist);
    }
}
