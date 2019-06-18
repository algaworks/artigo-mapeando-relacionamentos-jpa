package com.algaworks.artigo.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Exemplo {

	public static void main(String... string) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Funcionarios-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// FAÇA SEUS TESTES AQUI.

		entityManager.close();
		entityManagerFactory.close();
	}
}
