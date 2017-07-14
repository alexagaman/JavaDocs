package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandra.Gaman on 7/13/2017.
 */
public class DepartmentDao {
    private  EntityManager manager = new EntityManagerImpl();

    public  Department findById(Long id){
        return manager.findById(Department.class, id);
    }

    public  Long getNextIdVal(){
        String tableName = EntityUtils.getTableName(Department.class);
        String columnName = null;
        List<ColumnInfo> list = EntityUtils.getColumns(Department.class);
        for(ColumnInfo ci : list){
            if(ci.isId()){
                columnName = ci.getDbColumnName();
            }
        }
        return manager.getNextIdVal(tableName, columnName);
    }

    public  Department insert(Department entity){
        return (Department) manager.insert(entity);
    }

    public  List<Department> findAll(){
        return manager.findAll(Department.class);
    }

    public  Department update(Department entity){
        return manager.update(entity);
    }

    public   void delete(Department entity){
        manager.delete(entity);
    }

    public  List<Department> findByParams(Map<String, Object> params){
        return manager.findByParams(Department.class, params);
    }

}
