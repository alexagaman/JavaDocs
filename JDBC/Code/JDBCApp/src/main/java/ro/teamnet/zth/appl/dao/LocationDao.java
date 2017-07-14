package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandra.Gaman on 7/13/2017.
 */
public class LocationDao {
    private EntityManager manager = new EntityManagerImpl();

    public Location findById(Long id){
        return manager.findById(Location.class, id);
    }

    public  Long getNextIdVal(){
        String tableName = EntityUtils.getTableName(Location.class);
        String columnName = null;
        List<ColumnInfo> list = EntityUtils.getColumns(Location.class);
        for(ColumnInfo ci : list){
            if(ci.isId()){
                columnName = ci.getDbColumnName();
            }
        }
        return manager.getNextIdVal(tableName, columnName);
    }

    public  Location insert(Location entity){
        return (Location) manager.insert(entity);
    }

    public  List<Location> findAll(){
        return manager.findAll(Location.class);
    }

    public  Location update(Location entity){
        return manager.update(entity);
    }

    public   void delete(Location entity){
        manager.delete(entity);
    }

    public  List<Location> findByParams(Map<String, Object> params){
        return manager.findByParams(Location.class, params);
    }
}
