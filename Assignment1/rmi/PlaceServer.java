import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class PlaceServer {
    static Registry registry;
    public static void main(String args[]) {
        if (args.length > 1) {
            System.err.println("usage: java PlaceServer rmi_port");
            System.exit(1);
        }

        int port = 1099;
        try {
            if (args.length == 1) {            // first command-line argument is the port of the rmiregistry
                port = Integer.parseInt(args[0]);
            }
            try {
                registry = LocateRegistry.createRegistry(port);
            }catch (RemoteException re){
                registry = LocateRegistry.getRegistry(port);
            }
            String url = "//localhost:" + port + "/Places";
            System.out.println("binding " + url);
            registry.bind(url, new Places());       //bind to url
            System.out.println("server " + url + " is running...");
        }catch (Exception e) {
            System.out.println("Place server failed");
        }
    }
}
