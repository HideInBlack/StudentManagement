package service.adminS;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.JdbcSqlite;
import utils.LayuiUtil;

public class ClassManageService {

	public static final ClassManageService me = new ClassManageService();

	// ��ȡȫ��ѧԺ�����
	public void getAllAcademy(PrintWriter out, String pageNumber, String pageSize, String search) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			// �ж��Ƿ��ǲ�ѯ
			if (search == null || search.trim() == "") {
				course = db.queryToArray("select id ,name,memo from st_academy_info where del = 0");
			} else {// ��ѯtitle
				course = db.queryToArray("select id ,name,memo from st_academy_info where del = 0 and st_academy_info.name like '%"
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
				map.put("\"name\"", "\"" + course[i][1] + "\"");
				map.put("\"memo\"", "\"" + course[i][2] + "\"");
				map.put("\"level\"", 1);
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
		public void addAcademy(PrintWriter out, String name,String memo) {

			JdbcSqlite db;
			try {
				db = new JdbcSqlite("jdbc/sqlite");
				if (name.equals("")) {
					out.println("{\"ok\":false,\"msg\":\"�����д�ѧԺ���ʧ�ܣ�\"}");
				} else {
					int result = db.update("insert into st_academy_info (name,memo) values ('" + name
							+ "','" + memo + "')");
					if (result == 1) {
						out.println("{\"ok\":true,\"msg\":\"ѧԺ��ӳɹ���\"}");
					} else {
						out.println("{\"ok\":false,\"msg\":\"ѧԺ���ʧ�ܣ�\"}");
					}
				}
				db.commit();
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	// ��ȡ�����������ѧԺ�İ༶������ѧԺ��
	public void getAllklass(PrintWriter out, String pageNumber, String pageSize, String search) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course;
			// �ж��Ƿ��ǲ�ѯ
			if (search == null || search.trim() == "") {
				course = db.queryToArray("select id ,class_name,memo from st_class_info where del = 0");
			} else {// ��ѯtitle
				course = db.queryToArray("select id ,class_name,memo from st_class_info where del = 0 and academy_id = "+search+"");
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
				map.put("\"name\"", "\"" + course[i][1] + "\"");
				map.put("\"memo\"", "\"" + course[i][2] + "\"");
				map.put("\"level\"", 2);
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

	// ��ȡȫ���༶��������
	public void getClass(PrintWriter out) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] course = db.queryToArray("select id ,class_name from st_class_info where del = 0");

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

	// ��ȡȫ��ѧԺ-�༶-ѧ��������
	public void getAll(PrintWriter out) {

		JdbcSqlite db;
		try {
			db = new JdbcSqlite("jdbc/sqlite");

			Object[][] first = db.queryToArray("select id ,name from st_academy_info where del = 0");

			// ����ƴװ
			List<Map> firstall = new LinkedList();
			for (int i = 0; i < first.length; i++) {
				Map<String, Object> firstMap = new HashMap<>();

				Object[][] second = db.queryToArray(
						"select id ,class_name from st_class_info where del = 0 and academy_id = " + first[i][0] + "");
				List<Map> secondall = new LinkedList<>();

				for (int j = 0; j < second.length; j++) {
					Map<String, Object> secondMap = new HashMap<>();
					secondMap.put("\"id\"", second[j][0]);
					secondMap.put("\"title\"", "\"" + second[j][1] + "\"");
					secondMap.put("\"level\"", 2);
					// secondMap.put("\"children\"", first[i][0]);
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
}
