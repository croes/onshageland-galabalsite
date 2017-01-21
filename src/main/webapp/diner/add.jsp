<%@ page import="be.onshageland.galabal.bus.BusRegistrationEntry" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static be.onshageland.galabal.OfyService.ofy" %>
<%@ page import="be.onshageland.galabal.diner.DinerRegistrationEntry" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Galabal Ons Hageland</title>
</head>
<body>

<%
    try {
        int numberOfPersons = Integer.parseInt(request.getParameter("numberOfPersons"));

        List<DinerRegistrationEntry> registrationEntries = new ArrayList<DinerRegistrationEntry>();
        for (int i = 1; i <= numberOfPersons; i++) {
            String firstName = request.getParameter("firstName" + i);
            String lastName = request.getParameter("lastName" + i);
            String email = request.getParameter("email" + i);
            DinerRegistrationEntry.Food food = DinerRegistrationEntry.Food.parseString(request.getParameter("food" + i));
            boolean takesBus = request.getParameter("bus" + i).equals("true");

            if (firstName == null ||
                    lastName == null ||
                    email == null ||
                    food == null) {
                throw new IllegalArgumentException("One of the parameters is invalid!");
            }
            DinerRegistrationEntry dinerRegistrationEntry = new DinerRegistrationEntry(
                    firstName,
                    lastName,
                    email,
                    food,
                    takesBus
            );

            registrationEntries.add(dinerRegistrationEntry);
        }

        ofy().save().entities(registrationEntries).now();
%>


<h1>Reservatie geslaagd</h1>
<p>Uw reservatie voor het diner is succesvol geregistreerd.</p>

<h2>Details reservatie(s):</h2>

<table>
    <thead>
    <tr>
        <th>Nr.</th>
        <th>Voornaam</th>
        <th>Naam</th>
        <th>Email</th>
        <th>Keuze menu</th>
        <th>Busrit</th>
        <th>Prijs</th>
    </tr>
    </thead>
    <%
        int i = 1;
        int nbOfPersonsTransfer = 0;
        String transferComment = "Diner -";
        for (DinerRegistrationEntry entry : registrationEntries) {
    %>
    <tr>
        <td><%=i%>
        </td>
        <td><%=entry.getFirstName()%>
        </td>
        <td><%=entry.getLastName()%>
        </td>
        <td><%=entry.getEmailAddress()%>
        </td>
        <td><%=entry.getFood().toString()%>
        </td>
        <td><%=entry.isTakesBus() ? "Ja" : "Neen"%>
        </td>
        <td>60,00</td>
    </tr>
    <%
           nbOfPersonsTransfer++;
           transferComment = transferComment + " "
                   + entry.getFirstName().substring(0, 1) + ". " + entry.getLastName();
            i++;
        }
    %>
</table>
    <% if (nbOfPersonsTransfer > 0) { %>
<p>Gelieve <b><%=nbOfPersonsTransfer * 60%>,00 euro over te schrijven</b> op rekeningnummer <b>BE92 3770 1835 1023</b>
    ter attentie van <b>Ons Hageland</b>,
    met mededeling <b><%=transferComment%>
    </b>. (<%=nbOfPersonsTransfer%> personen aan 60,00 euro pp.)</p>
<%
    }
%>

<%
} catch (Exception e) {
%>

<h1>Oops!</h1>

<p>Onze excuses, er iets misgelopen bij het noteren van je reservatie.</p>
<p>Gelieve je reservatie per email door te sturen naar onze senior via <b>senior@onshageland.be</b></p>
        <%
    }
    %>
<p><a href="/index.html">Ga terug naar de website</a></p>
</body>
</html>
