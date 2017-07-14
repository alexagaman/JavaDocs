package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexandra.Gaman on 7/14/2017.
 */
public class LocationDaoTest {
    @Test
    public void testFindById() {
        LocationDao manager = new LocationDao();
        Location l = manager.findById(1000L);
        assertEquals(l.getStreetAddress(),"1297 Via Cola di Rie");
        assertTrue(l.getCity().equals("Roma"));
        assertTrue(l.getId().equals(1000L));
        assertNull(manager.findById(290L));
    }
    @Test
    public void testGetNextIdVal() {
        LocationDao manager = new LocationDao();
        Long id = manager.getNextIdVal();
        assertTrue(id.equals(3208L));
    }
    @Test
    public void testInsert() {
        LocationDao manager = new LocationDao();
        Location l = new Location();
        l.setStreetAddress("1 Cimitir");
        l.setCity("Tg Ocna");
        l.setStateProvince("Bacau");
        l.setPostalCode("605600");
        l = manager.insert(l);
        assertEquals(l.getStreetAddress(),"1 Cimitir");
        assertTrue(l.getCity().equals("Tg Ocna"));
        assertTrue(l.getId().equals(3208L));
    }
    @Test
    public void testFindAll() {
        LocationDao manager = new LocationDao();
        List<Location> locations = manager.findAll();
        assertEquals(locations.size(), 30);
    }
    @Test
    public void testUpdate() {
        LocationDao manager = new LocationDao();
        Location l = new Location();
        l.setId(3201L);
        l.setStreetAddress("3 Cimitir");
        l.setCity("Tg Ocna");
        l.setStateProvince("Bacau");
        l.setPostalCode("605600");
        l = manager.update(l);
        assertEquals(l.getStreetAddress(),"3 Cimitir");
        assertTrue(l.getCity().equals("Tg Ocna"));
        assertTrue(l.getId().equals(3201L));
    }
    @Test
    public void testDelete() {
        LocationDao manager = new LocationDao();
        Location l = new Location();
        l.setId(3208L);
        l.setStreetAddress("1 Cimitir");
        l.setCity("Tg Ocna");
        l.setStateProvince("Bacau");
        l.setPostalCode("605600");
        manager.delete(l);
        assertEquals(manager.findAll().size(), 29);
    }
    @Test
    public void testFindByParams() {
        LocationDao manager = new LocationDao();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("street_address", "1 Cimitir");
        List<Location> locations = manager.findByParams(params);
        assertEquals(locations.size(), 5);

        params.put("postal_code", "605601");
        locations = manager.findByParams(params);
        assertEquals(locations.size(), 2);
    }

}
