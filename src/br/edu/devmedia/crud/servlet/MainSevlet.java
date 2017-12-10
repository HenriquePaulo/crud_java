package br.edu.devmedia.crud.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.devmedia.crud.command.AtualizarPessoaCommand;
import br.edu.devmedia.crud.command.CadastroPessoaCommand;
import br.edu.devmedia.crud.command.Command;
import br.edu.devmedia.crud.command.ConsultasPessoaCommand;
import br.edu.devmedia.crud.command.EditarPessoaCommand;
import br.edu.devmedia.crud.command.LoginCommand;
import br.edu.devmedia.crud.command.MontagemCadastroCommand;
import br.edu.devmedia.crud.command.RemoverPessoaCommand;

@WebServlet("/main")
public class MainSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, Command> comandos = new HashMap<String, Command>();

	@Override
	public void init() throws ServletException {
		comandos.put("login", new LoginCommand());
		comandos.put("montagemCadastro", new MontagemCadastroCommand());
		comandos.put("cadastroPessoa", new CadastroPessoaCommand());
		comandos.put("consultas", new ConsultasPessoaCommand());
		comandos.put("removerPessoa", new RemoverPessoaCommand());
		comandos.put("editarPessoa", new EditarPessoaCommand());
		comandos.put("atualizarPessoa", new AtualizarPessoaCommand());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxima = null;
		try {
			Command comando = verificarComand(acao);
			proxima = comando.execute(request);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		request.getRequestDispatcher(proxima).forward(request, response);
	}
	
	private Command verificarComand(String acao) {
		Command comando = null;
		for (String key : comandos.keySet()) {
			if (key.equalsIgnoreCase(acao)) {
				comando = comandos.get(key);
			}
		}
		return comando;
	}

}
