package s.dic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class DicServlet
 */
@WebServlet(
		//description = "Dictionary Servlet", 
		urlPatterns = { 
				"/find/*",
				"/read/*",
				"/edit/*",
				"/save/*",
				"/signin",
				"/signout",
				"/signup",
				"/upload",
				"/download"
		}, 
		initParams = { 
				@WebInitParam(name = "one", value = "foo"), 
				@WebInitParam(name = "two", value = "bar")
		},
		loadOnStartup = 1
)
@MultipartConfig(
		fileSizeThreshold = 2_097_152, // 2MB
		maxFileSize = 5_242_880L, // 5MB
		maxRequestSize = 10_485_760L // 10MB
	)
public class DicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private volatile int DIC_ID_SEQ = 1;
	private Map<String,Map<Integer,Dic>> dic_repo = new Hashtable<>();
	
	private static final Map<String,User> user_repo = new Hashtable<>();
	static {
		user_repo.put("jack", new User("jack", "jack@somenet.org", "jack"));
		user_repo.put("jane", new User("jane", "jane@somenet.org", "jane"));
		user_repo.put("mike", new User("mike", "mike@somenet.org", "mike"));
	}
	
	private static final String SESSION_KEY_USERNAME = "username";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println(request.getMethod() + " " + request.getRequestURI());
		
		String servlet_path = get_servlet_path(request);
		
		if (request.getSession().getAttribute(SESSION_KEY_USERNAME) == null &&
				! servlet_path.equals("/signin") && ! servlet_path.equals("/signup")) {
			response.sendRedirect(get_context_path(request) + "/signin");
			return;
		}
		
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
			case "/signin":
				signin_form(request, response);
				break;
			case "/signout":
				signout(request, response);
				break;
			case "/signup":
				signup_form(request, response);
				break;
			case "/upload":
				upload_form(request, response);
				break;
			case "/download":
				download(request, response);
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
		
		System.out.println(request.getMethod() + " " + request.getRequestURI());
		
		String servlet_path = get_servlet_path(request);
		
		if (request.getSession().getAttribute(SESSION_KEY_USERNAME) == null &&
				! servlet_path.equals("/signin") && ! servlet_path.equals("/signup")) {
			response.sendRedirect(get_context_path(request) + "/signin");
			return;
		}
		
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
			case "/upload":
				upload(request, response);
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
	
	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER find()");
		
		String kw = tokenize_path_info(request)[0].trim();
		
		PrintWriter out = response.getWriter();
		
		String name = (String) request.getSession().getAttribute(SESSION_KEY_USERNAME);
		
		List<Dic> dics = null;
		if (kw.length() > 0) {
			System.out.println("find() kw=" + kw);
			dics = find_all(name, kw);
		} else {
			System.out.println("find() no kw");
			dics = find_all(name);
		}
		
		request.setAttribute("kw", kw);
		request.setAttribute("dics", dics);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/find.jsp");
		rd.forward(request, response);
	}
	
	private List<Dic> find_all(String name) {
		
		Map<Integer,Dic> dic_book = this.dic_repo.get(name);
		if (dic_book != null) {
			return new ArrayList<>(dic_book.values());
		} else {
			return new ArrayList<>();
		}
	}
	
	private List<Dic> find_all(String name, String kw) {
		
		List<Dic> dics = new ArrayList<>();
		
		if (kw.length() > 0) {
			Map<Integer,Dic> dic_book = this.dic_repo.get(name);
			if (dic_book != null) {
				for (int dic_id : dic_book.keySet()) {
					Dic dic = dic_book.get(dic_id);
					String dic_txt = dic.getTxt();
					if (dic_txt != null && dic_txt.contains(kw)) {
						dics.add(dic);
					}
				}
			}
		}
		
		return dics;
	}
	
	private void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER read()");
		
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
		
		String name = (String) request.getSession().getAttribute(SESSION_KEY_USERNAME);
		
		if (dic_id > 0) {
			System.out.println("read() dic_id=" + dic_id);
			Dic dic = find_one(name, dic_id);
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
	
	private Dic find_one(String name, int dic_id) {
		
		Dic dic = null;
		
		if (dic_id > 0) {
			Map<Integer,Dic> dic_book = this.dic_repo.get(name);
			if (dic_book != null) {
				dic = dic_book.get(dic_id);
			}
		}
		
		if (dic == null) dic = new Dic();
		
		return dic;
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER edit()");
		
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
		
		String name = (String) request.getSession().getAttribute(SESSION_KEY_USERNAME);
		
		if (dic_id > 0) {
			System.out.println("edit() dic_id=" + dic_id);
			Dic dic = find_one(name, dic_id);
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
		
		System.out.println("ENTER save()");
		
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
		
		String name = (String) request.getSession().getAttribute(SESSION_KEY_USERNAME);
		
		if (txt != null) {
			System.out.println("save() txt=[" + txt + "]");
			txt = txt.trim();
			//txt = new String(txt.getBytes("8859_1"), "utf-8");
			System.out.println("save() txt=[" + txt + "]");
			
			if (dic_id > 0) {
				System.out.println("save() dic_id=" + dic_id);
				synchronized(this) {
					save(name, dic_id, txt);
				}
			} else {
				synchronized(this) {
					dic_id = this.DIC_ID_SEQ++;
					System.out.println("save() dic_id=" + dic_id);
					save(name, dic_id, txt);
				}
			}
			response.sendRedirect(get_context_path(request) + "/read/" + dic_id);
		} else {
			System.out.println("save() no txt");
			response.sendRedirect(get_context_path(request) + "/find");
		}
	}
	
	private Dic save(String name, int dic_id, String txt) {
		Map<Integer,Dic> dic_book = this.dic_repo.get(name);
		if (dic_book == null) {
			dic_book = new LinkedHashMap<>();
			this.dic_repo.put(name, dic_book);
		}
		Dic dic = new Dic(dic_id, txt);
		dic_book.put(dic_id, dic);
		return dic;
	}
	
	private void signin_form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER signin_form()");
		
		HttpSession sess = request.getSession();
		if (sess.getAttribute(SESSION_KEY_USERNAME) != null) {
			System.out.println("signin_form() not null in session");
			response.sendRedirect(get_context_path(request) + "/find");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/signin.jsp");
			rd.forward(request, response);
		}
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER signin()");
		
		HttpSession sess = request.getSession();
		if (sess.getAttribute(SESSION_KEY_USERNAME) != null) {
			System.out.println("signin() not null in session");
			response.sendRedirect(get_context_path(request) + "/find");
			return;
		}
		
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("signin() name=[" + name + "]");
		System.out.println("signin() pass=[" + pass + "]");
		if (name == null || name.equals("") || pass == null || pass.equals("")) {
			request.setAttribute("signin_failed", "username or password missing");
			if (name != null) request.setAttribute("username", name);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/signin.jsp");
			rd.forward(request, response);
		}
		Maybe<User> maybe = signin(name, pass);
		if (maybe.getMessage() != null) {
			request.setAttribute("signin_failed", maybe.getMessage());
			if (name != null) request.setAttribute("username", name);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/signin.jsp");
			rd.forward(request, response);
		} else {
			sess.setAttribute(SESSION_KEY_USERNAME, name);
			request.changeSessionId();
			response.sendRedirect(get_context_path(request) + "/find");
		}
	}
	
	private Maybe<User> signin(String name, String pass) {
		
		Maybe<User> maybe = new Maybe<>();
		
		if (DicServlet.user_repo.containsKey(name)) {
			User user = DicServlet.user_repo.get(name);
			if (pass.equals(user.getPass())) {
				maybe.setValue(user);
			} else {
				maybe.setMessage("password not correct");
			}
		} else {
			maybe.setMessage("no user named " + name);
		}
		
		return maybe;
	}
	
	private void signout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("ENTER signout()");
		
		HttpSession sess = request.getSession();
		sess.invalidate();
		response.sendRedirect(get_context_path(request) + "/signin");
	}
	
	private void signup_form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER signup_form()");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp");
		rd.forward(request, response);
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER signup()");
		
		String name = request.getParameter("username");
		String mail = request.getParameter("email");
		String pass = request.getParameter("password");
		String confirm = request.getParameter("repassword");
		System.out.println("signin() name=[" + name + "]");
		System.out.println("signin() mail=[" + mail + "]");
		System.out.println("signin() pass=[" + pass + "]");
		System.out.println("signin() confirm=[" + confirm + "]");
		if (name == null || name.equals("") || mail == null || mail.equals("") ||
				pass == null || pass.equals("") || confirm == null || confirm.equals("")) {
			request.setAttribute("signin_failed", "missing info required");
			if (name != null) request.setAttribute("username", name);
			if (mail != null) request.setAttribute("email", mail);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/signin.jsp");
			rd.forward(request, response);
		}
		if (! pass.equals(confirm)) {
			request.setAttribute("signin_failed", "passwords not matched");
			if (name != null) request.setAttribute("username", name);
			if (mail != null) request.setAttribute("email", mail);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/signin.jsp");
			rd.forward(request, response);
		}
		Maybe<User> maybe = signup(name, mail, pass);
		if (maybe.getMessage() != null) {
			request.setAttribute("signin_failed", maybe.getMessage());
			if (name != null) request.setAttribute("username", name);
			if (mail != null) request.setAttribute("email", mail);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/signin.jsp");
			rd.forward(request, response);
		} else {
			request.getSession().setAttribute(SESSION_KEY_USERNAME, name);
			request.changeSessionId();
			response.sendRedirect(get_context_path(request) + "/find");
		}
	}
	
	private Maybe<User> signup(String name, String mail, String pass) {
		
		Maybe<User> maybe = new Maybe<>();
		
		if (DicServlet.user_repo.containsKey(name)) {
			maybe.setMessage("username alreday used");
		} else {
			User user = new User(name, mail, pass);
			DicServlet.user_repo.put(name, user);
			maybe.setValue(user);
		}
		
		return maybe;
	}
	
	private void upload_form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER upload_form()");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view/upload.jsp");
		rd.forward(request, response);
	}
	
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENTER upload()");
		
		String name = (String) request.getSession().getAttribute(SESSION_KEY_USERNAME);
		
		Part file_part = request.getPart("file");
		if (file_part != null && file_part.getSize() > 0) {
			upload(name, file_part);
		}
		
		response.sendRedirect(get_context_path(request) + "/find");
	}
	
	private void upload(String name, Part file_part) throws IOException {
		
		String file_name = file_part.getSubmittedFileName();
		System.out.println(file_name + " uploaded");
		
		StringBuffer lines = new StringBuffer();
		
		InputStream is = file_part.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		while (br.ready()) {
			String line = br.readLine();
			System.out.println("[" + line + "]");
			if (line.matches("^={4,}$")) {
				treat_lines(name, lines);
				lines.setLength(0);
			} else {
				if (lines.length() > 0) lines.append("\r\n");
				lines.append(line);
			}
		}
		treat_lines(name, lines);
		br.close();
	}
	
	private void treat_lines(String name, StringBuffer lines) {
		
		String txt = lines.toString().trim();
		if (txt.length() > 0) {
			synchronized(this) {
				int dic_id = this.DIC_ID_SEQ++;
				save(name, dic_id, txt);
			}
		}
	}
	
	private void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("ENTER download()");
		
		String name = (String) request.getSession().getAttribute(SESSION_KEY_USERNAME);
		
		List<Dic> dics = find_all(name);
		
		StringBuffer sb = new StringBuffer();
		for (Dic dic : dics) {
			if (sb.length() > 0) sb.append("\r\n").append("====").append("\r\n");
			sb.append(dic.getTxt());
		}
		
		String filename = "dic.txt";
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentType("application/octet-stream");

        ServletOutputStream sos = response.getOutputStream();
        sos.write(sb.toString().getBytes("utf-8"));
	}
}
