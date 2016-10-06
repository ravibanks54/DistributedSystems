import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class Places extends UnicastRemoteObject implements PlaceInterface{
    protected Places() throws RemoteException {

    }

    @Override
    public void findPlace(String placename, String state) {

    }
}
