package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandra.Gaman on 7/12/2017.
 */
public class QueryBuilder {
    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;


    public String getValueForQuery(Object value){
        if(value.getClass().getSimpleName().equals("String")){
            String s = "'";
            s += value;
            s += "'";
            return s;
        }
        else if(value.getClass().getSimpleName().equals("Date")){
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date) value)+"','mm-dd-YYYY'";
        }
        return value.toString();
    }

    public QueryBuilder addCondition(Condition condition){
        conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName){
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns){
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType){
        this.queryType = queryType;
        return this;
    }

    private String createSelectQuery(){
        StringBuilder sb = new StringBuilder("select ");
        for(ColumnInfo ci : queryColumns){
            sb.append(ci.getDbColumnName());
            sb.append(", ");
        }
        sb.delete(sb.length()-3, sb.length()-1);
        sb.append(" from ");
        sb.append(tableName);
        if(!conditions.isEmpty()){
            sb.append(" where ");
            for(Condition c : conditions){
                sb.append(c.getColumnName());
                sb.append("=");
                sb.append(c.getValue());
                sb.append(" and ");
            }
            sb.delete(sb.length()-5, sb.length()-1);
        }
        sb.append(" ;");
        return sb.toString();
    }
    private String createDeleteQuery(){
        StringBuilder sb = new StringBuilder("delete from");
        sb.append(tableName);
        if(!conditions.isEmpty()){
            sb.append(" where ");
            for(Condition c : conditions){
                sb.append(c.getColumnName());
                sb.append("=");
                sb.append(c.getValue());
                sb.append(" and ");
            }
            sb.delete(sb.length()-5, sb.length()-1);
        }
        sb.append(" ;");
        return sb.toString();
    }
    private String createUpdateQuery(){
        StringBuilder sb = new StringBuilder("update ");
        sb.append(tableName);
        sb.append(" set ");
        if(!queryColumns.isEmpty()) {
            for (ColumnInfo ci : queryColumns) {
                sb.append(ci.getDbColumnName());
                sb.append("=");
                sb.append(ci.getValue());
                sb.append(", ");
            }
            sb.delete(sb.length() - 3, sb.length() - 1);
        }
        if(!conditions.isEmpty()){
            sb.append(" where ");
            for(Condition c : conditions){
                sb.append(c.getColumnName());
                sb.append("=");
                sb.append(c.getValue());
                sb.append(" and ");
            }
            sb.delete(sb.length()-5, sb.length()-1);
        }
        sb.append(" ;");
        return sb.toString();
    }
    private String createInsertQuery(){
        StringBuilder sb = new StringBuilder("insert into ");
        sb.append(tableName);
        sb.append("(");
        for (ColumnInfo ci : queryColumns) {
            sb.append(ci.getDbColumnName());
            sb.append(", ");
        }
        sb.delete(sb.length() - 3, sb.length() - 1);
        sb.append(") values (");
        for (ColumnInfo ci : queryColumns) {
            sb.append(ci.getValue());
            sb.append(", ");
        }
        sb.delete(sb.length() - 3, sb.length() - 1);
        sb.append(");");
        return sb.toString();

    }

    public String createQuery(){
        switch (queryType){
            case DELETE:
                return createDeleteQuery();
            case INSERT:
                return createInsertQuery();
            case SELECT:
                return createSelectQuery();
            case UPDATE:
                return createUpdateQuery();
            default:
                return "";
        }
    }


}
