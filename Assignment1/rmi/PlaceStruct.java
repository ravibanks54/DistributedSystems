import PlaceData.PlaceDataProto;

import java.io.Serializable;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class PlaceStruct implements Serializable{
    String name;
    String state;
    double lat;
    double lon;

    public PlaceStruct(PlaceDataProto.Place place){
        this.name = place.getName();
        this.state = place.getState();
        this.lat = place.getLat();
        this.lon = place.getLon();
    }

    public String toString(){
        return "City: " + name + " State: " + state + "Lat, Lon: " + lat + ", " + lon;
    }
}
