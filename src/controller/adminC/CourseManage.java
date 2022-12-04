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
 * 课程管理中心
 *  2019年12月21日01:17:14
 * @author 董
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
			throw new RuntimeException("调用方法出错！");
		}
	}

	// 获取所有课程 及 表格的查询
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
			out.println("{\"ok\":false,\"msg\":\"请求出错，登录已失效请重新登录！\"}");
		} else {
			CourseManageService.me.getCourse(out, pageNumber, pageSize, search);
		}
	}

	// 管理员添加课程课程
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
			out.println("{\"ok\":false,\"msg\":\"请求出错，登录已失效请重新登录！\"}");
		} else {
			CourseManageService.me.addCourse(out, title, credit, class_time, memo);
		}
	}

	// 管理员修改课程信息
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
			out.println("{\"ok\":false,\"msg\":\"请求出错，登录已失效请重新登录！\"}");
		} else {
			CourseManageService.me.updateCourse(out, id, title, credit, class_time);
		}
	}

	// 管理员删除某个课程及批量删除
	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String order = request.getParameter("order");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"请求出错，登录已失效请重新登录！\"}");
		} else {
			if(order.equals("1")) {
				//删除单个
				CourseManageService.me.deleteCourse(out, id ,order);
			}else if(order.equals("2")){
				//批量删除
				String[] ids = id.split(",");
				for (int i = 0; i <ids.length ;i++){
					CourseManageService.me.deleteCourse(out, ids[i],order);
	            }
				out.println("{\"ok\":true,\"msg\":\"所选课程删除成功！\"}");
			}
			
		}
	}
}
