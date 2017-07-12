package ro.teamnet.zth.api.em;

/**
 * Created by Alexandra.Gaman on 7/12/2017.
 */
public class ColumnInfo {
    private String columnName;
    private Class columnType;
    private String dbColumnName;
    private boolean isId;
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isId() {
        return isId;
    }

    public void setId(boolean id) {
        isId = id;
    }

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    public Class getColumnType() {
        return columnType;
    }

    public void setColumnType(Class columnType) {
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass())
            return false;
        ColumnInfo o = (ColumnInfo) obj;
        if(columnName.equals(o.getColumnName()) && columnType.equals(o.getColumnType())
                && dbColumnName.equals(o.getDbColumnName()) && isId == o.isId())
            return true;
        return false;
    }
}
