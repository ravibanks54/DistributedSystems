import java.rmi.Remote;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public interface AirportInterface extends Remote {
    public Airport[] getAirports(double latitude, double longitude) throws java.rmi.RemoteException;     //Return array of data structures
}
