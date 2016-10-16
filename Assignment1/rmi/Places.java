import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import PlaceData.*;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class Places extends UnicastRemoteObject implements PlaceInterface{

    PlaceDataProto.PlaceList placeList;
    protected Places() throws RemoteException {
        System.out.println("New instance of Places created");
        File file = new File("./Assignment1/places/places-proto.bin");
        byte[] fileData = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileData);
            /*for (int i = 0; i < fileData.length; i++) {
                System.out.print((char)fileData[i]);
            }*/
            placeList = PlaceDataProto.PlaceList.parseFrom(fileData);

        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
//            e.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error Reading The File.");
//            e1.printStackTrace();
        }
    }

    @Override
    public PlaceStruct findPlace(String placename, String state) throws java.rmi.RemoteException{

        if(placeList == null){
            return null;
        }

        for (int i = 0; i < placeList.getPlaceCount(); i++){
            if (placeList.getPlace(i).getName().regionMatches(true, 0, placename, 0, placename.length()) && placeList.getPlace(i).getState().equalsIgnoreCase(state)){
                return new PlaceStruct(placeList.getPlace(i));
            }
        }
        return null;

    }
}
