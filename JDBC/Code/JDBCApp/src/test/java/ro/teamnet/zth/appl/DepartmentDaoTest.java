package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexandra.Gaman on 7/14/2017.
 */
public class DepartmentDaoTest {
    @Test
    public void testFindById() {
        DepartmentDao manager = new DepartmentDao();
        Department d = manager.findById(270L);
        assertEquals(d.getDepartmentName(),"Payroll");
        assertTrue(d.getLocation().equals(1700L));
        assertTrue(d.getId().equals(270L));
        assertNull(manager.findById(290L));
    }
    @Test
    public void testGetNextIdVal() {
        DepartmentDao manager = new DepartmentDao();
        Long id = manager.getNextIdVal();
        assertTrue(id.equals(280L));
    }
    @Test
    public void testInsert() {
        DepartmentDao manager = new DepartmentDao();
        Department instance = new Department();
        instance.setDepartmentName("ZeroToHero2");
        instance.setLocation(1500L);
        instance = manager.insert(instance);
        assertEquals(instance.getDepartmentName(),"ZeroToHero2");
        assertTrue(instance.getLocation().equals(1500L));
        assertTrue(instance.getId().equals(manager.getNextIdVal() - 1));
    }
    @Test
    public void testFindAll() {
        DepartmentDao manager = new DepartmentDao();
        List<Department> departments = manager.findAll();
        assertEquals(departments.size(), 35);
    }
    @Test
    public void testUpdate() {
        DepartmentDao manager = new DepartmentDao();
        Department instance = new Department();
        String name = "ZeroToHero" + 8;
        System.out.println(name);
        instance.setId(274L);
        instance.setDepartmentName(name);
        instance.setLocation(1700L);
        instance = manager.update(instance);
        assertEquals(instance.getDepartmentName(),name);
        assertTrue(instance.getLocation().equals(1700L));
        assertTrue(instance.getId().equals(274L));
    }
    @Test
    public void testDelete() {
        DepartmentDao manager = new DepartmentDao();
        Department instance = new Department();
        instance.setDepartmentName("ZeroToHero2");
        instance.setId(280L);
        instance.setLocation(1500L);
        manager.delete(instance);
        assertEquals(manager.findAll().size(), 35);
    }
    @Test
    public void testFindByParams() {
        DepartmentDao manager = new DepartmentDao();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("department_name", "ZeroToHero");
        List<Department> departments = manager.findByParams(params);
        assertEquals(departments.size(), 6);

        params.put("location_id", 1700L);
        departments = manager.findByParams(params);
        assertEquals(departments.size(), 2);
    }

}
