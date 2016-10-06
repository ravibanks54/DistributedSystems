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
            /*for (int i = 0; i < fileData.length; i++) {
                System.out.print((char)fileData[i]);
            }*/
            airportList = AirportDataProto.AirportList.parseFrom(fileData);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }
    }

    @Override
    public AirportStruct[] getAirports(double latitude, double longitude){

        latitude = Math.toRadians(latitude);
        longitude = Math.toRadians(longitude);

        List<AirportStruct> airportStructs = new ArrayList<>();

        for (int i = 0; i < airportList.getAirportCount(); i++){
            double listLat = Math.toRadians(airportList.getAirport(i).getLat());
            double listLon = Math.toRadians(airportList.getAirport(i).getLon());

            double d = Math.abs(60 * Math.toDegrees(Math.acos(Math.sin(latitude)*Math.sin(listLat) + Math.cos(latitude)*Math.cos(listLat)*Math.cos(listLon-longitude))));

            for(int j = 4; j >= 0; j--){
                if(!(airportStructs.size() < j+1 || d < airportStructs.get(j).dist)){
                    airportStructs.add(j+1, new AirportStruct(airportList.getAirport(i),d));
                    break;
                }
                if(airportStructs.isEmpty() && j == 0){
                    airportStructs.add(0, new AirportStruct(airportList.getAirport(i),d));
                    break;
                }
            }
        }

        airportStructs = new ArrayList<>(airportStructs.subList(0,5));

        return airportStructs.toArray(new AirportStruct[airportStructs.size()]);

    }

}