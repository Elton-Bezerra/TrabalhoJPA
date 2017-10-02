<!-- Declarando as variáveis para listar os usuários na tela -->
<%@page import="br.daoImpl.UsuarioDAOImpl"%>
<%@page import="br.singleton.FactorySingleton"%>
<%@page import="br.dao.IUsuarioDAO"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="br.model.Usuario" %>
<%@page import="java.util.List" %>
<%
	EntityManager em = FactorySingleton.getInstance().createEntityManager();
	IUsuarioDAO udao = new UsuarioDAOImpl(em);
	List<Usuario> lista = udao.listarTodos();
	
%>
<%@include file="pgs/topo.jsp"%>
<!-- Exibição da lista de usuários -->
<div class="col-lg-8">
	<div class="panel panel-default">
		<!-- Título do panel -->
		<div class="panel-heading">
			<h3>Lista de Usuários</h3>
		</div>
		<!--  FIM PANEL HEADING -->	
		<div class="panel-body">
			<div class="dataTable_wrapper">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Nome</th>
							<th>Email</th>
							<th>Telefone</th>
							<th>Nascimento</th>
							
						</tr>
					</thead>
					<!-- Fim da tablehead -->
					
					<tbody>
						<c:forEach var="user" items="<%=lista %>"> 	
							<tr>
								<td style="width: 10%;">${user.id}</td>
								<td>${user.nome}</td>
								<td>${user.email}</td>
								<td>${user.telefone}</td>
								<td>${user.formatar()}</td>
								<td>
									<c:url value="usuarioServlet" var="alterar">
										<c:param name="acao" value="carregar"/>
										<c:param name="id" value="${user.id}"/>
									</c:url>
									<button disabled="disabled" class="btn btn-warning">Alterar</button>
								</td>
								<td>	
									<c:url value="usuarioServlet" var="deletar">
										<c:param name="acao" value="excluir"/>
										<c:param name="id" value="${user.id}"/>
									</c:url>
									<a href="${deletar}" onclick="return confirm ('Deseja excluir?')" class="btn btn-danger">Excluir</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- Fechando Panel Body Tag -->
	</div>
</div>
<!-- Sessão de alteração do usuário -->
<!-- Sessão de cadastro de usuário -->
<div class="col-lg-4">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Cadastrar Usuário</h3>
			</div>
			<div class="panel-body">
				<form class="horizontal-form" action="usuarioServlet" method="post">
					<input type="hidden" name="acao" value="alterar">
					<section class="col-md-12">
						
						<input type="hidden" name="idUsuario" value="${usuario.id }">
						<input type="hidden" name="idEndereco" value="${usuario.endereco.id }">
						
						<label for="nome">Nome:</label>
						<input required type="text" class="form-control" name="nome" value="${usuario.nome}">
						
						<label for="email">Email:</label>
						<input required type="email" class="form-control" name="email" value="${usuario.email }">
						
						<label for="telefone">Telefone:</label>
						<input required type="text" class="form-control" name="telefone" value="${usuario.telefone}">
						
						<label for="nascimento">Data de nascimento:</label>
						<input required type="date" class="form-control" name="nascimento" value="${usuario.nascimento}">
						
  						<label for="logradouro"> Logradouro: </label> 
						<input required class="form-control" name="logradouro" type="text" value="${endereco.logradouro }"> 
										
						<label for="estado"> Estado: </label>
						<input required type="text" class="form-control" name="estado" value="${endereco.estado }">
						
						<label for="numero"> Número: </label> 
						<input required class="form-control" name="numero" type="text" value="${endereco.numero }"> 
										

						<label for="cep"> CEP: </label> 
						<input required class="form-control" name="cep" onkeypress="return MM_formtCep(event,this,'#####-###');" size="10" maxlength="9" value="${endereco.cep }">
						
						</br>
						<button type="submit" class="btn btn-success">Alterar</button>
						<a href="usuarioServlet?acao=listar" class="btn btn-default">Voltar</a>																				
						
					</section>
				
				</form>
			</div>
		</div>
	
	</div>
</div>
</body>
</html>