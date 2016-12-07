package be.onshageland.galabal.diner;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class DinerRegistrationEntry {

    public enum Food {
        MEAT("Vlees"),
        FISH("Vis"),
        VEGI("Vegi");

        private String displayString;

        Food(String aDisplayString) {
            displayString = aDisplayString;
        }

        @Override
        public String toString() {
            return displayString;
        }

        public static Food parseString(String aString) {
            switch(aString){
                case "MEAT": return MEAT;
                case "FISH": return FISH;
                case "VEGI": return VEGI;
                default: return null;
            }
        }
    }

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Food food;
    private boolean takesBus;

    private DinerRegistrationEntry(){
        this.firstName = "N/A";
        this.lastName = "N/A";
        this.emailAddress = "N/A";
        this.food = Food.MEAT;
        this.takesBus = false;
    }

    public DinerRegistrationEntry(String firstName,
                                  String lastName,
                                  String emailAddress,
                                  Food food,
                                  boolean takesBus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.food = food;
        this.takesBus = takesBus;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public Food getFood() {
        return food;
    }

    public boolean isTakesBus() {
        return takesBus;
    }
}
