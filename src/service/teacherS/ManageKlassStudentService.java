package service.teacherS;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.JdbcSqlite;
import utils.LayuiUtil;

public class ManageKlassStudentService {

	public static final ManageKlassStudentService me = new ManageKlassStudentService();

	// 获取全部课程-班级-学生（树）
	public void getAll(PrintWriter out, String tel) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] first = db.queryToArray(
					"select distinct st_course_info.id,st_course_info.title from st_teacher_class_course_relation , st_course_info\r\n"
							+ "where st_course_info.id = st_teacher_class_course_relation.course_id\r\n"
							+ "and st_teacher_class_course_relation.teacher_id in (\r\n"
							+ "select id from st_teacher_info where tel = '" + tel + "')");

			// 树的拼装
			List<Map> firstall = new LinkedList();
			for (int i = 0; i < first.length; i++) {
				Map<String, Object> firstMap = new HashMap<>();
				Object[][] second = db.queryToArray("select distinct st_class_info.id,st_class_info.class_name \r\n"
						+ "from st_teacher_class_course_relation , st_class_info\r\n"
						+ "where st_class_info.id = st_teacher_class_course_relation.class_id\r\n"
						+ "and st_teacher_class_course_relation.course_id = " + first[i][0] + "\r\n"
						+ "and st_teacher_class_course_relation.teacher_id in (\r\n"
						+ "select id from st_teacher_info where tel = '" + tel + "')");
				List<Map> secondall = new LinkedList<>();

				for (int j = 0; j < second.length; j++) {

					Map<String, Object> secondMap = new HashMap<>();
//					Object[][] third = db.queryToArray("");
//					List<Map> thirdall = new LinkedList<>();
//					
//					for (int k = 0; k < third.length; k++) {
//						
//						Map<String, Object> thirdMap = new HashMap<>();
//						thirdMap.put("\"id\"", third[k][0]);
//						thirdMap.put("\"title\"", "\"" + third[k][1] + "\"");
//						thirdMap.put("\"level\"", 3);
//						thirdall.add(thirdMap);
//					}	
					secondMap.put("\"id\"", second[j][0]);
					secondMap.put("\"title\"", "\"" + second[j][1] + "\"");
					secondMap.put("\"level\"", 2);
					secondMap.put("\"courseId\"", first[i][0]);
//					secondMap.put("\"children\"", thirdall);
					secondall.add(secondMap);
				}
				firstMap.put("\"id\"", first[i][0]);
				firstMap.put("\"title\"", "\"" + first[i][1] + "\"");
				firstMap.put("\"level\"", 1);
				firstMap.put("\"children\"", secondall);
				firstall.add(firstMap);
			}

			Map<String, Object> result = LayuiUtil.setResultMap(0, "", firstall, first.length);
			out.println(result.toString().replace("=", ":"));
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取全部学生成绩（班级为单位）
	public void getKlassScore(PrintWriter out, String pageNumber, String pageSize, String tel, String klassId,
			String courseId) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			String one = "1";
			// 判断是否是查询
			course = db.queryToArray(
					"select st_student_info.id ,st_student_info.student_id ,st_student_info.name ,st_student_info.sex,tel ,"
							+ "st_class_info.class_name as class,st_academy_info.name as academy,st_student_score_relation.score,st_course_info.title,st_student_score_relation.id as score_id\r\n"
							+ "from st_student_info ,st_class_info ,st_academy_info ,st_student_score_relation,st_course_info\r\n"
							+ "where st_student_info.class_id = st_class_info.id and st_class_info.academy_id = st_academy_info.id \r\n"
							+ "and st_student_info.del=0 and st_class_info.del =0 and st_academy_info.del = 0 \r\n"
							+ "and st_student_score_relation.student_id = st_student_info.id and st_course_info.id = "
							+ courseId + "\r\n" + "and  st_student_info.class_id =" + klassId
							+ " and st_student_score_relation.teacher_course_id = (\r\n"
							+ "select id from st_teacher_class_course_relation where class_id=" + klassId
							+ " and course_id=" + courseId + " \r\n"
							+ "and teacher_id = (select id from st_teacher_info where tel = " + tel + ")\r\n" + ") 	");

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
				map.put("\"score\"", course[i][7]);
				map.put("\"title\"", "\"" + course[i][8] + "\"");
				map.put("\"scoreId\"", course[i][9]);
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

	// 评分
	public void updateScore(PrintWriter out, String scoreId, String score) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (scoreId.equals("") || score.equals("") ) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，评分失败！\"}");
			} else {
				int result = db.update("UPDATE st_student_score_relation SET score="+score+" WHERE id = "+scoreId+"");
				if (result == 1) {
					out.println("{\"ok\":true,\"msg\":\"评分成功！\"}");
				} else {
					out.println("{\"ok\":false,\"msg\":\"未知错误，评分失败！\"}");
				}
			}
			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
