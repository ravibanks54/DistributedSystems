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

    protected Places() throws RemoteException {
        System.out.println("New instance of Places created");
    }

    @Override
    public void findPlace(String placename, String state) throws java.rmi.RemoteException{
        File file = new File("./Assignment1/places/airports-proto.bin");
        byte[] fileData = new byte[(int) file.length()];
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(fileData);
                for (int i = 0; i < fileData.length; i++) {
                    System.out.print((char)fileData[i]);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found.");
                e.printStackTrace();
            }
            catch (IOException e1) {
                System.out.println("Error Reading The File.");
                e1.printStackTrace();
            }
            PlaceDataProto.Place place = PlaceDataProto.Place.parseFrom(fileData);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }



    }
}
