package controller.shareC;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.shareS.LoginSomeService;
import utils.DzySomeUtil;

/**
 * 2019年11月26日09:41:43 登录相关类容器
 * 
 * @author 董
 *
 */
//@WebServlet("/*.login")
public class LoginSome extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 使用一个Servlet来处理多个请求 自定义反射方法 doGet doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求的URI地址信息
		String url = request.getRequestURI();
		// 截取其中的方法名
		String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		Method method = null;
		try {
			// 使用反射机制获取在本类中声明了的方法
			method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 执行方法
			method.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException("调用方法出错！");
		}
	}

	/**
	 * 登录判断 2019年11月27日10:49:26
	 * 
	 */
	private void loginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("进入登录判断...");
		// 获取 Application对象
		ServletContext application = this.getServletContext();
		// 获取 session
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");

		if (DzySomeUtil.sessionCheck(session)) {
			System.out.println("session判断：已经有账号处于登录状态！");
			out.println("{\"ok\":true,\"name\":\"" + (String)session.getAttribute("names") + "\",\"msg\":\"已经在登录状态，无需再次登录！\" ,\"href\":\"/StudentManagement/src/share/index.html\",\"roles\":"+ (String)session.getAttribute("roles")+"}");
		} else {
			System.out.println("session判断：登录失效，正在重新登录！");
			LoginSomeService.me.loginCheck(name, pwd, out, session );
		}
	}

	/**
	 * 退出登录2019年12月4日10:27:13
	 * 
	 */
	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		// 获取 session
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("names");
		// 使得session失效
		session.invalidate();

		PrintWriter out = response.getWriter();
	
		if(name == null) {
			out.println("{\"ok\":false,\"msg\":\"登录已经失效，将直接退出到登录界面！\",\"href\":\"/StudentManagement/src/share/login.html\"}");
		}else {
			out.println("{\"ok\":true,\"name\":\"" + name + "\",\"msg\":\"退出成功，可重新登录！\" ,\"href\":\"/StudentManagement/src/share/login.html\"}");
			System.out.println("session判断：退出登录！");
		}
	}
	/**
	 * get session值
	 */
	private void getSession(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		// 获取 session
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("names");
		String role = (String) session.getAttribute("roles");
		PrintWriter out = response.getWriter();
		if(name != null) {
			out.println("{\"ok\":true,\"name\":\"" + (String)session.getAttribute("names") + "\",\"msg\":\"进入主页！\" ,\"roles\":"+ (String)session.getAttribute("roles")+"}");
		}else {
			out.println("{\"ok\":false,\"msg\":\"登录已经失效，请重新登录！\" }");
		}
	
	
	}

}
