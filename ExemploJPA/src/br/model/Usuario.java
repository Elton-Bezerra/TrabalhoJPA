package br.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@SequenceGenerator(name="codUsuario", sequenceName="seqUsuario")
public class Usuario {
	@Id
	@GeneratedValue(generator="codUsuario", strategy=GenerationType.AUTO)
	private int id;
	private String nome;
	private String telefone;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date nascimento;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;
	
	public String formatar()
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(getNascimento());
	}
	
	
	public Endereco getEndereco(){
		return endereco;
	}
	public void setEndereco(Endereco endereco){
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	
}
