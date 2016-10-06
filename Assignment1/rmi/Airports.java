import java.rmi.RMISecurityException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class Airports extends UnicastRemoteObject implements AirportInterface{
    protected Airports() throws RemoteException {
    }

    @Override
    public Airport[] getAirports(double latitude, double longitude){
        return new Airport[0];
    }

}
