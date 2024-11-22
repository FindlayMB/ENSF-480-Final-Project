package FlightSystem.objects;

/**
 * Interface that objects implement such that when
 * inserting objects into db it takes the object and calls its
 * toQuery function.
 * 
 * @author Findlay Brown
 */
public interface ToQuery {
    public String toQuery();
}
