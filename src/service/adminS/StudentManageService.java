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

	// 获取全部学生
	public void getStudent(PrintWriter out, String pageNumber, String pageSize, String search,String key , String studentId) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			String one = "1";
			String myself = "7";//学生查询自己的（一个人）
			// 判断是否是查询
			if(myself.equals(key)) {
				course = db.queryToArray(//这是根据班级查询
						"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,\r\n"
								+ "tel ,st_class_info.class_name as class,st_academy_info.name as academy, st_student_info.memo\r\n"
								+ "from st_student_info ,st_class_info ,st_academy_info\r\n"
								+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id and "
								+ "st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0 and st_student_info.student_id ="+studentId+"");
			}else if(one.equals(key)) {
				course = db.queryToArray(//这是根据班级查询
						"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,\r\n"
								+ "tel ,st_class_info.class_name as class,st_academy_info.name as academy, st_student_info.memo\r\n"
								+ "from st_student_info ,st_class_info ,st_academy_info\r\n"
								+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id and "
								+ "st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0 and st_student_info.class_id ="+search+"");
			}else if (search == null || search.trim() == "") {
				course = db.queryToArray(//查询所有
						"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,\r\n"
								+ "tel ,st_class_info.class_name as class,st_academy_info.name as academy, st_student_info.memo\r\n"
								+ "from st_student_info ,st_class_info ,st_academy_info\r\n"
								+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id and "
								+ "st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0");
			} else {
				course = db.queryToArray(//这是模糊查询
						"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,\r\n"
								+ "tel ,st_class_info.class_name as class,st_academy_info.name as academy, st_student_info.memo\r\n"
								+ "from st_student_info ,st_class_info ,st_academy_info\r\n"
								+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id and "
								+ "st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0 and st_student_info.name like '%"
								+ search + "%'");
			}

			// 对分页的相关处理
			int pageSizes = Integer.parseInt(pageSize);
			int pageNum = (Integer.parseInt(pageNumber) - 1) * pageSizes;

			int iLength;
			int iList = 0;
			if (course.length - pageNum > pageSizes) {
				iLength = pageNum + pageSizes;
			} else {
				// 这里是最后一页的时候
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

	// 添加学生信息
	public void addStudent(PrintWriter out, String student_id ,String name, String sex, String tel, String klass, String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (student_id.equals("") || name.equals("") || sex.equals("") || tel.equals("") || klass.equals("") ) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，学生添加失败！\"}");
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
					out.println("{\"ok\":true,\"msg\":\"添加学生成功！\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"学生添加失败！\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改学生信息
	public void updateStudent(PrintWriter out, String id, String name, String sex, String tel, String klass,
			String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("") || name.equals("") || sex.equals("") || tel.equals("") || klass.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，学生信息修改失败！\"}");
			} else {
				int result = db.update("UPDATE st_student_info SET name = '"+name+"', sex ="+sex+",tel='"+tel+"' ,\r\n" + 
						"class_id="+klass+" ,memo='"+memo+"' WHERE id = "+id+"");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"学生信息修改成功！\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"未知错误，学生信息修改失败！\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除学生
	public void deleteStudent(PrintWriter out, String id, String order) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，学生信息删除失败！\"}");
			} else {
				int result = db.update("UPDATE st_student_info SET del = 1  WHERE id = " + id + "");
				if (order.equals("1")) {// 单个删除才会返回
					if (result == 1) {
						out.println("{\"ok\":true,\"msg\":\"学生信息删除成功！\"}");
					} else {
						out.println("{\"ok\":false,\"msg\":\"未知错误，学生信息删除失败！\"}");
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
