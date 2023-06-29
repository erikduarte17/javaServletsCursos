package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/entrada")
public class AutorizacaoFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {}
	@Override
	public void destroy() {}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		
		HttpSession session = request.getSession();
		boolean usuarioValidoOuNao = session.getAttribute("usuarioLogado") == null;
		boolean acaoEhSegura = ! (paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
		
		if (usuarioValidoOuNao && acaoEhSegura) {
			response.sendRedirect("entrada?acao=LoginForm");
			return;
		}
		
		chain.doFilter(request, response);
	}

}
