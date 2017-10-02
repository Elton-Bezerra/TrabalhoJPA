package br.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactorySingleton {
	
	private static EntityManagerFactory factory;
	
	private FactorySingleton(){ }
	
	public static EntityManagerFactory getInstance(){
		if(factory == null){
			factory = Persistence.createEntityManagerFactory("bancoExemploJPA");
		}
		return factory;
	}

}
