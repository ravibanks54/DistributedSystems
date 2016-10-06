import PlaceData.PlaceDataProto;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class PlaceStruct {
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
}