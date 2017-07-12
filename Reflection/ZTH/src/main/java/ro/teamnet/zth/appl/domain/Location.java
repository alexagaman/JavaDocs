package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Alexandra.Gaman on 7/12/2017.
 */
@Table(name = "locations")
public class Location {
    @Id(name = "location_id")
    private Long id;
    @Column(name = "Street_Address")
    private String streetAddress;
    @Column(name = "Postal_Code")
    private String postalCode;
    @Column(name = "State_Province")
    private String stateProvince;

    public long getId() {
        return id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass())
            return false;
        Location o = (Location) obj;
        if(id.equals(o.getId()) && streetAddress.equals(o.getStreetAddress()) && postalCode.equals(o.getPostalCode())
            && stateProvince.equals(o.getStateProvince()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        String s = "{id: ";
        s = s + id +";\n"+ "streetAddress: "+streetAddress + ";\n";
        s = s + "postalCode: "+postalCode+";\n";
        s = s+ "stateProvince: "+stateProvince+"}";
        return s;
    }
}
