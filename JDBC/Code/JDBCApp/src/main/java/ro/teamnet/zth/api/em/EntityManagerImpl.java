package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandra.Gaman on 7/13/2017.
 */



public class EntityManagerImpl implements  EntityManager {
    private QueryBuilder createQueryBuilder(String tableName,List<ColumnInfo> columns, Condition condition,
                                            QueryType type){
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        if(condition != null) queryBuilder.addCondition(condition);
        queryBuilder.setQueryType(type);
        return queryBuilder;
    }

    private <T> List<T> getResults(String query,List<ColumnInfo> columns, Class<T> entityClass){
        Connection connection = null;
        connection = DBManager.getConnection(connection);
        ArrayList<T> list = new ArrayList<T>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                T instance = entityClass.newInstance();
                for(ColumnInfo ci: columns){
                    Field f = instance.getClass().getDeclaredField(ci.getColumnName());
                    f.setAccessible(true);
                    f.set(instance,EntityUtils.castFromSqlType(resultSet.getObject(ci.getDbColumnName()), f.getType()));
                }
                list.add(instance);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private <T> void insertHelper(T entity,Long id, String tableName, Connection connection) throws SQLException {
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        for(ColumnInfo ci : columns){
            if(ci.isId()){
                id = id;
                ci.setValue(id);
            }
            else{
                setValueToColumn(ci, entity);
            }
        }
        QueryBuilder queryBuilder = createQueryBuilder(tableName, columns, null, QueryType.INSERT);
        try(Statement statement = connection.createStatement()){
            statement.execute(queryBuilder.createQuery());
        } catch (SQLException e) {
            throw  new SQLException();
        }
    }
    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        if(id == null) return null;
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Id.class);
        Condition condition = new Condition();
        for(ColumnInfo ci : columns) {
            if(ci.isId()) {
                condition.setColumnName(ci.getDbColumnName());
                condition.setValue(id);
            }
        }
        QueryBuilder queryBuilder = createQueryBuilder(tableName, columns, condition, QueryType.SELECT);
        List<T> list = getResults(queryBuilder.createQuery(), columns, entityClass);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    private String getMaxQuery(String columnName, String tableName){
        StringBuilder s = new StringBuilder("select max(");
        s.append(columnName);
        s.append(") from ");
        s.append(tableName);
        return s.toString();
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        Connection connection = null;
        connection = DBManager.getConnection(connection);
        try(Statement statement = connection.createStatement()){
            List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
            ColumnInfo cinfo = new ColumnInfo();
            cinfo.setDbColumnName(columnIdName);
            columns.add(cinfo);
            ResultSet resultSet = statement.executeQuery(getMaxQuery(columnIdName,tableName));
            Long max;
            if(resultSet.next()) {
                max = resultSet.getLong("max(" + columnIdName + ")");
                return max+1L;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> Object insert(T entity) {
        Connection connection = null;
        connection = DBManager.getConnection(connection);
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entity.getClass(), Id.class);
        Long id = getNextIdVal(tableName,fields.get(0).getAnnotation(Id.class).name());
        try {
            insertHelper(entity, id, tableName, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findById(entity.getClass(), id);
    }

    private <T> void setValueToColumn(ColumnInfo ci, T entity){
        try {
            Field f = entity.getClass().getDeclaredField(ci.getColumnName());
            f.setAccessible(true);
            ci.setValue(f.get(entity));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        Long id = null;
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        QueryBuilder queryBuilder = createQueryBuilder(tableName, columns, null, QueryType.SELECT);
        List<T> list = getResults(queryBuilder.createQuery(), columns, entityClass);
        return list;
    }

    @Override
    public <T> T update(T entity) {
        Connection connection;
        connection = DBManager.getConnection(null);
        Long id = null;
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entity.getClass(), Id.class);
        for(ColumnInfo ci : columns){
            setValueToColumn(ci, entity);
        }
        Condition condition = new Condition();
        for(ColumnInfo ci : columns) {
            if(ci.isId()) {
                condition.setColumnName(ci.getDbColumnName());
                try {
                    fields.get(0).setAccessible(true);
                    condition.setValue(fields.get(0).get(entity));
                    id = (Long) condition.getValue();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        QueryBuilder queryBuilder = createQueryBuilder(tableName, columns, condition, QueryType.UPDATE);
        try(Statement statement = connection.createStatement()){
            statement.execute(queryBuilder.createQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T) findById(entity.getClass(), id );
    }

    @Override
    public void delete(Object entity) {
        Connection connection;
        connection = DBManager.getConnection(null);
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entity.getClass(), Id.class);
        for(ColumnInfo ci : columns){
            setValueToColumn(ci, entity);
        }
        Condition condition = new Condition();
        for(ColumnInfo ci : columns) {
            if(ci.isId()) {
                condition.setColumnName(ci.getDbColumnName());
                try {
                    fields.get(0).setAccessible(true);
                    condition.setValue(fields.get(0).get(entity));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        QueryBuilder queryBuilder = createQueryBuilder(tableName, columns, condition, QueryType.DELETE);
        try(Statement statement = connection.createStatement()){
            statement.execute(queryBuilder.createQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Condition> conditions = new ArrayList<Condition>();
        for(Map.Entry<String, Object> entry : params.entrySet()){
            Condition condition = new Condition();
            condition.setColumnName(entry.getKey());
            condition.setValue(entry.getValue());
            conditions.add(condition);
        }
        QueryBuilder queryBuilder = createQueryBuilder(tableName, columns, null, QueryType.SELECT);
        for (Condition condition : conditions) {
            queryBuilder.addCondition(condition);
        }
        List<T> list = getResults(queryBuilder.createQuery(), columns, entityClass);
        return list;
    }

    public List<Employee> findEmployeesFromDep(String departmentName){
        StringBuilder sb = new StringBuilder("select ");
        for(ColumnInfo ci : EntityUtils.getColumns(Employee.class)){
            sb.append(ci.getDbColumnName() + ", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(" from employees natural join departments ");
        sb.append("where lower(department_name) like '%");
        sb.append(departmentName.toLowerCase());
        sb.append("%'");
        List<Employee> list = getResults(sb.toString(), EntityUtils.getColumns(Employee.class), Employee.class);
        return  list;
    }

    public <T> boolean transactionInsert(List<T> entities){
        if(entities.isEmpty())
            return true;
        Connection connection = null;
        connection = DBManager.getConnection(connection);
        try {
            connection.setAutoCommit(false);
            String tableName = EntityUtils.getTableName(entities.get(0).getClass());
            List<Field> fields = EntityUtils.getFieldsByAnnotations(entities.get(0).getClass(), Id.class);
            for (int i=0; i < entities.size(); i++) {
                T entity = entities.get(i);
                fields.get(0).setAccessible(true);
                Long id = (Long) fields.get(0).get(entity);
                insertHelper(entity, id, tableName, connection);
            }
            connection.commit();

        } catch (SQLException | IllegalAccessException e) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("the transaction couldn't be completed");
            return false;
        }
        return true;
    }
}
