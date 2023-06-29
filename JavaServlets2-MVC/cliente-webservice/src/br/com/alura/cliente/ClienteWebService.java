package br.com.alura.cliente;

import org.apache.http.client.fluent.Request;

public class ClienteWebService {

	public static void main(String[] args) throws Exception {

		String content = Request
				.Post("http://localhost:8080/gerenciador/empresas")
				.execute()
				.returnContent()
				.asString();
		
		System.out.println(content);
	}

}
