package servlets;

import database.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import metier.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Recherche", value = "/Recherche")
public class RechercheController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = "";
        try {
            id = request.getParameter("id");
            User user = null;
            System.out.println("id : " + id);
            DatabaseConnector dbc = new DatabaseConnector();
            List<User> users = dbc.getListUsers(id);
            System.out.println(users);

            request.setAttribute("nom", users.get(0).getNom());
            doGet(request, response);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
