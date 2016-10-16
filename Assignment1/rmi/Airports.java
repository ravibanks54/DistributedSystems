import AirportData.AirportDataProto;
import PlaceData.PlaceDataProto;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class Airports extends UnicastRemoteObject implements AirportInterface{

    AirportDataProto.AirportList airportList;

    protected Airports() throws RemoteException {
        System.out.println("New instance of Airports created");
        File file = new File("./Assignment1/places/airports-proto.bin");
        byte[] fileData = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileData);
            airportList = AirportDataProto.AirportList.parseFrom(fileData);

        } catch (InvalidProtocolBufferException e) {
            System.out.println("Invalid File.");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (IOException e1) {
            System.out.println("Error Reading The File.");
        }
    }

    @Override
    public AirportStruct[] getAirports(double latitude, double longitude){

        if(airportList == null){        //return null if the file for airports does not exist
            return null;
        }

        latitude = Math.toRadians(latitude);        //convert lat and long to radians
        longitude = Math.toRadians(longitude);

        List<AirportStruct> airportStructs = new ArrayList<>();     //container to store airports in sorted order

        for(int x = 0; x < 5; x++){
            airportStructs.add(x, new AirportStruct(airportList.getAirport(x),Integer.MAX_VALUE));
        }

        for (int i = 0; i < airportList.getAirportCount(); i++){
            double listLat = Math.toRadians(airportList.getAirport(i).getLat());
            double listLon = Math.toRadians(airportList.getAirport(i).getLon());

            //calculate distance between input and airport
            double d = Math.abs(60 * 1.1507794 * Math.toDegrees(Math.acos(Math.sin(latitude)*Math.sin(listLat) + Math.cos(latitude)*Math.cos(listLat)*Math.cos(listLon-longitude))));

            //compare distance with the current five shortest distances
            //if shorter, put into correct spot in top five
            for(int j = 4; j >= 0; j--){

                if(d < airportStructs.get(j).dist){
                    if(j==0){
                        airportStructs.add(j, new AirportStruct(airportList.getAirport(i),d));
                    }
                    else if(d < airportStructs.get(j-1).dist){
                        continue;
                    }
                    else{
                        airportStructs.add(j, new AirportStruct(airportList.getAirport(i),d));
                    }
                }

            }
        }

        airportStructs = new ArrayList<>(airportStructs.subList(0,5));      //take top 5 closest airports

        for(int y = 4; y >= 0; y--){
            if(airportStructs.get(y).dist == Integer.MAX_VALUE){
                airportStructs.remove(y);
            }
        }

        return airportStructs.toArray(new AirportStruct[airportStructs.size()]);

    }

}