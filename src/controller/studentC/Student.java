package controller.studentC;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.studentS.StudentService;


/**
 * 学生自己的容器 2019年12月24日14:44:49
 * 
 * @author 董
 *
 */
public class Student extends HttpServlet {

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

	// 获取学生 所以成绩（个人查询）
	private void getMyScore(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
			out.println("{\"ok\":false,\"msg\":\"请求出错，登录已失效请重新登录！\"}");
		} else {
			StudentService.me.getMyScore(out, pageNumber, pageSize, studentId,search ,key);
		}
	}

	// 修改学生信息
	private void updateOne(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String studentId = request.getParameter("studentId");
		String password = request.getParameter("password");
		String tel = request.getParameter("tel");
		String memo = request.getParameter("memo");

		PrintWriter out = response.getWriter();

		if ((String) session.getAttribute("names") == null) {
			out.println("{\"ok\":false,\"msg\":\"请求出错，登录已失效请重新登录！\"}");
		} else {
			StudentService.me.updateOne(out, studentId,password, tel, memo);
		}
	}
}
