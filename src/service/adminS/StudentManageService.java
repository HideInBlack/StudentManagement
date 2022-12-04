package service.adminS;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.JdbcSqlite;
import utils.LayuiUtil;

public class StudentManageService {

	public static final StudentManageService me = new StudentManageService();

	// ��ȡȫ��ѧ��
	public void getStudent(PrintWriter out, String pageNumber, String pageSize, String search,String key , String studentId) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			String one = "1";
			String myself = "7";//ѧ����ѯ�Լ��ģ�һ���ˣ�
			// �ж��Ƿ��ǲ�ѯ
			if(myself.equals(key)) {
				course = db.queryToArray(//���Ǹ��ݰ༶��ѯ
						"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,\r\n"
								+ "tel ,st_class_info.class_name as class,st_academy_info.name as academy, st_student_info.memo\r\n"
								+ "from st_student_info ,st_class_info ,st_academy_info\r\n"
								+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id and "
								+ "st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0 and st_student_info.student_id ="+studentId+"");
			}else if(one.equals(key)) {
				course = db.queryToArray(//���Ǹ��ݰ༶��ѯ
						"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,\r\n"
								+ "tel ,st_class_info.class_name as class,st_academy_info.name as academy, st_student_info.memo\r\n"
								+ "from st_student_info ,st_class_info ,st_academy_info\r\n"
								+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id and "
								+ "st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0 and st_student_info.class_id ="+search+"");
			}else if (search == null || search.trim() == "") {
				course = db.queryToArray(//��ѯ����
						"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,\r\n"
								+ "tel ,st_class_info.class_name as class,st_academy_info.name as academy, st_student_info.memo\r\n"
								+ "from st_student_info ,st_class_info ,st_academy_info\r\n"
								+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id and "
								+ "st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0");
			} else {
				course = db.queryToArray(//����ģ����ѯ
						"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,\r\n"
								+ "tel ,st_class_info.class_name as class,st_academy_info.name as academy, st_student_info.memo\r\n"
								+ "from st_student_info ,st_class_info ,st_academy_info\r\n"
								+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id and "
								+ "st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0 and st_student_info.name like '%"
								+ search + "%'");
			}

			// �Է�ҳ����ش���
			int pageSizes = Integer.parseInt(pageSize);
			int pageNum = (Integer.parseInt(pageNumber) - 1) * pageSizes;

			int iLength;
			int iList = 0;
			if (course.length - pageNum > pageSizes) {
				iLength = pageNum + pageSizes;
			} else {
				// ���������һҳ��ʱ��
				iLength = course.length;
			}
			List<Map> list = new LinkedList<>();
			for (int i = pageNum; i < iLength; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("\"id\"", course[i][0]);
				map.put("\"student_id\"", course[i][1]);
				map.put("\"name\"", "\"" + course[i][2] + "\"");
				map.put("\"sex\"", course[i][3]);
				map.put("\"tel\"", "\"" + course[i][4] + "\"");
				map.put("\"class\"", "\"" + course[i][5] + "\"");
				map.put("\"academy\"", "\"" + course[i][6] + "\"");
				map.put("\"memo\"", "\"" + course[i][7] + "\"");
				list.add(iList, map);
				iList++;
			}
			Map<String, Object> result = LayuiUtil.setResultMap(0, "", list, course.length);
			out.println(result.toString().replace("=", ":"));
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���ѧ����Ϣ
	public void addStudent(PrintWriter out, String student_id ,String name, String sex, String tel, String klass, String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (student_id.equals("") || name.equals("") || sex.equals("") || tel.equals("") || klass.equals("") ) {
				out.println("{\"ok\":false,\"msg\":\"�����д�ѧ�����ʧ�ܣ�\"}");
			} else {
				int result = db.update("insert into st_student_info (student_id,name,sex,tel,class_id,memo) \r\n" + 
						"values ('"+student_id+"' , '"+name+"' , "+sex+" , '"+tel+"' , "+klass+" , '"+memo+"')");
				db.commit();
				
				Object[][] tcid =  db.queryToArray("select id from st_teacher_class_course_relation where class_id="+klass+"");
				Object[][] stuid =  db.queryToArray("select id from st_student_info where student_id = "+student_id+"");
				for(int i = 0 ; i < tcid.length ; i++) {
					db.update("INSERT INTO st_student_score_relation (student_id,teacher_course_id) VALUES("+stuid[0][0]+","+tcid[i][0]+")");
				}
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"���ѧ���ɹ���\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"ѧ�����ʧ�ܣ�\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �޸�ѧ����Ϣ
	public void updateStudent(PrintWriter out, String id, String name, String sex, String tel, String klass,
			String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("") || name.equals("") || sex.equals("") || tel.equals("") || klass.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"�����д�ѧ����Ϣ�޸�ʧ�ܣ�\"}");
			} else {
				int result = db.update("UPDATE st_student_info SET name = '"+name+"', sex ="+sex+",tel='"+tel+"' ,\r\n" + 
						"class_id="+klass+" ,memo='"+memo+"' WHERE id = "+id+"");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"ѧ����Ϣ�޸ĳɹ���\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"δ֪����ѧ����Ϣ�޸�ʧ�ܣ�\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ɾ��ѧ��
	public void deleteStudent(PrintWriter out, String id, String order) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"�����д�ѧ����Ϣɾ��ʧ�ܣ�\"}");
			} else {
				int result = db.update("UPDATE st_student_info SET del = 1  WHERE id = " + id + "");
				if (order.equals("1")) {// ����ɾ���Ż᷵��
					if (result == 1) {
						out.println("{\"ok\":true,\"msg\":\"ѧ����Ϣɾ���ɹ���\"}");
					} else {
						out.println("{\"ok\":false,\"msg\":\"δ֪����ѧ����Ϣɾ��ʧ�ܣ�\"}");
					}
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
