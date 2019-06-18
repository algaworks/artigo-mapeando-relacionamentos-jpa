package com.algaworks.artigo.jpa.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "departamento_codigo")
	private Departamento departamento;
	
	@OneToOne
	@JoinColumn(name = "estacao_trabalho_codigo")
	private EstacaoTrabalho estacaoTrabalho;
	
	@OneToOne(mappedBy = "funcionario")
	private Endereco endereco;
	
	@OneToOne
	@JoinTable(name = "funcionario_vaga_estacionamento", 
				joinColumns = { @JoinColumn(name = "funcionario_codigo") },
				inverseJoinColumns = { @JoinColumn(name = "vaga_estacionamento_codigo") })
	private VagaEstacionamento vagaEstacionamento;
	
	@ManyToMany(mappedBy = "funcionarios")
	private Collection<Projeto> projetos;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public EstacaoTrabalho getEstacaoTrabalho() {
		return estacaoTrabalho;
	}

	public void setEstacaoTrabalho(EstacaoTrabalho estacaoTrabalho) {
		this.estacaoTrabalho = estacaoTrabalho;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public VagaEstacionamento getVagaEstacionamento() {
		return vagaEstacionamento;
	}

	public void setVagaEstacionamento(VagaEstacionamento vagaEstacionamento) {
		this.vagaEstacionamento = vagaEstacionamento;
	}

	public Collection<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(Collection<Projeto> projetos) {
		this.projetos = projetos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
