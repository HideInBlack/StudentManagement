package utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 访问控制类
 * @author 董
 *
 */
public final class AccessControl {
	//登录 返回的是roles身份 用来设置给session
	public static String login(JdbcSqlite db,String name,String pwd)throws Exception{
		Object[][] a=db.queryToArray("select roles from user where name='"+name+"' and pwd='"+pwd+"' limit 1");
		if(a.length==1)return (String)a[0][0];
		return null;
	}
	//包含角色
	public static boolean in(String role,String roles){
		Set<String> s=new HashSet<String>();
		String[] a=roles.split(",");
		for(String b:a)s.add(b);
		return s.contains(role);
	}
}
