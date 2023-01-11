package servlets;

import database.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import metier.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InscriptionController", value = "/InscriptionController")
public class InscriptionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*A ne pas utiliser*/

        /*response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Première connexion </title>");
        out.println("<body>Mauvaise config </body>");
        out.println("</html");*/

        this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int age = 0;
        boolean error = false;
        String errorType = "";

        String nom = request.getParameter("nom");

        String prenom = request.getParameter("prenom");

        String email = request.getParameter("email");

        String password = request.getParameter("password");
        System.out.println("Password : " + password);

        String confirm = request.getParameter("confirm");
        System.out.println("Confirm : " + confirm);


        try {
            age = Integer.parseInt(request.getParameter("age"));
            System.out.println("Age : " + age);
        } catch(Exception e) {
           System.out.println("Valeur non gérée");
           errorType = "Valeur non-convertible";
           error = true;
        }

        if(age > 100) {
            error = true;
            errorType = "Valeur non-comprise dans les intervals définis";
        }

        if(error) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } else {
            User user = new User(nom, prenom, age, email, password);
            DatabaseConnector dbc = new DatabaseConnector();
            dbc.getUsers();
            dbc.createUser();
            doGet(request, response);
        }

    }
}
