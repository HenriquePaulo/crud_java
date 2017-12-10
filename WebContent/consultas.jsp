<%@page import="br.edu.devmedia.crud.dto.PreferenciaMusicalDTO"%>
<%@page import="br.edu.devmedia.crud.dto.PessoaDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultas</title>
<link rel="stylesheet" href="css/global.css"/>
</head>
<body>

	<jsp:include page="cabecalho.jsp"/>
	
	<div class="main">
		<form action="">
			<jsp:include page="msg.jsp"/>
			
			<fieldset>
				<legend>Consultas</legend> 
				<h1>Consultas</h1>
				
				<table width="100%" border="1" cellspacing="0" cellpadding="5">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>Sexo</th>
							<th>CPF</th>
							<th>Dt. Nasc.</th>
							<th>Endereço</th>
							<th>Cidade</th>
							<th>UF</th>
							<th colspan="3">Ações</th>
						</tr>
					</thead>
					<tbody>
					<%
						List<PessoaDTO> listaPessoas = (List<PessoaDTO>) request.getAttribute("listaPessoas");
						for (PessoaDTO pessoa : listaPessoas) {
					%>
						<tr>
							<td class="alignCenter"><%= pessoa.getIdPessoa() %></td>
							<td class="alignLeft"><%= pessoa.getNome() %></td>
							<td class="alignCenter"><%= pessoa.getSexo() %></td>
							<td class="alignCenter"><%= pessoa.getCpf() %></td>
							<td class="alignCenter"><%= pessoa.getDtNasc() %></td>
							<td class="alignLeft"><%= pessoa.getEndereco().getLogradouro() %></td>
							<td class="alignLeft"><%= pessoa.getEndereco().getCidade().getDescricao() %></td>
							<td class="alignLeft"><%= pessoa.getEndereco().getCidade().getUf().getDescricao() %></td>
							<td class="alignCenter">
								<%
									String preferencias = "";
									for (PreferenciaMusicalDTO preferencia : pessoa.getPreferencias()) {
										preferencias += "[" + preferencia.getDescricao() + "] ";
									}
								%>
								<a href="javascript:void(0)" title="Preferências" onclick="alert('<%=preferencias%>');">
									<img alt="Preferências" src="img/preference.png"/>
								</a>
							</td>
							<td class="alignCenter">
								<a href="main?acao=editarPessoa&id_pessoa=<%= pessoa.getIdPessoa() %>" title="Editar">
									<img alt="Edição de Pessoa" src="img/edit.png"/>
								</a>
							</td>
							<td class="alignCenter">
								<a href="main?acao=removerPessoa&id_pessoa=<%= pessoa.getIdPessoa() %>" title="Deletar">
									<img alt="Remoção de Pessoa" src="img/delete.png"/>
								</a>
							</td>
						</tr>
					<%
						}
					%>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
	
	<jsp:include page="rodape.jsp"/>

</body>
</html>