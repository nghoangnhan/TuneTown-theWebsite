package vn.hcmute.tunetown.controller.User;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import vn.hcmute.tunetown.DAO.GenreDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/loadGenres"})
public class loadGenre extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenreDAO genreDAO = new GenreDAO();
        List<Genre> genreList = genreDAO.getFavoriteGenresByUserId(GlobalUser.globalUserId);
        List<Genre> allGenres = genreDAO.getAllGenres();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("genreList",genreList);
        jsonObject.put("allGenres",allGenres);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonArray.toString());
    }
}
