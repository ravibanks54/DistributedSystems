import java.rmi.Naming;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class PlaceServer {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("usage: java SampleServer rmi_port");
            System.exit(1);
        }
        // Create and install a security manager
/*
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());
*/
        try {
            // first command-line argument is the port of the rmiregistry
            int port = Integer.parseInt(args[0]);
            String url = "//localhost:" + port + "/Sample";
            System.out.println("binding " + url);
            Naming.rebind(url, new Sample());
            // Naming.rebind("Sample", new Sample());
            System.out.println("server " + url + " is running...");
        }
        catch (Exception e) {
            System.out.println("Sample server failed:" + e.getMessage());
        }
    }
}
