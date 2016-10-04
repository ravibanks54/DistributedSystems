import java.rmi.Naming;
import java.rmi.RemoteException;

public class SampleClient  {
    public static void main(String args[]) {
        try {
            if (args.length < 3) {
                System.err.println("usage: java SampleClient registry_host registry_port string... \n");
                System.exit(1);
            }

            int port = Integer.parseInt(args[1]);
            String url = "//" + args[0] + ":" + port + "/Sample";
            System.out.println("looking up " + url);

            SampleInterface sample = (SampleInterface)Naming.lookup(url);

            // args[2] onward are the strings we want to reverse
            for (int i=2; i < args.length; ++i)
                // call the remote method and print the return
                System.out.println(sample.invert(args[i]));
        } catch(Exception e) {
            System.out.println("SampleClient exception: " + e);
        }
    }
}
