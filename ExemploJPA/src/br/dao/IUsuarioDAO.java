package br.dao;

import java.util.List;

import br.model.Usuario;

public interface IUsuarioDAO extends DAO<Usuario, Integer> {
	List<Usuario> listarTodos();
}

