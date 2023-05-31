package vn.hcmute.tunetown.controller.Song;

import vn.hcmute.tunetown.DAO.GenreDAO;
import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Genre;
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
            UserDAO userDAO = new UserDAO();

            User userLog = userDAO.getUserById(GlobalUser.globalUserId);

            if (loggedUser == null) {
                url = "/view/login.jsp";
            }
            else {
                SongDAO songDAO = new SongDAO();
                List<Song> listSong = songDAO.getAllSongs();
                req.setAttribute("listSong", listSong);

                List<Song> listTop10Song = songDAO.getTop10Songs();
                req.setAttribute("listTop10Song", listTop10Song);


                PlaylistDAO playlistDAO = new PlaylistDAO();
                List<Playlist> listPlaylist = playlistDAO.getAllPlaylistByUserId(loggedUser.getUserID());

                Playlist suggestedPlaylist = playlistDAO.findSuggestedPlaylist(GlobalUser.globalUserId);

                if(suggestedPlaylist != null){
                    listPlaylist.add(suggestedPlaylist);
                }

                GenreDAO genreDAO = new GenreDAO();
                List<Genre> listGenre = genreDAO.getAllGenres();
                req.setAttribute("listGenre", listGenre);


                req.setAttribute("listPlaylist", listPlaylist);
                req.setAttribute("loggedUser", userLog);
                req.setAttribute("userId", loggedUser.getUserID());
                User user = UserDAO.getUserByEmail(loggedUser.getEmail());
                req.setAttribute("avatar", user.getUserAvatar());
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
