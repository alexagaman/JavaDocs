package ro.teamnet.zth.api.em;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexandra.Gaman on 7/12/2017.
 */
public class EntityUtils {
    private EntityUtils(){
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity){
        Annotation[] annotations = entity.getAnnotations();
        for(int i =0; i < annotations.length; i++){
            if(annotations[i].annotationType().getSimpleName().equals("Table")){
                Class an = annotations[i].annotationType();
                try {
                   return (String) an.getMethod("name").invoke(annotations[i]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity.getSimpleName();
    }

    public static List<ColumnInfo> getColumns(Class entity){
        List<ColumnInfo> list = new ArrayList<ColumnInfo>();
        List<Field> fields = Arrays.asList(entity.getDeclaredFields());
        for(Field field : fields){
            Annotation[] annotations = field.getAnnotations();
            for(int i =0; i < annotations.length; i++){
                if(annotations[i].annotationType().getSimpleName().equals("Column") ||
                        annotations[i].annotationType().getSimpleName().equals("Id") ){
                    Class an = annotations[i].annotationType();
                    try {
                        ColumnInfo ci = new ColumnInfo();
                        ci.setColumnName(field.getName());
                        ci.setColumnType(field.getType());
                        ci.setId(false);
                        ci.setDbColumnName((String) an.getMethod("name").invoke(annotations[i]));
                        list.add(ci);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                else if(annotations[i].annotationType().getSimpleName().equals("Id") ){
                    Class an = annotations[i].annotationType();
                    try {
                        ColumnInfo ci = new ColumnInfo();
                        ci.setColumnName(field.getName());
                        ci.setColumnType(field.getType());
                        ci.setId(true);
                        ci.setDbColumnName((String) an.getField("name").get(an));
                        list.add(ci);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }

    public static Object castFromSqlType(Object value, Class wantedType){
        if(value.getClass().getSimpleName().equals("BigDecimal")){
            if(wantedType.getSimpleName().equals("Integer"))
                return Integer.valueOf(((BigDecimal) value).intValue());
            else if(wantedType.getSimpleName().equals("Long"))
                return Long.valueOf(((BigDecimal) value).longValue());
            else if(wantedType.getSimpleName().equals("Float"))
                return Float.valueOf(((BigDecimal) value).floatValue());
            else if(wantedType.getSimpleName().equals("Double"))
                return Double.valueOf(((BigDecimal) value).doubleValue());
        }
        return value;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation){
        List<Field> list = new ArrayList<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            Annotation[] annotations = fields[i].getAnnotations();
            for(int j=0; j< annotations.length; j++)
                if(annotations[j].annotationType().getSimpleName().equals(annotation.getSimpleName())) {
                    list.add(fields[i]);
                    break;
                }
        }
        return list;
    }

    public static Object getSqlValue(Object object){
        Annotation[] annotations = object.getClass().getAnnotations();
        for(int i =0; i < annotations.length; i++){
            if(annotations[i].annotationType().getSimpleName().equals("Table")){
                Field[] fields = object.getClass().getDeclaredFields();
                for(int j = 0; j < fields.length; j++){
                    Annotation[] annotations1 = fields[j].getAnnotations();
                    for(int k =0; k<annotations1.length; k++){
                        if(annotations1[k].annotationType().getSimpleName().equals("Id")) {
                            fields[j].setAccessible(true);
                            return fields[j].getType();
                        }
                    }
                }
            }
        }
        return object;
    }


}
