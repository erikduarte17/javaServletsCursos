package br.com.alura.gerenciador.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramLogin = request.getParameter("login");
		String paramSenha = request.getParameter("senha");
		
		System.out.println("Criando login");

		Banco banco = new Banco();
		
		Usuario user = banco.existeUsuario(paramLogin, paramSenha);
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", user);
			return "redirect:entrada?acao=ListaEmpresas";
		}
		else
			return "redirect:entrada?acao=LoginForm";
		
		
	}

}
