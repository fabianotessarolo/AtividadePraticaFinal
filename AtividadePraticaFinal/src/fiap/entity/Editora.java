package fiap.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="editora")
public class Editora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ID") 
	private int id;

	@Column(name="NOME", length=45) 
	private String nome;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="editora")
	private List<Autor> autores = new ArrayList<Autor>();

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

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "Editora [id=" + id + ", nome=" + nome + "]";
	}
	
}
