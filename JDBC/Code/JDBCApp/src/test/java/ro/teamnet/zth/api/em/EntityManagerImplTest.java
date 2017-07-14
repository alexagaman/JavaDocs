package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Alexandra.Gaman on 7/13/2017.
 */
public class EntityManagerImplTest{
    @Test
    public void testFindById() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Department d = manager.findById(Department.class, 270L);
        assertEquals(d.getDepartmentName(),"Payroll");
        assertTrue(d.getLocation().equals(1700L));
        assertTrue(d.getId().equals(270L));
        assertNull(manager.findById(Department.class, 290L));
    }
    @Test
    public void testGetNextIdVal() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Long id = manager.getNextIdVal("departments","department_id");
        assertTrue(id.equals(274L));
    }

    @Test
    public void testInsert() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Department instance = new Department();
        instance.setDepartmentName("ZeroToHero");
        instance.setLocation(1400L);
        instance = (Department) manager.insert(instance);
        assertEquals(instance.getDepartmentName(),"ZeroToHero");
        assertTrue(instance.getLocation().equals(1400L));
        assertTrue(instance.getId().equals(280L));
    }
    @Test
    public void testFindAll() {
        EntityManagerImpl manager = new EntityManagerImpl();
        List<Department> departments = manager.findAll(Department.class);
        assertEquals(departments.size(), 30);
    }
    @Test
    public void testUpdate() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Department instance = new Department();
        instance.setId(273L);
        instance.setDepartmentName("ZeroToHero");
        instance.setLocation(1700L);
        instance = (Department) manager.update(instance);
        assertEquals(instance.getDepartmentName(),"ZeroToHero");
        assertTrue(instance.getLocation().equals(1700L));
        assertTrue(instance.getId().equals(273L));
    }

    @Test
    public void testDelete() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Department instance = new Department();
        instance.setId(282L);
        manager.delete(instance);
        assertEquals(manager.findAll(Department.class).size(), 36);
    }

    @Test
    public void testFindByParams() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("department_name", "ZeroToHero");
        List<Department> departments = manager.findByParams(Department.class,params);
        assertEquals(departments.size(), 7);

        params.put("location_id", 1700L);
        departments = manager.findByParams(Department.class,params);
        assertEquals(departments.size(), 2);
    }

    @Test
    public void testFindEmployeesFromDep(){
        EntityManagerImpl manager = new EntityManagerImpl();
        assertEquals(manager.findEmployeesFromDep("A").size(), 53);
    }

    @Test
    public void testTransactionInsert(){
        List<Department> list = new ArrayList<Department>();
        Department instance = new Department();
        instance.setId(255L);
        instance.setDepartmentName("transaction");
        instance.setLocation(1400L);
        list.add(instance);
        instance = new Department();
        instance.setId(257L);
        instance.setDepartmentName("transaction");
        instance.setLocation(1400L);
        list.add(instance);
        EntityManagerImpl manager = new EntityManagerImpl();
        assertTrue(manager.transactionInsert(list));
        assertFalse(manager.transactionInsert(list));

    }

}
