package service.studentS;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.JdbcSqlite;
import utils.LayuiUtil;

public class StudentService {

	public static final StudentService me = new StudentService();

	// 获取全部学生成绩（班级为单位）
	public void getMyScore(PrintWriter out, String pageNumber, String pageSize, String studentId,String search,String key) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			String one = "-1";//search=-1是查课程数据图表
			// 判断是否是查询
			if (search == null || search.trim() == "") {
				course = db.queryToArray("select st_student_score_relation.id ,st_student_info.name,st_course_info.title ,st_teacher_info.name as teacher, st_student_score_relation.score ,st_student_score_relation.memo\r\n" + 
						"from st_student_score_relation ,st_student_info,st_teacher_class_course_relation,st_course_info,st_teacher_info\r\n" + 
						"where st_student_score_relation.student_id = st_student_info.id\r\n" + 
						"and st_student_score_relation.teacher_course_id =st_teacher_class_course_relation.id and st_teacher_class_course_relation.course_id = st_course_info.id and st_teacher_info.id = st_teacher_class_course_relation.teacher_id and st_student_score_relation.del=0 and st_student_info.del=0 and st_teacher_class_course_relation.del = 0 and st_course_info.del =0 and st_teacher_info.del=0\r\n" + 
						"and st_student_info.student_id = '"+studentId+"'");
			}else {
				course = db.queryToArray("select st_student_score_relation.id ,st_student_info.name,st_course_info.title ,st_teacher_info.name as teacher, st_student_score_relation.score ,st_student_score_relation.memo\r\n" + 
						"from st_student_score_relation ,st_student_info,st_teacher_class_course_relation,st_course_info,st_teacher_info\r\n" + 
						"where st_student_score_relation.student_id = st_student_info.id\r\n" + 
						"and st_student_score_relation.teacher_course_id =st_teacher_class_course_relation.id and st_teacher_class_course_relation.course_id = st_course_info.id and st_teacher_info.id = st_teacher_class_course_relation.teacher_id and st_student_score_relation.del=0 and st_student_info.del=0 and st_teacher_class_course_relation.del = 0 and st_course_info.del =0 and st_teacher_info.del=0\r\n" + 
						"and st_course_info.title like '%"+search+"%' and st_student_info.student_id = '"+studentId+"'");
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
				map.put("\"name\"", "\""+ course[i][1] +"\"");
				map.put("\"title\"", "\""+ course[i][2] +"\"");
				map.put("\"teacher\"", "\""+ course[i][3] + "\"");
				map.put("\"score\"", "\""+course[i][4] + "\"");
				map.put("\"memo\"",  "\""+course[i][5]+ "\"");
				list.add(iList, map);
				iList++;
			}
			Map<String, Object> result = LayuiUtil.setResultMap(0, "", list, course.length);
			
			if(one.equals(key)) {//查出表格
				String datax = "[";
				for (int i = pageNum; i < iLength; i++) {
					if(i==iLength-1){
						datax = datax + "\""+course[i][2]+"\"]" ;
					}else{
						datax = datax + "\""+course[i][2]+"\"," ;
					}
				}
				//out.println("{\"code\":0,"+"\"data\":" + datax+",\"msg\":\"\",\"count\":"+iLength+"}");
				out.println(datax);
			}else {

				out.println(result.toString().replace("=", ":"));
			}
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 学生修改自己信息
	public void updateOne(PrintWriter out, String studentId, String password, String tel, String memo) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");
			if (studentId.equals("") || password.equals("") || tel.equals("") ) {
				out.println("{\"ok\":false,\"msg\":\"数据有错，学生信息修改失败！\"}");
			} else {
				int result = db.update("UPDATE st_student_info SET password ='"+password+"' , tel="+tel+", memo='"+memo+"'\r\n" + 
						"WHERE student_id = "+studentId+"");
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

}
