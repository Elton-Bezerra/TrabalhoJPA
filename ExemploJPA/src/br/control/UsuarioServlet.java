package br.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.dao.IEnderecoDAO;
import br.dao.IUsuarioDAO;
import br.daoImpl.EnderecoDAOImpl;
import br.daoImpl.UsuarioDAOImpl;
import br.model.Endereco;
import br.model.Usuario;
import br.singleton.FactorySingleton;

@WebServlet("/usuarioServlet")
public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	EntityManager em = FactorySingleton.getInstance().createEntityManager();
	IUsuarioDAO udao = new UsuarioDAOImpl(em);
	IEnderecoDAO edao = new EnderecoDAOImpl(em);
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		listar(req);
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String retorno = "";
		String acao = req.getParameter("acao");
		
		switch(acao){
		case "cadastrar":
			cadastrar(req);
			retorno = "usuarioServlet?acao=listar";
			break;
		case "listar":
			listar(req);
			retorno = "index.jsp";
			break;
		case "excluir":
			excluir(req);
			listar(req);// busca a lista
			retorno = "index.jsp";
			break;
		case "carregar":
			//abrir página com dados preenchidos para alteração
			listar(req);
			carregar(req);
			retorno = "alterarUsuario.jsp";
			break;
		case "alterar":
			try {
				alterar(req);
				listar(req);
				retorno = "usuarioServlet?acao=listar";
				
			} catch (Exception e) {

				
				e.printStackTrace();
			}
			
			break;
		
		default:
			break;
		}
		
		
		req.getRequestDispatcher(retorno).forward(req, resp);
	}
	
	
	private void cadastrar(HttpServletRequest request){
		Usuario novo = new Usuario();
		Endereco endereco = new Endereco();
		
		novo.setNome(request.getParameter("nome"));
		novo.setEmail(request.getParameter("email"));
		novo.setTelefone(request.getParameter("telefone"));
		Date dataNasc = null;
		try {
			dataNasc = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("nascimento"));
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		novo.setNascimento(dataNasc);
		endereco.setLogradouro(request.getParameter("logradouro"));
		endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
		endereco.setEstado(request.getParameter("estado"));
		endereco.setCep(Integer.parseInt(request.getParameter("cep").replace("-", "")));		
		novo.setEndereco(endereco);
		
		udao.insert(novo);
		edao.insert(endereco);
		
	}
	
	private void excluir(HttpServletRequest req){
		int codigo = Integer.parseInt(req.getParameter("id"));
		udao.remove(codigo);
		
	}
	
	
	private void listar(HttpServletRequest req) {
		List<Usuario> lista = udao.listarTodos();
		req.setAttribute("listaUsuarios", lista);		
	}
	
	private void carregar(HttpServletRequest req){

		
		int codigo = Integer.parseInt(req.getParameter("id"));
		Usuario usuario = new Usuario();
		usuario = udao.searchById(codigo);
		System.out.println("ENDEREÇO: " + usuario.getEndereco().getId());
		Endereco e = edao.searchById(usuario.getEndereco().getId());
		
		//coloca o usuario no request
		req.setAttribute("endereco", e);
		req.setAttribute("usuario", usuario);
	}
	
	private void alterar(HttpServletRequest request){
		Usuario usuario = new Usuario();
		Endereco endereco = new Endereco();
		usuario.setId(Integer.parseInt(request.getParameter("idUsuario")));
		System.out.println("Id do endereço: " + request.getParameter("idEndereco"));
		endereco.setId(Integer.parseInt(request.getParameter("idEndereco")));
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setTelefone(request.getParameter("telefone"));
		Date dataNasc = null;
		try {
			dataNasc = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("nascimento"));
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		usuario.setNascimento(dataNasc);
		endereco.setLogradouro(request.getParameter("logradouro"));
		endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
		endereco.setEstado(request.getParameter("estado"));
		endereco.setCep(Integer.parseInt(request.getParameter("cep").replace("-", "")));		
		usuario.setEndereco(endereco);
		
		udao.update(usuario);
		edao.update(endereco);
		
		
	}

}
