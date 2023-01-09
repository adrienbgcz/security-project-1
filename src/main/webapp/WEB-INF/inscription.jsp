<%--
  Created by IntelliJ IDEA.
  User: adrienbogacz
  Date: 09/01/2023
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page d'inscription</title>
</head>
<body>
Veuillez saisir les informations d'inscription :
    <form method="post" action="inscription"> <%--action=mm nom que url--%>
        <p>Nom</p>
        <input type="text" name="nom">
        <p>Prénom</p>
        <input type="text" name="prenom">
        <p>Age</p>
        <input type="text" name="age">
        <p>Email</p>
        <input type="email" name="email">
        <p>Password</p>
        <input type="password" name="password">
        <p>Confirm password</p>
        <input type="password" name="confirm">

        <p><input type="submit" value="Valider"></p>
    </form>
</body>
</html>
