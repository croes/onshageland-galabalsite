package be.onshageland.galabal.bus;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static be.onshageland.galabal.OfyService.ofy;

public class AddBusRegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int numberOfPersons = Integer.parseInt(req.getParameter("numberOfPersonsBus"));

            List<BusRegistrationEntry> registrationEntries = new ArrayList<>();
            for (int i = 1; i <= numberOfPersons; i++) {
                String firstName = req.getParameter("firstNameBus" + i);
                String lastName = req.getParameter("lastNameBus" + i);
                String email = req.getParameter("emailBus" + i);
                BusRegistrationEntry.PaymentMethod paymentMethod = BusRegistrationEntry.PaymentMethod.parseString(req.getParameter("paymentMethodBus" + i));

                if (firstName == null ||
                        lastName == null ||
                        email == null ||
                        paymentMethod == null) {
                    throw new IllegalArgumentException("One of the parameters is invalid!");
                }
                BusRegistrationEntry busRegistrationEntry = new BusRegistrationEntry(
                        firstName,
                        lastName,
                        email,
                        paymentMethod
                );

                registrationEntries.add(busRegistrationEntry);
            }


            ofy().save().entities(registrationEntries).now();

            req.getRequestDispatcher("../successBus.html").forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("../failure.html").forward(req, resp);
        }

    }
}
