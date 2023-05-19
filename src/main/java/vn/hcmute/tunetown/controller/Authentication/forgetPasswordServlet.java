package vn.hcmute.tunetown.controller.Authentication;

import vn.hcmute.tunetown.DAO.UserDAO;
import vn.hcmute.tunetown.SendMail;
import vn.hcmute.tunetown.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/view/forgetPassword"})
public class forgetPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/forgetPassword.jsp";
        String message2 = null;
        String message1 = null;


        String email = req.getParameter("email");
        String OTP = req.getParameter("OTPcode");
        String emailCheck = UserDAO.checkUserByEmail(email);
        String OTPcheck = String.valueOf(SendMail.otp);

        User user = UserDAO.getUserByEmail(email);
        try{
            if (emailCheck == null){
                message1 = "Email does not exist or you have not generated OTP yet!";
                System.out.println(email);
            }
            else{
                if(OTP != null){
                    if(OTP.equals(OTPcheck))
                    {
                        message1 = "Your password is: ";
                        message2 = user.getUserPassword();
                    }
                    else{
                        message1 = "OTP is not correct!";
                    }
                }
                else{
                    message1 = "OTP null!";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("message2", message2);
        req.setAttribute("message1", message1);
        req.setAttribute("email", email);
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
