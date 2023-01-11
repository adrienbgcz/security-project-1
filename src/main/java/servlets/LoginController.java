package servlets;

import database.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import metier.User;
import utils.IpChecker;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("login")) {
                    String loginCookieToSet = cookie.getValue();
                    request.setAttribute("loginCookie", loginCookieToSet);
                    String ipAddress = null;
                    try {
                        ipAddress = IpChecker.getIp();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("IP ADDRESS");
                    System.out.println(ipAddress);
                    request.setAttribute("ipAdress", ipAddress);

                    Cookie cookie2 = new Cookie("ipAddress", ipAddress);
                    cookie.setMaxAge(60*60*24);
                    response.addCookie(cookie2);

                }
            }
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = "";
        String password = "";
        String actualIpAddress = null;
        try {
            actualIpAddress = IpChecker.getIp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //Création d'un cookie

        try {
            login = request.getParameter("login");
            password = request.getParameter("password");


            User user = null;
            System.out.println("LOGIN : " + login);
            System.out.println("PASSWORD : " + password);

            DatabaseConnector dbc = new DatabaseConnector();
            List<User> users = dbc.getListUsersConnection(login, password);
            System.out.println("USERS : " + users);

            Cookie loginCookie = new Cookie("login", login);
            loginCookie.setMaxAge(60*60*24);
            response.addCookie(loginCookie);

            if(users.size() <= 0) {
                int tentatives = 1;

                Cookie tentativesCookie = new Cookie("tentatives", Integer.toString(tentatives));
                loginCookie.setMaxAge(60*60*24);


                Cookie[] cookies = request.getCookies();

                if(cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("tentatives")) {
                            tentativesCookie.setValue(cookie.getValue());
                        }
                    }
                }
                if(cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("ipAddress")) {
                            if (actualIpAddress.equals(cookie.getValue())) {
                                int actualValue = Integer.parseInt(tentativesCookie.getValue());
                                tentativesCookie.setValue(Integer.toString(actualValue + 1));
                            }
                        }
                    }
                }
                request.setAttribute("tentatives", tentativesCookie.getValue());

                response.addCookie(tentativesCookie);
                doGet(request, response);
            } else {
                request.setAttribute("nom", users.get(0).getNom());
                doGet(request, response);
            }
            /*if(users != null) {
                users.get(0);
                request.setAttribute("nom", user.getNom());
                doGet(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
            }*/

        } catch(Exception e) {
            System.out.println(e);
        }

    }
}
