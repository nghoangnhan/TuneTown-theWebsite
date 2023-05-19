package vn.hcmute.tunetown.controller.Authentication;

import vn.hcmute.tunetown.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logOut"})
public class logOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/login.jsp";

        try {
            HttpSession session = req.getSession();
            User loggedUser = (User) session.getAttribute("loggedUser");
            if(loggedUser != null) {
                session.invalidate();
            }
            getServletContext().getRequestDispatcher(url).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
