package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

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
        String nom = request.getParameter("nom");
        System.out.println("Nom : " + nom);
        String prenom = request.getParameter("prenom");
        System.out.println("Prenom : " + prenom);
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println("Age : " + age);
        String email = request.getParameter("email");
        System.out.println("Email : " + email);
        String password = request.getParameter("password");
        System.out.println("Password : " + password);
        String confirm = request.getParameter("confirm");
        System.out.println("Confirm : " + confirm);
    }
}
