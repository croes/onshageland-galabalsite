<%@ page import="be.onshageland.galabal.bus.BusRegistrationEntry" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static be.onshageland.galabal.OfyService.ofy" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Galabal Ons Hageland</title>
</head>
<body>

<%
    try {
        int numberOfPersons = Integer.parseInt(request.getParameter("numberOfPersonsBus"));

        List<BusRegistrationEntry> registrationEntries = new ArrayList<BusRegistrationEntry>();
        for (int i = 1; i <= numberOfPersons; i++) {
            String firstName = request.getParameter("firstNameBus" + i);
            String lastName = request.getParameter("lastNameBus" + i);
            String email = request.getParameter("emailBus" + i);

            if (firstName == null ||
                    lastName == null ||
                    email == null) {
                throw new IllegalArgumentException("One of the parameters is invalid!");
            }
            BusRegistrationEntry busRegistrationEntry = new BusRegistrationEntry(
                    firstName,
                    lastName,
                    email,
                    BusRegistrationEntry.PaymentMethod.CASH
            );

            registrationEntries.add(busRegistrationEntry);
        }


        ofy().save().entities(registrationEntries).now();
%>


<h1>Reservatie geslaagd</h1>
<p>Uw reservatie voor de busrit van het avondfeest is succesvol geregistreerd.</p>

<h2>Details reservatie(s):</h2>

<table>
    <thead>
    <tr>
        <th>Nr.</th>
        <th>Voornaam</th>
        <th>Naam</th>
        <th>Email</th>
    </tr>
    </thead>
    <%
        int i = 1;
        int nbOfPersonsCash = 0;
        int nbOfPersonsTransfer = 0;
        String transferComment = "Bus -";
        for (BusRegistrationEntry entry : registrationEntries) {
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
            </tr>
    <%
        i++;
    }
    %>
</table>

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
