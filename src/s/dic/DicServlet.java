package s.dic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DicServlet
 */
@WebServlet(
		//description = "Dictionary Servlet", 
		urlPatterns = { 
				"/find", "/find/*",
				"/read", "/read/*",
				"/create",
				"/edit/*",
				"/save", "/save/*",
				"/signin",
				"/signout",
				"/signup"
		}, 
		initParams = { 
				@WebInitParam(name = "one", value = "foo"), 
				@WebInitParam(name = "two", value = "bar")
		},
		loadOnStartup = 1
)
//@MultipartConfig(
//		fileSizeThreshold = 1_048_576, // 1MB
//		maxFileSize = 2_097_152L, // 2MB
//		maxRequestSize = 5_242_880L // 5MB
//	)
public class DicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private volatile int DIC_ID_SEQ = 1;
	private Map<Integer,Dic> dic_repo = new LinkedHashMap<>();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		String servlet_path = get_servlet_path(request);
		
		String context_path = get_context_path(request);
		String path_info = get_path_info(request);
		System.out.println(context_path + servlet_path + path_info);
		
		switch (servlet_path) {
			case "/find":
				find(request, response);
				break;
			case "/read":
				read(request, response);
				break;
			case "/create":
				edit(request, response);
				break;
			case "/edit":
				edit(request, response);
				break;
			case "/signin_form":
				signin(request, response);
				break;
			case "/signout":
				break;
			case "/signup_form":
				signup(request, response);
				break;
			default:
				response.sendRedirect(get_context_path(request) + "/find");
		}
		
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		
//		PrintWriter out = response.getWriter();
//
//		print_infos(request, out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		String servlet_path = get_servlet_path(request);

		String context_path = get_context_path(request);
		String path_info = get_path_info(request);
		System.out.println(context_path + servlet_path + path_info);
		
		switch (servlet_path) {
			case "/save":
				save(request, response);
				break;
			case "/signin":
				signin(request, response);
				break;
			case "/signup":
				signup(request, response);
				break;
			default:
				response.sendRedirect(get_context_path(request) + "/find");
		}
	}
	
	private void print_infos(HttpServletRequest request, PrintWriter out) {
		
		out.println("<ul>");
		out.println("<li>" + "AuthType" + "=" + request.getAuthType());
		out.println("<li>" + "CharacterEncoding" + "=" + request.getCharacterEncoding());
		out.println("<li>" + "ContentLength" + "=" + request.getContentLength());
		out.println("<li>" + "ContentLengthLong" + "=" + request.getContentLengthLong());
		out.println("<li>" + "ContentType" + "=" + request.getContentType());
		out.println("<li>" + "ContextPath" + "=" + request.getContextPath());
		out.println("<li>" + "LocalAddr" + "=" + request.getLocalAddr());
		out.println("<li>" + "LocalName" + "=" + request.getLocalName());
		out.println("<li>" + "LocalPort" + "=" + request.getLocalPort());
		out.println("<li>" + "Method" + "=" + request.getMethod());
		out.println("<li>" + "PathInfo" + "=" + request.getPathInfo());
		out.println("<li>" + "PathTranslated" + "=" + request.getPathTranslated());
		out.println("<li>" + "Protocol" + "=" + request.getProtocol());
		out.println("<li>" + "QueryString" + "=" + request.getQueryString());
		out.println("<li>" + "RemoteAddr" + "=" + request.getRemoteAddr());
		out.println("<li>" + "RemoteHost" + "=" + request.getRemoteHost());
		out.println("<li>" + "RemotePort" + "=" + request.getRemotePort());
		out.println("<li>" + "RemoteUser" + "=" + request.getRemoteUser());
		out.println("<li>" + "RequestURI" + "=" + request.getRequestURI());
		out.println("<li>" + "RequestURL" + "=" + request.getRequestURL());
		out.println("<li>" + "Scheme" + "=" + request.getScheme());
		out.println("<li>" + "ServerName" + "=" + request.getServerName());
		out.println("<li>" + "ServerPort" + "=" + request.getServerPort());
		out.println("<li>" + "ServletPath" + "=" + request.getServletPath());
		out.println("</ul>");
		
		Enumeration<String> header_names = request.getHeaderNames();
		out.println("<ul>");
		while (header_names.hasMoreElements()) {
			String header_name = header_names.nextElement();
			String header_value = request.getHeader(header_name);
			out.println("<li>" + header_name + "=" + header_value);
		}
		out.println("</ul>");
	}
	
	private String get_context_path(HttpServletRequest request) {
		String context_path = request.getContextPath();
		return context_path;
	}
	
	private String get_servlet_path(HttpServletRequest request) {
		String servlet_path = request.getServletPath();
		if (servlet_path == null) servlet_path = "/find";
		return servlet_path;
	}
	
	private String get_path_info(HttpServletRequest request) {
		String path_info = request.getPathInfo();
		if (path_info == null) path_info = "/";
		return path_info;
	}
	
	private String[] tokenize_path_info(HttpServletRequest request) {
		String path_info = get_path_info(request);
		if (path_info.startsWith("/")) path_info = path_info.substring(1);
		if (path_info.endsWith("/")) path_info = path_info.substring(0, path_info.length() - 1);
		String[] tokens = path_info.split("/");
		return tokens;
	}
	
	private void find(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String kw = tokenize_path_info(request)[0].trim();
		
		PrintWriter out = response.getWriter();
		
		List<Dic> dics = null;
		if (kw.length() > 0) {
			System.out.println("find() kw=" + kw);
			dics = find_all(kw);
		} else {
			System.out.println("find() no kw");
			dics = find_all();
		}
		
		request.setAttribute("kw", kw);
		request.setAttribute("dics", dics);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/find.jsp");
		rd.forward(request, response);
	}
	
	private List<Dic> find_all() {
		
		List<Dic> dics = new ArrayList<>();
		
		for (int dic_id : this.dic_repo.keySet()) {
			Dic dic = this.dic_repo.get(dic_id);
			dics.add(dic);
		}
		
		return dics;
	}
	
	private List<Dic> find_all(String kw) {
		
		List<Dic> dics = new ArrayList<>();
		
		if (kw.length() > 0) {
			for (int dic_id : this.dic_repo.keySet()) {
				Dic dic = this.dic_repo.get(dic_id);
				String dic_txt = dic.getTxt();
				if (dic_txt != null && dic_txt.contains(kw)) {
					dics.add(dic);
				}
			}
		}
		
		return dics;
	}
	
	private void read(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int dic_id = 0;
		String _dic_id = tokenize_path_info(request)[0].trim();
		if (_dic_id.length() > 0) {
			try {
				dic_id = Integer.parseInt(_dic_id);
			} catch (NumberFormatException nfe) {
				dic_id = 0;
			}
		}
		
		PrintWriter out = response.getWriter();
		
		if (dic_id > 0) {
			System.out.println("read() dic_id=" + dic_id);
			Dic dic = find_one(dic_id);
			if (dic != null) {
				request.setAttribute("dic", dic);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/read.jsp");
				rd.forward(request, response);
				return;
			} else {
				System.out.println("read() no dic");
			}
		} else {
			System.out.println("read() no dic_id");
		}
		
		response.sendRedirect(get_context_path(request) + "/find");
	}
	
	private Dic find_one(int dic_id) {
		
		Dic dic = null;
		
		if (dic_id > 0) {
			dic = this.dic_repo.get(dic_id);
		} else {
			dic = new Dic();
		}
		
		return dic;
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int dic_id = 0;
		String _dic_id = tokenize_path_info(request)[0].trim();
		if (_dic_id.length() > 0) {
			try {
				dic_id = Integer.parseInt(_dic_id);
			} catch (NumberFormatException nfe) {
				dic_id = 0;
			}
		}
		
		PrintWriter out = response.getWriter();
		
		if (dic_id > 0) {
			System.out.println("edit() dic_id=" + dic_id);
			Dic dic = find_one(dic_id);
			if (dic != null) {
				request.setAttribute("dic", dic);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/edit.jsp");
				rd.forward(request, response);
				return;
			} else {
				System.out.println("edit() no dic");
				response.sendRedirect(get_context_path(request) + "/find");
			}
		} else {
			System.out.println("edit() no dic_id");
			request.setAttribute("dic", new Dic());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/edit.jsp");
			rd.forward(request, response);
		}
	}
	
	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int dic_id = 0;
		String _dic_id = tokenize_path_info(request)[0].trim();
		if (_dic_id.length() > 0) {
			try {
				dic_id = Integer.parseInt(_dic_id);
			} catch (NumberFormatException nfe) {
				dic_id = 0;
			}
		}
		
		String txt = request.getParameter("txt");
		
		PrintWriter out = response.getWriter();
		
		if (txt != null) {
			System.out.println("save() txt=[" + txt + "]");
			txt = txt.trim();
			//txt = new String(txt.getBytes("8859_1"), "utf-8");
			System.out.println("save() txt=[" + txt + "]");
			
			if (dic_id > 0) {
				System.out.println("save() dic_id=" + dic_id);
				synchronized(this) {
					save(dic_id, txt);
				}
			} else {
				synchronized(this) {
					dic_id = this.DIC_ID_SEQ++;
					System.out.println("save() dic_id=" + dic_id);
					save(dic_id, txt);
				}
			}
			response.sendRedirect(get_context_path(request) + "/read/" + dic_id);
		} else {
			System.out.println("save() no txt");
			response.sendRedirect(get_context_path(request) + "/find");
		}
	}
	
	private Dic save(int dic_id, String txt) {
		Dic dic = new Dic(dic_id, txt);
		this.dic_repo.put(dic_id, dic);
		return dic;
	}
	
	private void signin_form(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void signout(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void signup_form(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) {
		
	}

}
