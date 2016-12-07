<%@ page import="java.util.List" %>
<%@ page import="static com.googlecode.objectify.ObjectifyService.ofy" %>
<%@ page import="be.onshageland.galabal.diner.DinerRegistrationEntry" %><%--
  Created by IntelliJ IDEA.
  User: Glenn
  Date: 16/01/2016
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inschrijvingen</title>
</head>
<body>

<h1>Registraties</h1>
<ul>
<%
    List<DinerRegistrationEntry> registrationEntries = ofy().load().type(DinerRegistrationEntry.class).list();
    for(int i=0; i < registrationEntries.size(); i++) {
        DinerRegistrationEntry entry = registrationEntries.get(i);
        %>
    <li>
        <ul>
            <li>ID: <%=entry.getId()%></li>
            <li>Voornaam: <%=entry.getFirstName()%></li>
            <li>Achternaam: <%=entry.getLastName()%></li>
            <li>Email: <%=entry.getEmailAddress()%></li>
            <li>Hoofdgerecht: <%=entry.getFood().toString()%></li>
            <li>Bus: <%=entry.isTakesBus() ? "JA" : "NEE"%></li>
        </ul>
    </li>
    <%}
%>
</ul>

</body>
</html>
