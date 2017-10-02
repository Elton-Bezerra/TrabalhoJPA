package br.dao;

import java.util.List;

import br.model.Endereco;

public interface IEnderecoDAO extends DAO<Endereco, Integer> {
	List<Endereco> listarTodos();
}
