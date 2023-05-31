package vn.hcmute.tunetown.controller.User;

import vn.hcmute.tunetown.DAO.GenreDAO;
import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Genre;
import vn.hcmute.tunetown.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/submitSelectedGenres"})
public class submitSelectedGenres extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] genreIds = request.getParameterValues("genreIds[]");


        // Convert the genreIds array to a list
        List<String> selectedGenreIds = new ArrayList<>();
        if (genreIds != null) {
            for (String genreId : genreIds) {
                selectedGenreIds.add(genreId);
            }
        }
        request.setAttribute("selectedGenreIds", selectedGenreIds);
        // Use the selectedGenreIds list as needed
        // For example, you can create a List<Genre> and populate it based on the selected genre IDs

        List<Genre> listGenre = new ArrayList<>();
        GenreDAO genreDAO = new GenreDAO();
        for (String genreId : selectedGenreIds) {
            // Retrieve Genre object based on genreId and add it to the list
            Genre genre = genreDAO.findGenreById(Integer.valueOf(genreId));
            listGenre.add(genre);
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(GlobalUser.globalUserId);
        user.setFavoriteGenre(listGenre);
        UserDAO.update(user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
