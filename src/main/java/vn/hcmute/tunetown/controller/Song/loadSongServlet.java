package vn.hcmute.tunetown.controller.Song;

import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Playlist;
import vn.hcmute.tunetown.model.Song;
import vn.hcmute.tunetown.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/loadSong"})
public class loadSongServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "/view/home.jsp";
        try {

            // Check whether User has logged already or not
            HttpSession session = req.getSession();
            User loggedUser = (User) session.getAttribute("loggedUser");

            if (loggedUser == null) {
                url = "/view/login.jsp";
            }
            else {
                SongDAO songDAO = new SongDAO();
                List<Song> listSong = songDAO.getAllSongs();
                req.setAttribute("listSong", listSong);

                PlaylistDAO playlistDAO = new PlaylistDAO();
                List<Playlist> listPlaylist = playlistDAO.getAllPlaylistByUserId(loggedUser.getUserID());
                req.setAttribute("listPlaylist", listPlaylist);
                req.setAttribute("username", loggedUser.getUserName());
            }
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
