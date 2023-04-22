package vn.hcmute.tunetown.controller;

import vn.hcmute.tunetown.DAO.UserDAO;
//import vn.hcmute.tunetown.connection.HibernateConnection;
import vn.hcmute.tunetown.model.User;

import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/Login"})
public class logInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "/view/login.jsp";
        String message = null;

        UserDAO usersDAO = new UserDAO();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = UserDAO.getUserByEmail(email);

        try {
            if(email.equals(user.getEmail()) && password.equals(user.getUserPassword())){
                url = "/loadSong";
                if (user.getRoles() == 0) {
                    url ="/view/upSong.html";
                }
            }
            else{
                message = "Password are not correct";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Invalid email";
        }

        req.setAttribute("message", message);

//        User user1 = new User();
//        user1.setUserName("Nhan");
//        UserDAO.insert(user1);
//        HibernateConnection connection = new HibernateConnection();
//        connection.saveTest();

        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
