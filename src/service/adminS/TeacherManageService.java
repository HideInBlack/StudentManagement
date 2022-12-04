package service.adminS;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.JdbcSqlite;
import utils.LayuiUtil;

public class TeacherManageService {

	public static final TeacherManageService me = new TeacherManageService();

	// 获取全部教师
	public void getTeacher(PrintWriter out, String pageNumber, String pageSize, String search) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			// 判断是否是查询
			if (search == null || search.trim() == "") {
				course = db.queryToArray("select st_teacher_info.id, st_teacher_info.name,sex,tel,\r\n"
						+ "st_teacher_info.memo,st_academy_info.name as academyname \r\n"
						+ "from st_teacher_info,st_academy_info \r\n" + "where st_teacher_info.del = 0 \r\n"
						+ "and st_academy_info.del=0\r\n" + "and st_academy_info.id = st_teacher_info.academy_id");
			} else {// 查询title
				course = db.queryToArray("select st_teacher_info.id, st_teacher_info.name,sex,tel,\r\n"
						+ "st_teacher_info.memo,st_academy_info.name as academy \r\n"
						+ "from st_teacher_info,st_academy_info \r\n" + "where st_teacher_info.del = 0 \r\n"
						+ "and st_academy_info.del=0\r\n"
						+ "and st_academy_info.id = st_teacher_info.academy_id and st_teacher_info.name like '%"
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
				map.put("\"name\"", "\"" + course[i][1] + "\"");
				map.put("\"sex\"", course[i][2]);
				map.put("\"tel\"", "\"" + course[i][3] + "\"");
				map.put("\"memo\"", "\"" + course[i][4] + "\"");
				map.put("\"academy\"", "\"" + course[i][5] + "\"");
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

	// 添加教师
	public void addTeacher(PrintWriter out, String name, String sex, String tel, String academy, String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (name.equals("") || sex.equals("") || tel.equals("") || academy.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，教师添加失败！\"}");
			} else {
				int result = db.update("insert into st_teacher_info (name,sex,tel,academy_id,memo) \r\n" + "values ('"
						+ name + "'," + Integer.parseInt(sex) + ",'" + tel + "', " + academy + ",'" + memo + "')");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"添加教师成功！\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"教师添加失败！\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改教师
	public void updateTeacher(PrintWriter out, String id, String name, String sex, String tel, String academy,
			String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("") || name.equals("") || sex.equals("") || tel.equals("") || academy.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，教师信息修改失败！\"}");
			} else {
				int result = db.update("UPDATE st_teacher_info SET name = '" + name + "', sex =" + Integer.parseInt(sex)
						+ " ,tel='" + tel + "' ,academy_id='" + Integer.parseInt(academy) + "' ,memo='" + memo
						+ "' WHERE id = " + Integer.parseInt(id) + "");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"教师信息修改成功！\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"未知错误，教师信息修改失败！\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除教师
	public void deleteTeacher(PrintWriter out, String id, String order) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，教师信息删除失败！\"}");
			} else {
				int result = db.update("UPDATE st_teacher_info SET del = 1  WHERE id = " + id + "");
				if (order.equals("1")) {// 单个删除才会返回
					if (result == 1) {
						out.println("{\"ok\":true,\"msg\":\"教师信息删除成功！\"}");
					} else {
						out.println("{\"ok\":false,\"msg\":\"未知错误，教师信息删除失败！\"}");
					}
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取全部学院（下拉框）
	public void getAcademy(PrintWriter out) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course = db.queryToArray("select id ,name from st_academy_info where del = 0");

			List<Map> list = new LinkedList<>();
			for (int i = 0; i < course.length; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("\"id\"", course[i][0]);
				map.put("\"name\"", "\"" + course[i][1] + "\"");
				list.add(i, map);
			}
			Map<String, Object> result = LayuiUtil.setResultMap(0, "", list, course.length);
			out.println(result.toString().replace("=", ":"));
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
