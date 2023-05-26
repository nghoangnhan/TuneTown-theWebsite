package vn.hcmute.tunetown.controller.Authentication;

import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = {"/view/register"})
public class registerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "/view/register.jsp";
        String message = null;

//        String fullName = req.getParameter("fullName");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        String emailCheck = UserDAO.checkUserByEmail(email);
        String usernameCheck = UserDAO.checkUserByUsername(username);
        User user = new User(username,null,email,password,2,1,null,"https://firebasestorage.googleapis.com/v0/b/tunetowntest-e968a.appspot.com/o/images%2Favatar.png?alt=media&token=%5B101");
        try {
            if(emailCheck != null && emailCheck.equals(email)){
                message = "Email existed!";
            }
            else if(usernameCheck != null && usernameCheck.equals(username)){
                message = "Username existed!";
            }
            else if (!email.contains("@gmail.com")){
                message = "Wrong email format!";
            }
            else{
                if (password.equals(rePassword)){
                    UserDAO.insert(user);
                    message = "Register successfully!";
                }
                else{
                    message = "Password does not match!";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
