package be.onshageland.galabal.diner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static be.onshageland.galabal.OfyService.ofy;

public class DownloadDinerServlet extends HttpServlet {

    private static final String SEPERATOR = ";";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DinerRegistrationEntry> registrationEntries = ofy().load().type(DinerRegistrationEntry.class).list();

        resp.setContentType("text/csv");
        resp.setHeader("Content-Disposition", "attachment; filename=\"inschrijvingen-galabal-diner.csv\"");

        try
        {
            OutputStream outputStream = resp.getOutputStream();
            String outputResult = "\"Identificatienummer\"" + SEPERATOR +
                    "\"Voornaam\"" + SEPERATOR +
                    "\"Naam\"" + SEPERATOR +
                    "\"Email\"" + SEPERATOR +
                    "\"Hoofdgerecht\"" + SEPERATOR +
                    "\"Bus\"\n";
            outputStream.write(outputResult.getBytes());
            for(DinerRegistrationEntry entry : registrationEntries) {
                String line = "\"" + entry.getId() + "\"" + SEPERATOR +
                        "\"" + entry.getFirstName() + "\"" + SEPERATOR +
                        "\"" + entry.getLastName() + "\"" + SEPERATOR +
                        "\"" + entry.getEmailAddress() + "\"" + SEPERATOR +
                        "\"" + entry.getFood().toString() + "\"" + SEPERATOR +
                        "\"" + (entry.isTakesBus() ? "ja" : "nee") + "\"\n";
                outputStream.write(line.getBytes());
            }
            outputStream.flush();
            outputStream.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
