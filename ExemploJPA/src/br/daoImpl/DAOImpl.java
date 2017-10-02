package br.daoImpl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.dao.DAO;

public class DAOImpl<T,K> implements DAO<T, K> {
	protected EntityManager em;
	protected Class<T> classe;
	
	@SuppressWarnings("unchecked")
	public DAOImpl(EntityManager em) {
		this.em = em;
		classe = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass())
				.getActualTypeArguments()[0];
				
	}
	
	@Override
	public void insert(T entity) {
		try{
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		}catch (Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
		}
		
	}
	@Override
	public void update(T entity) {
		try{
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		}catch (Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
		}
		
	}
	@Override
	public void remove(K id) {
		T t = searchById(id);
		if ( t == null ) throw new NullPointerException("Entidade não encontrada");
		
		try{
			em.getTransaction().begin();
			em.remove(t);
			em.getTransaction().commit();
		}catch (Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
		}
	}
	@Override
	public T searchById(K id) {
		return em.find(classe, id);
	}
}
