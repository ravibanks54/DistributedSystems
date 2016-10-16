import java.rmi.registry.Registry;
import static java.rmi.registry.LocateRegistry.getRegistry;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class Client {
    static Registry registry;       //Registry variable to hold the RMI Registry. Declared static to prevent
                                    //garbage collection
    public static void main(String args[]) {
        try {
            if (args.length < 2) {      //Not enough arguments
                System.err.println("usage: java SampleClient -h rmiregistryserver -p registry_port city state \n");
                System.exit(1);
            }
            String host = "localhost";  //Set default host as localhost
            int port = 1099;            //Set default port as 1099
            String city = "";
            String state = "";

            for (int i = 0; i < args.length; i++) {     //Parse all flags
                if (args[i].equalsIgnoreCase("-h")) {   //Check if host is specified. If so overwrite host string
                    if (args.length <= i + 1) {         //Check if the flag is specified without an argument
                        System.err.println("usage: java SampleClient -h rmiregistryserver -p registry_port city state \n");
                        System.exit(1);
                    }
                    host = args[i + 1];     //Set the hostname from the arguments
                } else if (args[i].equalsIgnoreCase("-p")) {    //Check if port is specified. If so overwrite port int
                    if (args.length <= i + 1) {
                        System.err.println("usage: java SampleClient -h rmiregistryserver -p registry_port city state \n");
                        System.exit(1);
                    }
                    port = Integer.parseInt(args[i + 1]);   //Set the port from the arguments
                }
            }
            //Set the city and state from the arguments depending on the number of flags specified
            if (args.length == 2){
                city = args[0];
                state = args[1];
            }else if (args.length == 4){
                city = args[2];
                state = args[3];
            }else if (args.length == 6){
                city = args[4];
                state = args[5];
            }
            registry = getRegistry(port);       //Create the RMI Registry with the given port
            String urlPlaces = "//" + host + ":" + port + "/Places";    //Set the lookup url
            System.out.println("looking up " + urlPlaces);
            //PlaceInterface place = (PlaceInterface) Naming.lookup(urlPlaces);
            PlaceInterface place = (PlaceInterface) registry.lookup(urlPlaces);     //Initialize the interface


                // call the remote method and print the return
            PlaceStruct placeStruct = place.findPlace(city, state);     //Search for the specified city and state
            if (placeStruct == null){
                System.out.println("Place not found - Either place does not exist or error retrieving Places file");
                return;
            } else {
                System.out.println(placeStruct.toString());
            }

            //port--;
            String urlAirports = "//" + host + ":" + port + "/Airports";        //Form the lookup url
            System.out.println("looking up " + urlAirports);
            //AirportInterface airport = (AirportInterface) Naming.lookup(urlAirports);
            AirportInterface airport = (AirportInterface) registry.lookup(urlAirports);     //Initialize the Airports interface
            // Call the remote method and print the return
            AirportStruct[] airportStructs = airport.getAirports(placeStruct.lat, placeStruct.lon);
            if(airportStructs == null){
                System.out.println("Error retrieving Airport data");
                return;
            }
            for (int i = 0; i < airportStructs.length; i++){

                System.out.println(airportStructs[i].toString());       //Output the five closes airports
            }

        } catch(Exception e) {
            System.out.println("Client exception: " + e);

        }
    }
}
