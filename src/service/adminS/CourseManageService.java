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

	// ��ȡȫ���γ�
	public void getCourse(PrintWriter out, String pageNumber, String pageSize, String search) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			// �ж��Ƿ��ǲ�ѯ
			if (search == null || search.trim() == "") {
				course = db.queryToArray("select * from st_course_info where del = 0");
			} else {// ��ѯtitle
				course = db
						.queryToArray("select * from st_course_info where del = 0 and title like '%" + search + "%'");
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

	// ��ӿγ�
	public void addCourse(PrintWriter out, String title, String credit, String class_time, String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (title.equals("") || title.equals("") || title.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"�����д��γ����ʧ�ܣ�\"}");
			} else {
				int result = db.update("insert into st_course_info (title,credit,class_time,memo) values ('" + title
						+ "','" + credit + "','" + class_time + "','" + memo + "')");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"�γ���ӳɹ���\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"�γ����ʧ�ܣ�\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �޸Ŀγ�
	public void updateCourse(PrintWriter out, String id, String title, String credit, String class_time) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("") || title.equals("") || credit.equals("") || class_time.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"�����д��γ��޸�ʧ�ܣ�\"}");
			} else {
				int result = db.update("UPDATE st_course_info SET title = '" + title + "', credit = " + credit
						+ " ,class_time=" + class_time + " WHERE id = " + id + "");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"�γ��޸ĳɹ���\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"δ֪���󣬿γ��޸�ʧ�ܣ�\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ɾ���γ�
	public void deleteCourse(PrintWriter out, String id,String order) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (id.equals("")) {
				out.println("{\"ok\":false,\"msg\":\"�����д��γ�ɾ��ʧ�ܣ�\"}");
			} else {
				int result = db.update("UPDATE st_course_info SET del = 1  WHERE id = " + id + "");
				if(order.equals("1")) {//����ɾ���Ż᷵��
					if (result == 1 ) {
						out.println("{\"ok\":true,\"msg\":\"�γ�ɾ���ɹ���\"}");
					} else {
						out.println("{\"ok\":false,\"msg\":\"δ֪���󣬿γ�ɾ��ʧ�ܣ�\"}");
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
