package com.fas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fas.service.StudentService;

/**
 * Servlet implementation class AndroidIfRegisteredFaceServlet
 */
@WebServlet("/AndroidIfRegisteredFaceServlet")
public class AndroidIfRegisteredFaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidIfRegisteredFaceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 设置客户端的解码方式为utf-8
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		// 根据标示名获取表单所包含的参数
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		PrintWriter out = response.getWriter();// 回应请求
		String result;

		StudentService studentService = new StudentService();
		result = studentService.AndroidIfRegisteredFaceService(id, flag);

		out.write(result);
		out.flush();
		out.close();
		System.out.println(result);
	}

}
