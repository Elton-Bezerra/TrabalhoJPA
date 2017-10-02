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
							<th colspan="2" class="text-center">Ação</th>
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
										<c:param name="idEndereco" value="${user.endereco}"/>
									</c:url>
									<a href="${alterar}" class="btn btn-warning">Alterar</a>
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

<!-- Sessão de cadastro de usuário -->
<div class="col-lg-4">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Cadastrar Usuário</h3>
			</div>
			<div class="panel-body">
				<form class="horizontal-form" action="usuarioServlet" method="post">
					<input type="hidden" name="acao" value="cadastrar">
					<section class="col-md-12">
						<label for="nome">Nome:</label>
						<input required type="text" class="form-control" name="nome">
						
						<label for="email">Email:</label>
						<input required type="email" class="form-control" name="email">
						
						<label for="telefone">Telefone:</label>
						<input required type="text" class="form-control" name="telefone">
						
						<label for="nascimento">Data de nascimento:</label>
						<input required type="date" class="form-control" name="nascimento">
						
  						<label for="logradouro"> Logradouro: </label> 
						<input required class="form-control" name="logradouro" type="text"> 
										
						<label for="estado"> Estado: </label>
						<input required type="text" class="form-control" name="estado">
						
						<label for="numero"> Número: </label> 
						<input required class="form-control" name="numero" type="text"> 
										

						<label for="cep"> CEP: </label> 
						<input required class="form-control" name="cep" onkeypress="return MM_formtCep(event,this,'#####-###');" size="10" maxlength="9">
						
						</br>
						<button type="submit" class="btn btn-success">Cadastrar</button>
					</section>
				
				</form>
			</div>
		</div>
	
	</div>
</div>
</body>
</html>