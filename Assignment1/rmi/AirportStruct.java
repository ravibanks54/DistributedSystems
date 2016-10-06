import AirportData.AirportDataProto;

import java.io.Serializable;

/**
 * Created by ravibhankharia on 10/5/16.
 */
public class AirportStruct implements Serializable{
    String code;
    String name;
    String state;
    double lat;
    double lon;
    double dist;

    public AirportStruct(AirportDataProto.Airport airport, double dist) {
        this.code = airport.getCode();
        this.name = airport.getName();
        this.state = airport.getState();
        this.lat = airport.getLat();
        this.lon = airport.getLon();
        this.dist = dist;
    }

    public String toString(){
        return "code=" + code + ", name=" + name + ", state=" + state + " distance: " + Math.round(dist) + "miles";
    }
}
