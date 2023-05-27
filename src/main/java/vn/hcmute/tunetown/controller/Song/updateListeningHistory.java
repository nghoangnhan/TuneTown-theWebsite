package vn.hcmute.tunetown.controller.Song;

import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Song;
import vn.hcmute.tunetown.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = {"/updateListeningHistory"})
public class updateListeningHistory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //update song's amount of listens
        SongDAO songDAO = new SongDAO();

        Integer songId = Integer.parseInt(req.getParameter("songId"));
        Song song = songDAO.getSong(songId);

        song.setAmountOfListens(song.getAmountOfListens() + 1);
        songDAO.updateAmountOfListens(song);


        //update user listening history
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(GlobalUser.globalUserId);
        List<Song> history = userDAO.getUserListeningHistory(GlobalUser.globalUserId);
        history.add(song);

        for(Song s: history) {
            System.out.println(s.getSongId());
        }

        user.setHistory(history);

        UserDAO.update(user);
    }
}
