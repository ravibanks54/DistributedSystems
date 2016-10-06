import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class AirportServer {
    static Registry registry;
    public static void main(String args[]) {
        if (args.length > 1) {
            System.err.println("usage: java AirportServer rmi_port");
            System.exit(1);
        }
        // Create and install a security manager
/*
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());
*/
        int port = 1099;
        try {
            // first command-line argument is the port of the rmiregistry
            if (args.length == 1){
                port = Integer.parseInt(args[0]);
            }
            try{
                registry = LocateRegistry.getRegistry(port);
            }catch (RemoteException re){
                registry = LocateRegistry.createRegistry(port);
            }
            //registry = LocateRegistry.getRegistry(port);
            String url = "//localhost:" + port + "/Airports";
            System.out.println("binding " + url);
            registry.bind(url, new Airports());
            //Naming.rebind(url, new Airports());
            System.out.println("server " + url + " is running...");
        }
        catch (Exception e) {
            System.out.println("Airport server failed:" + e.getMessage());
        }
    }
}
