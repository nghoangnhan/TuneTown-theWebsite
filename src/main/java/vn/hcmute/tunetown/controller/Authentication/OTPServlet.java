package vn.hcmute.tunetown.controller.Authentication;

import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/view/OTP"})
public class OTPServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/forgetPassword.jsp";
        String message = null;

        String email = req.getParameter("email");
        String emailCheck = UserDAO.checkUserByEmail(email);
        try{
            if (emailCheck == null){
                message = "Email does not exist!";
            }
            else{
                SendMail.sendMail(email);
                message = "An OTP code has been sent to your email, please check it!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        req.setAttribute("message", message);
        req.setAttribute("email", email);
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
