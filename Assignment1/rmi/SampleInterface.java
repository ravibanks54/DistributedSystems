import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SampleInterface extends Remote {
	public String invert(String msg) throws RemoteException;
}
