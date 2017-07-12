package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Alexandra.Gaman on 7/12/2017.
 */
@Table(name = "departments")
public class Department {

    @Id(name = "Department_id")
    private Long id;
    @Column(name = "Department_Name")
    private String departmentName;
    @Column(name = "Location")
    private Location location;

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Location getLocation() {
        return location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass())
            return false;
        Department o = (Department) obj;
        if(id.equals(o.getId()) && departmentName.equals(o.getDepartmentName())
                && location.equals(o.getLocation()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        String s = "{id: ";
        s = s + id +";\n";
        s = s + "DepartmentName: "+departmentName+";\n";
        s = s+ "Location: "+location+"}";
        return s;
    }
}
