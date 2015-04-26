package s.dic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig fConfig) throws ServletException {}

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
    	HttpSession session = ((HttpServletRequest) request).getSession(false);
    	if (session == null || session.getAttribute(DicServlet.SESSION_KEY_USERNAME) == null) {
    		((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/signin");
    	} else {
    		chain.doFilter(request, response);
    	}
	}

    @Override
    public void destroy() {}
}
