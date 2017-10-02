package br.dao;

public interface DAO<T, K> {
	void insert(T entity);
	void update(T entity);
	void remove(K id);
	T searchById(K id);
}
