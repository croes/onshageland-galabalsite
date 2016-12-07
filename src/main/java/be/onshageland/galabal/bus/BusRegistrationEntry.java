package be.onshageland.galabal.bus;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class BusRegistrationEntry {

    public enum PaymentMethod {
        TRANSFER("Overschrijving"),
        CASH("Cash");

        private String displayString;

        PaymentMethod(String aDisplayString) {
            displayString = aDisplayString;
        }

        @Override
        public String toString() {
            return displayString;
        }

        public static PaymentMethod parseString(String aString) {
            switch(aString) {
                case "CASH":
                    return CASH;
                case "TRANSFER":
                    return TRANSFER;
                default:
                    return null;
            }
        }
    }

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private PaymentMethod paymentMethod;

    public BusRegistrationEntry() {
        this("N/A", "N/A", "N/A", PaymentMethod.CASH);
    }

    public BusRegistrationEntry(
            String aFirstName,
            String aLastName,
            String aEmail,
            PaymentMethod aPaymentMethod
    ) {
        firstName = aFirstName;
        lastName = aLastName;
        email = aEmail;
        paymentMethod = aPaymentMethod;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getEmailAddress() {
        return email;
    }
}
