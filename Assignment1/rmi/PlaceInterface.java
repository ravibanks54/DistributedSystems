import java.rmi.Remote;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public interface PlaceInterface extends Remote {
    public void findPlace(String placename, String state);  //Create data structure as return type
}
