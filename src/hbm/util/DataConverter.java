package hbm.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
	 * DB에서 호출 시 : DB-ResultSet => List<Map>
	 * 호출 결과 GUI에 출력 시 : List<Map>
	 * DB에 추가, 수정 시 : List<Map> -> List<VO> or VO => DB
	 */
	
	/*
	 * Convert SQL-ResultSet to List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> convResultSetToMapList(ResultSet resultset) {
		ResultSetMetaData resultSetMetaData = null;
		List<Map<String, Object>> list = null;
		try {
			resultSetMetaData = resultset.getMetaData();

		    int columnCnt = resultSetMetaData.getColumnCount();
		    list = new ArrayList<>();
		    while(resultset.next()) {
		        Map<String, Object> row = new HashMap<>(columnCnt);
		        for(int i = 1; i <= columnCnt; ++i) {
		            row.put(resultSetMetaData.getColumnName(i), resultset.getObject(i));
		            // debug
//		            System.out.print("[resultSetMetaData] colName("+i+"): " + resultSetMetaData.getColumnName(i) + ", getObj: " + resultset.getObject(i));
//		            if(resultset.getObject(i) != null)
//		            	System.out.println(", getClass: " + resultset.getObject(i).getClass());
//		            else
//		            	System.out.println();
		        }
		        // debug
//            	System.out.println();
//		        System.out.println(row);
		        list.add(row);
		    }
		    // Debug
			if(Properties.getInstance().isDebugMode()) {
				Debug.show("[Ret List<Map>]");
				try {
					Debug.showListToTable(list);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return list;
	}
	
	/*
	 * Convert Map<String, Object> to VO
	 */
	public static <T> T mapToVO(Class<T> voType, Map<String, Object> map) {
		String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;
        Iterator<String> itr = map.keySet().iterator();
		
		Class<T> cls = voType;
		Object[] constructorParamObject = new Object[] {};
		Constructor<T> constructor = null;
		T ret = null;
		try {
			constructor = cls.getConstructor();
			ret = constructor.newInstance(constructorParamObject);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// debug
//		System.out.println("cls: " + cls);
//		System.out.println("constructorParamObject: " + constructorParamObject);
//		System.out.println("constructor" + constructor);
//		System.out.println("ret" + ret);
        
        while(itr.hasNext()) {
            keyAttribute = (String) itr.next();
            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1).toLowerCase();
            // debug
//            System.out.print("keyAttribute: " + keyAttribute);
//            System.out.print(", methodString: " + methodString);
//            if(map.get(keyAttribute) != null)
//            	System.out.println(", map.get(keyAttribute).getClass(): " + map.get(keyAttribute).getClass());
//            else
//            	System.out.println(", map.get(keyAttribute).getClass(): (null)");
            Method[] methods = ret.getClass().getDeclaredMethods();
            for(int i = 0; i < methods.length; i++) {
                if(methodString.equalsIgnoreCase(methods[i].getName())) {
                    try {
                        // debug
//                        System.out.println("map.get(keyAttribute): " + map.get(keyAttribute));
                        // java.math.BigDecimal to int
                    	if(map.get(keyAttribute) instanceof BigDecimal) {
                    		methods[i].invoke(ret, ((BigDecimal) map.get(keyAttribute)).intValue());
                    	}
                    	// Prevent String-null Exception 
                    	else if(map.get(keyAttribute) instanceof String) {
                    		if(map.get(keyAttribute) != null)
                    			methods[i].invoke(ret, map.get(keyAttribute));
                    		else
                    			methods[i].invoke(ret);
                    	}
                    	// java.sql.Timestamp to LocalDate
                    	else if(map.get(keyAttribute) instanceof java.sql.Timestamp)
                    		methods[i].invoke(ret, ((java.sql.Timestamp) map.get(keyAttribute)).toLocalDateTime().toLocalDate());
                    	// oracle.sql.TIMESTAMP to LocalDateTime
                    	else if(map.get(keyAttribute) instanceof oracle.sql.TIMESTAMP)
                    		methods[i].invoke(ret, ((oracle.sql.TIMESTAMP) map.get(keyAttribute)).timestampValue().toLocalDateTime());
                    	else
                    		methods[i].invoke(ret, map.get(keyAttribute));
                    	// additional Types
                    	// ...
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
		return ret;
	}
	
	/*
	 * Convert List<Map<String, Object>> to List<VO>
	 */
	public static <T> List<T> mapListToVOList(Class<T> voType, List<Map<String, Object>> mapList) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<T> retList = new ArrayList<>();
        if(!mapList.isEmpty()) {
        	for(int i = 0; i < mapList.size(); i++) {
        		retList.add(mapToVO(voType, mapList.get(i)));
        	}
        }
        return retList;
    }
	
	
}
