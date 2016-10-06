import java.rmi.Naming;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class Client {
    public static void main(String args[]) {
        try {
            if (args.length < 2) {
                System.err.println("usage: java SampleClient -h rmiregistryserver -p registry_port city state \n");
                System.exit(1);
            }
            String host = "localhost";
            int port = 1099;
            String city = "";
            String state = "";

            for (int i = 0; i < args.length; i++) {     //Whichever order the flags are in, it'll parse them
                if (args[i].equalsIgnoreCase("-h")) {
                    if (args.length <= i + 1) {
                        System.err.println("usage: java SampleClient -h rmiregistryserver -p registry_port city state \n");
                        System.exit(1);
                    }
                    host = args[i + 1];
                } else if (args[i].equalsIgnoreCase("-p")) {
                    if (args.length <= i + 1) {
                        System.err.println("usage: java SampleClient -h rmiregistryserver -p registry_port city state \n");
                        System.exit(1);
                    }
                    port = Integer.parseInt(args[i + 1]);
                }
            }
            if (args.length == 2){
                city = args[0];
                city = args[1];
            }else if (args.length == 4){
                city = args[2];
                state = args[3];
            }else if (args.length == 6){
                city = args[4];
                state = args[5];
            }
            String url = "//" + host + ":" + port + "/Sample";
            System.out.println("looking up " + url);
            SampleInterface sample = (SampleInterface) Naming.lookup(url);

            // args[2] onward are the strings we want to reverse
            for (int i=4; i < args.length; ++i)
                // call the remote method and print the return
                System.out.println(sample.invert(args[i]));
        } catch(Exception e) {
            System.out.println("SampleClient exception: " + e);
        }
    }
}
