<%--
  Created by IntelliJ IDEA.
  User: adrienbogacz
  Date: 09/01/2023
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="post" action="login">
        <p>Login</p>
        <input type="email" name="login">
        <p>Password</p>
        <input type="password" name="password">

        <p><input type="submit" value="Valider"></p>
    </form>

    <p>${!empty nom ? "Connected" : "Disconnected"}</p>
    <p>Cookie value : ${loginCookie}</p>
    <p>Ip address : ${ipAdress}</p>
    <p>Tentatives manquées: ${empty tentatives ? "0" : tentatives}</p>
<!-- bloquer l'utilisateur après X tentatives -->
</body>
</html>
