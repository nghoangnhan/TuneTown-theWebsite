package vn.hcmute.tunetown.controller.Authentication;

import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/view/changePassword"})
public class changePassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        String message = null;
        User user = userDAO.getUserById(GlobalUser.globalUserId);

        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String reNewPassword = req.getParameter("reNewPassword");

        if(!oldPassword.equals(user.getUserPassword())){
            message = "Old password does not correct!";
        }
        else if (!newPassword.equals(reNewPassword)){
            message = "New password does not match!";
        }
        else{
            message = "Change password successfully!";
            user.setUserPassword(newPassword);
            UserDAO.update(user);
        }
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/view/changePassword.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }



}
