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

import service.adminS.TeacherManageService;

/**
 * ��ʦ�������� 2019��12��21��22:47:48
 * 
 * @author ��
 *
 */
public class TeacherManage extends HttpServlet {

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

	// ��ȡ���н�ʦ �� ������ѯ
	private void getTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String search = request.getParameter("search");

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			TeacherManageService.me.getTeacher(out, pageNumber, pageSize, search);
		}
	}

	// ����Ա��ӽ�ʦ
	private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		String academy = request.getParameter("academy");
		String memo = request.getParameter("memo");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			TeacherManageService.me.addTeacher(out, name, sex, tel, academy, memo);
		}
	}

	// ����Ա�޸Ľ�ʦ��Ϣ
	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		String academy = request.getParameter("academy");
		String memo = request.getParameter("memo");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			TeacherManageService.me.updateTeacher(out, id, name, sex, tel, academy, memo);
		}
	}

	// ����Աɾ��ĳ����ʦ������ɾ��
	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
				TeacherManageService.me.deleteTeacher(out, id, order);
			} else if (order.equals("2")) {
				// ����ɾ��
				String[] ids = id.split(",");
				for (int i = 0; i < ids.length; i++) {
					TeacherManageService.me.deleteTeacher(out, ids[i], order);
				}
				out.println("{\"ok\":true,\"msg\":\"��ѡ��ʦɾ���ɹ���\"}");
			}

		}
	}

	// ��ȡ����ѧԺ
	private void getAcademy(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			TeacherManageService.me.getAcademy(out);
		}
	}

}
