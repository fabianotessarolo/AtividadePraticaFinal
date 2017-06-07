package fiap.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import fiap.helper.AutorHelper;
import fiap.helper.EditoraHelper;
import fiap.helper.LivroHelper;
/**
 * 
 * @author RM37981 / RM31863
 *
 */
public class Main {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("atividade-pratica-final");
		EntityManager em = emf.createEntityManager();
		
		StringBuilder builder = new StringBuilder();
		boolean sair = false;
		boolean voltar = false;

		while (!sair) {

			Object[] opcoes = { "A-Editora", "B-Autor", "C-Livro", "0-Sair" };
			String op = (String) JOptionPane.showInputDialog(null, "Escolha uma opção:", "Atividade",
					JOptionPane.PLAIN_MESSAGE, null, opcoes, "A-Editora");
			System.out.println("Comeco");
			switch (op.substring(0, 1)) {

			case "A":

				while (!voltar) {
					System.out.println("CaseA");
					Object[] acoes = { "1-Criar Editora", "2-Listar Editora", "3-Buscar Editora", "4-Alterar Editora",
							"5-Remover Editora", "9-Voltar" };
					String ac = (String) JOptionPane.showInputDialog(null, "Escolha uma ação:", "Editora",
							JOptionPane.PLAIN_MESSAGE, null, acoes, "1-Criar Editora");
					EditoraHelper dao = new EditoraHelper(em);
					
					switch (ac.substring(0, 1)) {

					case "1":
						builder = new StringBuilder();
						EditoraApp.montarEditora(em, builder);
						break;

					case "2":
						builder = new StringBuilder();
						EditoraApp.listarEditora(dao, builder);
						break;

					case "3":
						builder = new StringBuilder();
						EditoraApp.buscarEditora(dao, builder);
						break;

					case "4":
						builder = new StringBuilder();
						EditoraApp.editaEditora(dao, builder);
						break;

					case "5":
						EditoraApp.removerEditora(dao);
						break;

					default:
						voltar = true;
						break;
					}
				}
				break;			
			case "B":
				while (!voltar) {
					System.out.println("CaseB");
					Object[] acoes = { "1-Listar Autores", "2-Buscar Autores", "9-Voltar" };
					String ac = (String) JOptionPane.showInputDialog(null, "Escolha uma ação:", "Autores",
							JOptionPane.PLAIN_MESSAGE, null, acoes, "1-Listar Autores");
					AutorHelper dao = new AutorHelper(em);
					
					switch (ac.substring(0, 1)) {


					case "1":
						builder = new StringBuilder();
						AutorApp.listarAutor(dao, builder);
						break;

					case "2":
						builder = new StringBuilder();
						AutorApp.buscarAutor(dao, builder);
						break;

					default:
						voltar = true;
						break;
					}
				}
				break;
				
				
				
			case "C":
				while (!voltar) {
					System.out.println("CaseC");
					Object[] acoes = { "1-Listar Livros", "2-Buscar Livros", "9-Voltar" };
					String ac = (String) JOptionPane.showInputDialog(null, "Escolha uma ação:", "Livros",
							JOptionPane.PLAIN_MESSAGE, null, acoes, "1-Listar Livros");
					LivroHelper dao = new LivroHelper(em);
					
					switch (ac.substring(0, 1)) {


					case "1":
						builder = new StringBuilder();
						LivroApp.listarLivro(dao, builder);
						break;

					case "2":
						builder = new StringBuilder();
						LivroApp.buscarLivro(dao, builder);
						break;

					default:
						voltar = true;
						break;
					}
				}
				break;
			default:
				sair = true;
				break;
			}	
		}
		

		System.out.println("FIM");
		
	}

	

}
