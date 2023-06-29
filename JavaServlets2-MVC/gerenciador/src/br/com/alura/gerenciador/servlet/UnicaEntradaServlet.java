package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.service.Acao;

/**
 * Servlet implementation class UnicaEntradaServlet
 */
@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paramAcao = request.getParameter("acao");
		
//		HttpSession session = request.getSession();
//		boolean usuarioValidoOuNao = session.getAttribute("usuarioLogado") == null;
//		boolean acaoEhSegura = ! (paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
//		
//		if (usuarioValidoOuNao && acaoEhSegura) {
//			response.sendRedirect("entrada?acao=LoginForm");
//			return;
//		}
		
		String className = "br.com.alura.gerenciador.service." + paramAcao;
		String dados = null;
		
		try {
			Class<?> classe = Class.forName(className);
			
			@SuppressWarnings("deprecation")
			Acao acao = (Acao) classe.newInstance();
			dados = acao.execute(request, response);	
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		String[] tipoEEndereco = dados.split(":");
		if (tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/" + tipoEEndereco[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEEndereco[1]);
		}
	
	}

}
