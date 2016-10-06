import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public interface PlaceInterface extends Remote {
    public void findPlace(String placename, String state) throws RemoteException;  //Create data structure as return type
}
