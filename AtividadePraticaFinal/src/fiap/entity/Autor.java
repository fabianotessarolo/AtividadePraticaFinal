package fiap.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="autor")
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ID") 
	private int id;

	@Column(name="NOME", length=45) 
	private String nome;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="ID_EDITORA") 
	private Editora editora;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="pedido") 
	private Set<Livro> livros = new LinkedHashSet<Livro>();

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}
}