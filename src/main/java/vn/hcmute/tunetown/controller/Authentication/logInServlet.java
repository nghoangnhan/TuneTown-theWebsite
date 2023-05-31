package vn.hcmute.tunetown.controller.Authentication;

import vn.hcmute.tunetown.DAO.GenreDAO;
import vn.hcmute.tunetown.DAO.UserDAO;
//import vn.hcmute.tunetown.connection.HibernateConnection;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Genre;
import vn.hcmute.tunetown.model.User;

import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/login"})
public class logInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/login.jsp";
        String message = null;

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = UserDAO.getUserByEmailAndPassword(email, password);
            if(user != null) {
                GlobalUser.globalUserId = user.getUserID();
                GlobalUser.globalUserName = user.getUserName();

                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", user);


                url = "/loadSong";
            }
            else{
                message = "Password are not correct";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Invalid email";
        }
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
