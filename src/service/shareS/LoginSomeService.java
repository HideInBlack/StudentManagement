package service.shareS;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.AccessControl;
import utils.JdbcSqlite;

/**
 * 2019��11��27��10:50:01
 * ��¼����߼����ݴ��������
 * @author ��
 *
 */
public class LoginSomeService {
	public static final LoginSomeService me = new LoginSomeService(); 

	//��¼�жϷ���
	public void loginCheck(String num,String pwd ,PrintWriter out ,HttpSession session ) {
		
		//jdbc���ݿ�����
		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			Object[][] name = db.queryToArray("select num  ,role,password ,name from st_admin \r\n" + 
					"union select tel as num,role,password ,name from st_teacher_info\r\n" + 
					"union select student_id as num,role,password, name from st_student_info");
			
			//role=0:û���˺� /role=1:admin /role=2:teacher /role=3:student
			int role = 0;
			//�������ж���û���˺�
			for (int i = 0; i < name.length; i++) {
				if (num.equals(name[i][0])) {
					//���˺ţ��ж�������ȷ���
					role = 1;
					if (pwd.equals(name[i][2])) {
						out.println("{\"ok\":true,\"name\":\"" + num + "\",\"msg\":\"��¼�ɹ���\" ,\"href\":\"/StudentManagement/src/share/index.html\",\"roles\":"+ (String) name[i][1]+"}");
						//��¼�ɹ������浽session
						String roles= (String) name[i][1];
						String nums=num;
						String names= (String) name[i][3];
						session.setAttribute("roles",roles);
						session.setAttribute("nums",nums);
						session.setAttribute("names",names);
						
					} else {
						out.println("{\"ok\":false,\"name\":\"" + num + "\",\"msg\":\"�������\" }");
					}
				}
			}
			if (role == 0) {
				out.println("{\"ok\":false,\"msg\":\"�˺Ų����ڣ�������ע�ᣡ\" }");
			}
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
