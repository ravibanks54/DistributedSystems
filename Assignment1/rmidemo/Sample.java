import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;

// this is the class with remote methods

public class Sample
  extends UnicastRemoteObject
  implements SampleInterface {

    private int a;

    public Sample() throws RemoteException {
	System.out.println("New instance of Sample created");
	a = 1;
    }

    public String invert(String m) throws RemoteException {
        // return input message with characters reversed
        System.out.println("invert("+m+") a=" + a);
        return new StringBuffer(m).reverse().toString();
    }
}
