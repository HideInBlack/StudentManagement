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

import service.adminS.CourseManageService;
import service.shareS.LoginSomeService;
import utils.DzySomeUtil;

/**
 * �γ̹�������
 *  2019��12��21��01:17:14
 * @author ��
 *
 */
public class CourseManage extends HttpServlet {

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

	// ��ȡ���пγ� �� ���Ĳ�ѯ
	private void getCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
			CourseManageService.me.getCourse(out, pageNumber, pageSize, search);
		}
	}

	// ����Ա��ӿγ̿γ�
	private void addCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String title = request.getParameter("title");
		String credit = request.getParameter("credit");
		String class_time = request.getParameter("class_time");
		String memo = request.getParameter("memo");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			CourseManageService.me.addCourse(out, title, credit, class_time, memo);
		}
	}

	// ����Ա�޸Ŀγ���Ϣ
	private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String credit = request.getParameter("credit");
		String class_time = request.getParameter("class_time");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"���������¼��ʧЧ�����µ�¼��\"}");
		} else {
			CourseManageService.me.updateCourse(out, id, title, credit, class_time);
		}
	}

	// ����Աɾ��ĳ���γ̼�����ɾ��
	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
			if(order.equals("1")) {
				//ɾ������
				CourseManageService.me.deleteCourse(out, id ,order);
			}else if(order.equals("2")){
				//����ɾ��
				String[] ids = id.split(",");
				for (int i = 0; i <ids.length ;i++){
					CourseManageService.me.deleteCourse(out, ids[i],order);
	            }
				out.println("{\"ok\":true,\"msg\":\"��ѡ�γ�ɾ���ɹ���\"}");
			}
			
		}
	}
}
