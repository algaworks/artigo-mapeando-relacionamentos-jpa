package com.algaworks.artigo.jpa;

import com.algaworks.artigo.jpa.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

public class Exemplo {

	public static void main(String... string) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("Funcionarios-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

//		relacionamentoManyToOne(entityManager);
//		relacionamentoOneToMany(entityManager);
//		relacionamentoOneToOne(entityManager);
//		relacionamentoOneToOneMapsId(entityManager);
//		relacionamentoOneToOneJoinTable(entityManager);
		relacionamentoManyToMany(entityManager);

		entityManager.close();
		entityManagerFactory.close();
	}

	public static void relacionamentoManyToOne(EntityManager entityManager) {
		Departamento departamento = new Departamento();
		departamento.setNome("Vendas");

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Alexandre");
		funcionario.setDepartamento(departamento);

		entityManager.getTransaction().begin();
		entityManager.persist(departamento);
		entityManager.persist(funcionario);
		entityManager.getTransaction().commit();

		entityManager.clear(); // Limpando a memória para o "find" abaixo ir diretamente no banco de dados.

		Funcionario funcionario2 = entityManager.find(Funcionario.class, funcionario.getCodigo());
		System.out.println(funcionario2.getDepartamento().getNome());
	}

	public static void relacionamentoOneToMany(EntityManager entityManager) {
		Departamento departamento = new Departamento();
		departamento.setNome("Vendas");

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Alexandre");
		funcionario.setDepartamento(departamento);

		departamento.setFuncionarios(Arrays.asList(funcionario));

		entityManager.getTransaction().begin();
		entityManager.persist(departamento);
		entityManager.persist(funcionario);
		entityManager.getTransaction().commit();

		entityManager.clear(); // Limpando a memória para o "find" abaixo ir diretamente no banco de dados.

		Departamento departamento2 = entityManager.find(Departamento.class, departamento.getCodigo());
		System.out.println(departamento2.getFuncionarios().iterator().next().getNome());
	}

	public static void relacionamentoOneToOne(EntityManager entityManager) {
		EstacaoTrabalho estacaoTrabalho = new EstacaoTrabalho();
		estacaoTrabalho.setBloco(1);
		estacaoTrabalho.setNumero(1);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Alexandre");
		funcionario.setEstacaoTrabalho(estacaoTrabalho);

		entityManager.getTransaction().begin();
		entityManager.persist(estacaoTrabalho);
		entityManager.persist(funcionario);
		entityManager.getTransaction().commit();

		entityManager.clear(); // Limpando a memória para o "find" abaixo ir diretamente no banco de dados.

		Funcionario funcionario2 = entityManager.find(Funcionario.class, funcionario.getCodigo());
		System.out.println(funcionario2.getEstacaoTrabalho().getBloco());

		entityManager.clear(); // Limpando a memória para o "find" abaixo ir diretamente no banco de dados.

		EstacaoTrabalho estacaoTrabalho2 = entityManager.find(EstacaoTrabalho.class, estacaoTrabalho.getCodigo());
		System.out.println(estacaoTrabalho2.getFuncionario().getNome());
	}

	public static void relacionamentoOneToOneMapsId(EntityManager entityManager) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Alexandre");

		Endereco endereco = new Endereco();
		endereco.setCep("38.000-000");
		endereco.setFuncionario(funcionario);

		entityManager.getTransaction().begin();
		entityManager.persist(endereco);
		entityManager.persist(funcionario); // Mesmo que a chamada de persist para endereço tenha ocorrido primeiro, funcionário é quem será salvo antes. Até pq o JPA sabe que endereco depende da PK de funcionário.
		entityManager.getTransaction().commit();

		entityManager.clear(); // Limpando a memória para o "find" abaixo ir diretamente no banco de dados.

		Funcionario funcionario2 = entityManager.find(Funcionario.class, funcionario.getCodigo());
		System.out.println(funcionario2.getEndereco().getCep());
	}

	public static void relacionamentoOneToOneJoinTable(EntityManager entityManager) {
		VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
		vagaEstacionamento.setLocalizacao("015");

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Alexandre");
		funcionario.setVagaEstacionamento(vagaEstacionamento);

		entityManager.getTransaction().begin();
		entityManager.persist(vagaEstacionamento);
		entityManager.persist(funcionario);
		entityManager.getTransaction().commit();

		entityManager.clear(); // Limpando a memória para o "find" abaixo ir diretamente no banco de dados.

		Funcionario funcionario2 = entityManager.find(Funcionario.class, funcionario.getCodigo());
		System.out.println(funcionario2.getVagaEstacionamento().getLocalizacao());
	}

	public static void relacionamentoManyToMany(EntityManager entityManager) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Alexandre");

		Projeto projeto = new Projeto();
		projeto.setNome("Reestruturação do Software");
		projeto.setFuncionarios(Arrays.asList(funcionario));

		entityManager.getTransaction().begin();
		entityManager.persist(projeto);
		entityManager.persist(funcionario);
		entityManager.getTransaction().commit();

		entityManager.clear(); // Limpando a memória para o "find" abaixo ir diretamente no banco de dados.

		Funcionario funcionario2 = entityManager.find(Funcionario.class, funcionario.getCodigo());
		System.out.println(funcionario2.getProjetos().iterator().next().getNome());
	}
}
