package br.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.dao.IUsuarioDAO;
import br.model.Usuario;

public class UsuarioDAOImpl extends DAOImpl<Usuario, Integer> implements IUsuarioDAO {

	public UsuarioDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Usuario> listarTodos() {
		TypedQuery<Usuario> query = em.createQuery("from Usuario", Usuario.class);
		return query.getResultList();
	}

}
