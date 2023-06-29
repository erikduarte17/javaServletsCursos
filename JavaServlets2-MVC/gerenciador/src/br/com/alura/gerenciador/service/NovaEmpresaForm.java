package br.com.alura.gerenciador.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovaEmpresaForm implements Acao {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "forward:formNovaEmpresa.jsp";
	}

}
