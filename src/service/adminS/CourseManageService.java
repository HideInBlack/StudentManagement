package service.adminS;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.JdbcSqlite;
import utils.LayuiUtil;

public class CourseManageService {

	public static final CourseManageService me = new CourseManageService();

	// 获取全部课程
	public void getCourse(PrintWriter out, String pageNumber, String pageSize, String search) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			// 判断是否是查询
			if (search == null || search.trim() == "") {
				course = db.queryToArray("select * from st_course_info where del = 0");
			} else {// 查询title
				course = db
						.queryToArray("select * from st_course_info where del = 0 and title like '%" + search + "%'");
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
				map.put("\"title\"", "\"" + course[i][1] + "\"");
				map.put("\"credit\"", course[i][2]);
				map.put("\"class_time\"", course[i][3]);
				map.put("\"memo\"", "\"" + course[i][4] + "\"");
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

	// 添加课程
	public void addCourse(PrintWriter out, String title, String credit, String class_time, String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (title.equals("") || title.equals("") || title.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，课程添加失败！\"}");
			} else {
				int result = db.update("insert into st_course_info (title,credit,class_time,memo) values ('" + title
						+ "','" + credit + "','" + class_time + "','" + memo + "')");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"课程添加成功！\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"课程添加失败！\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改课程
	public void updateCourse(PrintWriter out, String id, String title, String credit, String class_time) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("") || title.equals("") || credit.equals("") || class_time.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，课程修改失败！\"}");
			} else {
				int result = db.update("UPDATE st_course_info SET title = '" + title + "', credit = " + credit
						+ " ,class_time=" + class_time + " WHERE id = " + id + "");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"课程修改成功！\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"未知错误，课程修改失败！\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除课程
	public void deleteCourse(PrintWriter out, String id,String order) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，课程删除失败！\"}");
			} else {
				int result = db.update("UPDATE st_course_info SET del = 1  WHERE id = " + id + "");
				if(order.equals("1")) {//单个删除才会返回
					if (result == 1 ) {
						out.println("{\"ok\":true,\"msg\":\"课程删除成功！\"}");
					} else {
						out.println("{\"ok\":false,\"msg\":\"未知错误，课程删除失败！\"}");
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
