package be.onshageland.galabal.bus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class DownloadBusServlet extends HttpServlet {

    private static final String SEPERATOR = ";";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BusRegistrationEntry> registrationEntries = ofy().load().type(BusRegistrationEntry.class).list();

        resp.setContentType("text/csv");
        resp.setHeader("Content-Disposition", "attachment; filename=\"inschrijvingen-galabal-bus.csv\"");

        try {
            OutputStream outputStream = resp.getOutputStream();
            String outputResult = "\"Identificatienummer\"" + SEPERATOR +
                    "\"Voornaam\"" + SEPERATOR +
                    "\"Naam\"" + SEPERATOR +
                    "\"Email\"\n";
            outputStream.write(outputResult.getBytes());
            for (BusRegistrationEntry entry : registrationEntries) {
                String line = "\"" + entry.getId() + "\"" + SEPERATOR +
                        "\"" + entry.getFirstName() + "\"" + SEPERATOR +
                        "\"" + entry.getLastName() + "\"" + SEPERATOR +
                        "\"" + entry.getEmailAddress() + "\"\n";
                outputStream.write(line.getBytes());
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
