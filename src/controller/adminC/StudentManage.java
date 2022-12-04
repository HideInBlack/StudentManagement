package controller.adminC;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.adminS.StudentManageService;
import service.adminS.TeacherManageService;

/**
 * ѧ����������
 * 2019��12��22��22:38:45
 * @author ��
 *
 */

public class StudentManage extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		Method method = null;
		try {
			method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException("���÷�������");
		}
	}

	// ��ȡ����ѧ�� �� ������ѯ���ְ༶��ѯ���Լ�ѧ���ǽ�ȥ�Լ����Լ�
	private void getStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String search = request.getParameter("search");
		String key = request.getParameter("key");

		String studentId = (String) session.getAttribute("nums");
		
		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			StudentManageService.me.getStudent(out, pageNumber, pageSize, search,key ,studentId);
		}
	}

	// ����Ա���ѧ��
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String student_id = request.getParameter("student_id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		String klass = request.getParameter("klass");
		String memo = request.getParameter("memo");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			StudentManageService.me.addStudent(out, student_id,name, sex, tel, klass, memo);
		}
	}

	// ����Ա�޸�ѧ����Ϣ
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		String klass = request.getParameter("klass");
		String memo = request.getParameter("memo");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			StudentManageService.me.updateStudent(out, id, name, sex, tel, klass, memo);
		}
	}

	// ����Աɾ��ĳ��ѧ��������ɾ��
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String order = request.getParameter("order");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			if (order.equals("1")) {
				// ɾ������
				StudentManageService.me.deleteStudent(out, id, order);
			} else if (order.equals("2")) {
				// ����ɾ��
				String[] ids = id.split(",");
				for (int i = 0; i < ids.length; i++) {
					StudentManageService.me.deleteStudent(out, ids[i], order);
				}
				out.println("{\"ok\":true,\"msg\":\"��ѡѧ��ɾ���ɹ���\"}");
			}

		}
	}
}
