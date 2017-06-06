package fiap.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fiap.entity.Autor;
import fiap.entity.Editora;
import fiap.entity.Livro;
import fiap.helper.EditoraHelper;

public class CadastroEditora {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("atividade-pratica-final");
		EntityManager em = emf.createEntityManager();

		Livro livro = new Livro();
		Autor autor = new Autor();
		Editora editora = new Editora();
		
		livro.setAutor(autor);
		livro.setNome("Livro 1");
		
		autor.setEditora(editora);
		autor.setNome("Autor 1");
		List<Livro> livros = new ArrayList<>();
		livros.add(livro);
		autor.setLivros(livros);

		editora.setNome("Editora 1");
		List<Autor> autores = new ArrayList<>();
		autores.add(autor);
		editora.setAutores(autores);
		
		incluirEditora(em, editora);
		Editora editoraSalvo = buscarEditora(em, editora.getId());
		editoraSalvo.setNome("Editora 2");
		editarEditora(em, editoraSalvo);
		removerEditora(em, editoraSalvo);
	}
	
	private static void incluirEditora(EntityManager em, Editora editora){ 
		EditoraHelper dao = new EditoraHelper(em);
		try {
			dao.salvar(editora);
			System.out.println("Editora OK"); 
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static Editora buscarEditora(EntityManager em, int idEditora){ 
		EditoraHelper dao = new EditoraHelper(em);
		try {
			return dao.buscar(idEditora);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private static void editarEditora(EntityManager em, Editora editora){ 
		EditoraHelper dao = new EditoraHelper(em);
		try {
			dao.editar(editora);
			System.out.println("Editora Alterada"); 
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void removerEditora(EntityManager em, Editora editora){ 
		EditoraHelper dao = new EditoraHelper(em);
		try {
			dao.remover(editora);
			System.out.println("Editora Removida"); 
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
