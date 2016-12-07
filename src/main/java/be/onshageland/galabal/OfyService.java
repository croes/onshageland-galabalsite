package be.onshageland.galabal;

import be.onshageland.galabal.bus.BusRegistrationEntry;
import be.onshageland.galabal.diner.DinerRegistrationEntry;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
    static {
        factory().register(BusRegistrationEntry.class);
        factory().register(DinerRegistrationEntry.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}