package service.shareS;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.AccessControl;
import utils.JdbcSqlite;

/**
 * 2019年11月27日10:50:01
 * 登录相关逻辑数据处理服务类
 * @author 董
 *
 */
public class LoginSomeService {
	public static final LoginSomeService me = new LoginSomeService(); 

	//登录判断服务
	public void loginCheck(String num,String pwd ,PrintWriter out ,HttpSession session ) {
		
		//jdbc数据库驱动
		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			Object[][] name = db.queryToArray("select num  ,role,password ,name from st_admin \r\n" + 
					"union select tel as num,role,password ,name from st_teacher_info\r\n" + 
					"union select student_id as num,role,password, name from st_student_info");
			
			//role=0:没有账号 /role=1:admin /role=2:teacher /role=3:student
			int role = 0;
			//这里先判断有没有账号
			for (int i = 0; i < name.length; i++) {
				if (num.equals(name[i][0])) {
					//有账号，判断密码正确与否
					role = 1;
					if (pwd.equals(name[i][2])) {
						out.println("{\"ok\":true,\"name\":\"" + num + "\",\"msg\":\"登录成功！\" ,\"href\":\"/StudentManagement/src/share/index.html\",\"roles\":"+ (String) name[i][1]+"}");
						//登录成功，保存到session
						String roles= (String) name[i][1];
						String nums=num;
						String names= (String) name[i][3];
						session.setAttribute("roles",roles);
						session.setAttribute("nums",nums);
						session.setAttribute("names",names);
						
					} else {
						out.println("{\"ok\":false,\"name\":\"" + num + "\",\"msg\":\"密码错误！\" }");
					}
				}
			}
			if (role == 0) {
				out.println("{\"ok\":false,\"msg\":\"账号不存在，建议先注册！\" }");
			}
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
