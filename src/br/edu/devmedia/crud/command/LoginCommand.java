package br.edu.devmedia.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.edu.devmedia.crud.bo.UsuarioBO;
import br.edu.devmedia.crud.dto.UsuarioDTO;
import br.edu.devmedia.crud.exception.NegocioException;

public class LoginCommand implements Command {
	
	private UsuarioBO usuarioBO;

	private String proximo;
	
	public String execute(HttpServletRequest request) {
		// Sempre iniciar com o caso onde o erro pode acontecer
		proximo = "login.jsp";
		usuarioBO = new UsuarioBO();
		
		String usuario = request.getParameter("login");
		String senha = request.getParameter("senha");

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsuario(usuario);
		usuarioDTO.setSenha(senha);
		
		try {
			if (usuarioBO.validarUsuario(usuarioDTO)) {
				proximo = "index.jsp";
			}
		} catch (NegocioException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}
		request.getSession().setAttribute("usuario", usuarioDTO);
		return proximo;
	}
	
}
