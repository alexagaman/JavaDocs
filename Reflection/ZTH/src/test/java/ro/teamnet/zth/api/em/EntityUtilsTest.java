package ro.teamnet.zth.api.em;

import junit.framework.TestCase;
import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra.Gaman on 7/12/2017.
 */
public class EntityUtilsTest extends TestCase {
    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetColumns() {
        List<ColumnInfo> list = EntityUtils.getColumns(Department.class);
        List<ColumnInfo> results = new ArrayList<ColumnInfo>();
        ColumnInfo aux = new ColumnInfo();
        aux.setId(true);
        aux.setColumnType(Long.class);
        aux.setColumnName("id");
        aux.setDbColumnName("Department_id");
        results.add(aux);
        aux = new ColumnInfo();
        aux.setId(false);
        aux.setColumnType(String.class);
        aux.setColumnName("departmentName");
        aux.setDbColumnName("Department_Name");
        results.add(aux);
        aux = new ColumnInfo();
        aux.setId(true);
        aux.setColumnType(String.class);
        aux.setColumnName("location");
        aux.setDbColumnName("Location");
        results.add(aux);
        for(ColumnInfo ci : list){
            System.out.println(ci.getColumnType());
            assertTrue(list.contains(ci));
        }
    }

    @Test
    public void testCastFromSqlType() {
       assertEquals(EntityUtils.castFromSqlType(new BigDecimal(5), Integer.class).getClass(), Integer.class);
       assertEquals(EntityUtils.castFromSqlType(new BigDecimal(5), Long.class).getClass(), Long.class);
       assertEquals(EntityUtils.castFromSqlType(new BigDecimal(5), Double.class).getClass(), Double.class);
       assertEquals(EntityUtils.castFromSqlType(new BigDecimal(5), Float.class).getClass(), Float.class);
    }

    @Test
    public void testGetFieldsByAnnotations() {
        List<Field> list = EntityUtils.getFieldsByAnnotations(Department.class, Column.class);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testGetSqlValue(){
        Object o = EntityUtils.getSqlValue(new Department());
        assertEquals(o , Long.class);
        o = EntityUtils.getSqlValue(new String(" "));
        assertEquals(o ," ");
    }

}
