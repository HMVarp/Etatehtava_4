package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.Dao;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    public Login() {
        System.out.println("Login.Login()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login.doGet()");
		String logout = request.getParameter("logout");
		if(logout!=null) {
			HttpSession session = request.getSession(true);
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login.doPost()");
		String tunnus = request.getParameter("tunnus");
		System.out.println("tunnus=" + tunnus);
		String salasana = request.getParameter("salasana");
		System.out.println("salasana=" + salasana);
		String salattuS = request.getParameter("salattuS");
		System.out.println("salattuS=" +salattuS);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Dao dao = new Dao();
		String nimi=dao.findUser(tunnus, salattuS);
		if(nimi!=null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("kayttaja", nimi);
			out.print("Listaaasiakkaat.jsp");
		}else {
			out.print("Index.jsp?unknown=1");
		}		
	}

}
