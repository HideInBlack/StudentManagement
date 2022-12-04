package utils;

import javax.servlet.http.HttpSession;

public class DzySomeUtil {

	/**
	 * session的核实判断是否已经登录
	 * @param session
	 * @throws Exception
	 */
	public static boolean sessionCheck(HttpSession session) throws Exception {
		
		//已经登录：查看session session有值，说明已经登录，给出判定页面即可
		String roles=(String)session.getAttribute("roles");
		String names =(String)session.getAttribute("names");

		//进行登录：session里没有roles值，说明登录已经失效，或者第一次登录，进行登录并把roles值绑定到session上
		if(roles==null){
			return false ;
		}else {
			return true ;
		}
	}
	/**
	 * 给字符串加双引号
	 */
	public String addMark(String old) {
		return "\"" + old + "\"";
	}
}
