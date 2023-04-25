package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Myynti;
import model.dao.Dao;


@WebServlet("/myynnit/*")
public class Myynnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Myynnit() {
        System.out.println("Myynnit.Myynnit()");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Myynnit.doGet()");
		String hakusana = request.getParameter("hakusana");
		System.out.println(hakusana);
		Dao dao = new Dao();
		ArrayList<Myynti> myynnit;
		String strJSON = "";
		if (hakusana != null) {
			if (!hakusana.equals("")) {
				myynnit = dao.getAllItems(hakusana);
			} else {
				myynnit = dao.getAllItems();
			}
			strJSON = new Gson().toJson(myynnit);
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(strJSON);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Myynnit.doPost()");
		String strJSONInput = request.getReader().lines().collect(Collectors.joining());
		//System.out.println(strJSONInput); //onnistuu
		Myynti myynti = new Gson().fromJson(strJSONInput, Myynti.class);
		//System.out.println(myynti); //onnistuu
		Dao dao = new Dao();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(dao.addItem(myynti)) {
			out.println("{\"response\":1}");
		}else {
			out.println("{\"response\":0}");
		}
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Myynnit.doPut()");
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Myynnit.doDelete()");
		int id = Integer.parseInt(request.getParameter("asiakas_id"));
		Dao dao = new Dao();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(dao.removeItem(id)) {
			out.println("{\"response\":1}");
		}else {
			out.println("{\"response\":0}");
		}
	}

}
