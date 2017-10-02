package br.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.dao.IEnderecoDAO;
import br.model.Endereco;

public class EnderecoDAOImpl extends DAOImpl<Endereco, Integer> implements IEnderecoDAO {

	public EnderecoDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Endereco> listarTodos() {
		TypedQuery<Endereco> query = em.createQuery("from Endereco", Endereco.class);
			return query.getResultList();
	}

}
