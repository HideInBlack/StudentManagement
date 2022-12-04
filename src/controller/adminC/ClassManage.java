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

import service.adminS.ClassManageService;
import service.adminS.CourseManageService;
import service.adminS.TeacherManageService;

/**
 * �༶���� 2019��12��23��00:38:55
 * 
 * @author ��
 *
 */
public class ClassManage extends HttpServlet {

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

	// ��ȡ����ѧԺ�����
	private void getAllAcademy(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
			ClassManageService.me.getAllAcademy(out, pageNumber, pageSize, search);
		}
	}
	// ����Ա��ӿγ̿γ�
		private void addAcademy(HttpServletRequest request, HttpServletResponse response) throws Exception {

			ServletContext application = this.getServletContext();
			HttpSession session = request.getSession();

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			String name = request.getParameter("name");
			String memo = request.getParameter("memo");

			PrintWriter out = response.getWriter();

			if ((String) session.getAttribute("names") == null) {
				out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
			} else {
				ClassManageService.me.addAcademy(out, name, memo);
			}
		}

	// ��ȡ�����������ѧԺ�İ༶������ѧԺ�������
	private void getAllklass(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
			ClassManageService.me.getAllklass(out, pageNumber, pageSize, search);
		}
	}

	// ��ȡ���а༶ �� ������ѯ
	private void getClass(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			ClassManageService.me.getClass(out);
		}
	}

	// ��ȡȫ��ѧԺ-�༶-ѧ��������
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			ClassManageService.me.getAll(out);
		}
	}
}
