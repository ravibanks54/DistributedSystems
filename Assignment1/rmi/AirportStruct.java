import AirportData.AirportDataProto;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class AirportStruct {
    String code;
    String name;
    double lat;
    double lon;
    double dist;

    public AirportStruct(AirportDataProto.Airport airport, double dist) {
        this.code = airport.getCode();
        this.name = airport.getName();
        this.lat = airport.getLat();
        this.lon = airport.getLon();
        this.dist = dist;
    }

    public String toString(){
        return "Code: " + code + ", Name: " + name + ", Distance: " + dist;
    }
}
