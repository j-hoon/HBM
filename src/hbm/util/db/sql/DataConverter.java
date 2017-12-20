package hbm.util.db.sql;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataConverter {
	
	
	/*
	 * 
	 */
	public static List<Map<String, Object>> convResultSetToMapList(ResultSet resultset) throws SQLException {
		ResultSetMetaData resultSetMetaData = resultset.getMetaData();
	    int columnCnt = resultSetMetaData.getColumnCount();
	    List<Map<String, Object>> list = new ArrayList<>();
	    while(resultset.next()) {
	        Map<String, Object> row = new HashMap<>(columnCnt);
	        for(int i = 1; i <= columnCnt; ++i) {
	            row.put(resultSetMetaData.getColumnName(i), resultset.getObject(i));
//	            System.out.print("[resultSetMetaData] colName("+i+"): " + resultSetMetaData.getColumnName(i) + ", getObj: " + resultset.getObject(i));
//	            if(resultset.getObject(i) != null)
//	            	System.out.println(", getClass: " + resultset.getObject(i).getClass());
//	            else
//	            	System.out.println();
	        }
	        list.add(row);
	    }
	    return list;
	}
	
	/*
	 * 
	 */
	public static <T> T mapToObject(Map<String, Object> map, T obj) {
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;
        Iterator<String> itr = map.keySet().iterator();
        
        while(itr.hasNext()) {
            keyAttribute = (String) itr.next();
            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1).toLowerCase();	// TODO 두 단어 이상 변수 고려해야
            // debug
//            System.out.print("keyAttribute: " + keyAttribute);
//            System.out.print(", methodString: " + methodString);
//            if(map.get(keyAttribute) != null)
//            	System.out.println(", map.get(keyAttribute).getClass(): " + map.get(keyAttribute).getClass());
//            else
//            	System.out.println(", map.get(keyAttribute).getClass(): (null)");
            Method[] methods = obj.getClass().getDeclaredMethods();
            for(int i = 0; i < methods.length; i++) {
                if(methodString.equalsIgnoreCase(methods[i].getName())) {
                    try {
                        // debug
//                        System.out.println("map.get(keyAttribute): " + map.get(keyAttribute));
                        // java.math.BigDecimal to int
                    	if(map.get(keyAttribute) instanceof BigDecimal) {
                    		methods[i].invoke(obj, ((BigDecimal) map.get(keyAttribute)).intValue());
                    	}
                    	// Prevent String-null Exception 
                    	else if(map.get(keyAttribute) instanceof String) {
                    		if(map.get(keyAttribute) != null)
                    			methods[i].invoke(obj, map.get(keyAttribute));
                    		else
                    			methods[i].invoke(obj, "");
                    	}
                    	// java.sql.Timestamp to LocalDate
                    	else if(map.get(keyAttribute) instanceof java.sql.Timestamp)
                    		methods[i].invoke(obj, ((java.sql.Timestamp) map.get(keyAttribute)).toLocalDateTime().toLocalDate());
                    	// oracle.sql.TIMESTAMP to LocalDateTime
                    	else if(map.get(keyAttribute) instanceof oracle.sql.TIMESTAMP)
                    		methods[i].invoke(obj, ((oracle.sql.TIMESTAMP) map.get(keyAttribute)).timestampValue().toLocalDateTime());
                    	else
                    		methods[i].invoke(obj, map.get(keyAttribute));
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }
	
}
