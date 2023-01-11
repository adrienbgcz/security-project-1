package database;

import metier.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    public void getUsers() {

        Statement statement = null;
        Connection connexion =  null;
        ResultSet resultSet = null;

        //établir connexion avec la base

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/users", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //préparer la requête
        try {
           String nom = "toto";
           String prenom = "tutu";
            statement = connexion.createStatement();
            resultSet = statement.executeQuery("SELECT NOM, PRENOM FROM USER WHERE NOM='"+nom+"' AND PRENOM = '"+prenom+"'");
            while(resultSet.next()) {
                 nom = resultSet.getString("NOM");
                 prenom = resultSet.getString("PRENOM");
                 System.out.println( nom+" "+prenom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        //analyser les résultats
    }

    public void createUser() {

        PreparedStatement statement = null; // prends des données entrantes
        Connection connexion =  null;

        //établir connexion avec la base

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/users", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //préparer la requête
        try {
            statement = connexion.prepareStatement("INSERT INTO USER(NOM, PRENOM, AGE, EMAIL, PASSWORD) VALUES (?,?,?,?,?)");
            statement.setString(1, "Dupont");
            statement.setString(2, "Titi");
            statement.setInt(3, 23);
            statement.setString(4, "dupont@gmail.com");
            statement.setString(5, "dupont2022");
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        //analyser les résultats
    }

    public List<User> getListUsers(String id) {
       /* 2' UNION SELECT TABLE_NAME, COLUMN_NAME, FROM information_schema.COLUMNS WHERE TABLE_NAME='user'#     # => pour commenter ce qu'il y a après
       *
       * SELECT NOM, PRENOM FROM USER WHERE ID='2' UNION SELECT NOM, PASSWORD FROM USER #'  ==> METTRE UNION plutot que point-virgule et même nombre d'éléments de chaque côté si select
       * */

        List<User> users = new ArrayList<User>();
        PreparedStatement statement = null;
        Connection connexion = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/users", "root", "root");
        } catch (Exception e) {

        }

        try {
            statement = connexion.prepareStatement("SELECT NOM, PRENOM FROM USER WHERE ID=?");
            statement.setString(1, id);
            /*String query =
            System.out.println(query);*/


            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String nom = resultSet.getString("NOM");
                String prenom = resultSet.getString("PRENOM");
                User user = new User();
                user.setNom(nom);
                user.setPrenom(prenom);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return users;
    }

    public List<User> getListUsersConnection(String login, String password) {
        /* 2' UNION SELECT TABLE_NAME, COLUMN_NAME, FROM information_schema.COLUMNS WHERE TABLE_NAME='user'#     # => pour commenter ce qu'il y a après
         *
         * SELECT NOM, PRENOM FROM USER WHERE ID='2' UNION SELECT NOM, PASSWORD FROM USER #'  ==> METTRE UNION plutot que point-virgule et même nombre d'éléments de chaque côté si select
         * */

        System.out.println("Récup list users");
        List<User> users = new ArrayList<User>();
        PreparedStatement statement = null;
        Connection connexion = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/users", "root", "root");
        } catch (Exception e) {

        }

        try {
            statement = connexion.prepareStatement("SELECT NOM, PRENOM FROM USER WHERE EMAIL=? AND PASSWORD=?");
            statement.setString(1, login);
            statement.setString(2, password);


            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String nom = resultSet.getString("NOM");
                String prenom = resultSet.getString("PRENOM");
                User user = new User();
                user.setNom(nom);
                user.setPrenom(prenom);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return users;
    }
}
