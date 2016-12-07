package be.onshageland.galabal.diner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static be.onshageland.galabal.OfyService.ofy;

public class AddDinerRegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int numberOfPersons = Integer.parseInt(req.getParameter("numberOfPersons"));

            List<DinerRegistrationEntry> registrationEntries = new ArrayList<DinerRegistrationEntry>();
            for (int i = 1; i <= numberOfPersons; i++) {
                String firstName = req.getParameter("firstName" + i);
                String lastName = req.getParameter("lastName" + i);
                String email = req.getParameter("email" + i);
                DinerRegistrationEntry.Food food = DinerRegistrationEntry.Food.parseString(req.getParameter("food" + i));
                boolean takesBus = req.getParameter("bus" + i).equals("true");

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

            req.getRequestDispatcher("../success.html").forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("../failure.html").forward(req, resp);
        }

    }
}
