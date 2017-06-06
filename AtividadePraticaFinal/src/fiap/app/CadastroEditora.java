package fiap.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import fiap.entity.Autor;
import fiap.entity.Editora;
import fiap.entity.Livro;
import fiap.helper.EditoraHelper;

public class CadastroEditora {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("atividade-pratica-final");
		EntityManager em = emf.createEntityManager();
		EditoraHelper dao = new EditoraHelper(em);
		StringBuilder builder = new StringBuilder();
		boolean sair = false;
		boolean voltar = false;

		while (!sair) {

			Object[] opcoes = { "A-Editora", "B-Autor", "C-Livro", "0-Sair" };
			String op = (String) JOptionPane.showInputDialog(null, "Escolha uma opção:", "Atividade",
					JOptionPane.PLAIN_MESSAGE, null, opcoes, "A-Editora");

			switch (op.substring(0, 1)) {

			case "A":

				while (!voltar) {

					Object[] acoes = { "1-Criar Editora", "2-Listar Editora", "3-Buscar Editora", "4-Alterar Editora",
							"5-Remover Editora", "9-Voltar" };
					String ac = (String) JOptionPane.showInputDialog(null, "Escolha uma ação:", "Editora",
							JOptionPane.PLAIN_MESSAGE, null, acoes, "1-Criar Editora");

					switch (ac.substring(0, 1)) {

					case "1":
						builder = new StringBuilder();
						montarEditora(em, builder);
						break;

					case "2":
						builder = new StringBuilder();
						listarEditora(dao, builder);
						break;

					case "3":
						builder = new StringBuilder();
						buscarEditora(dao, builder);
						break;

					case "4":
						builder = new StringBuilder();
						editaEditora(dao, builder);
						break;

					case "5":
						removerEditora(dao);
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

	}

	private static void montarEditora(EntityManager em, StringBuilder builder) {
		String nomeEditora = "";
		String nomeAutor = "";
		String nomeLivro = "";

		while (nomeLivro.isEmpty()) {
			nomeLivro = (String) JOptionPane.showInputDialog(null, "Preencha com o nome da editora:", "Editora",
					JOptionPane.PLAIN_MESSAGE);
		}

		while (nomeAutor.isEmpty()) {
			nomeAutor = (String) JOptionPane.showInputDialog(null, "Preencha com o nome do autor:", "Autor",
					JOptionPane.PLAIN_MESSAGE);
		}

		while (nomeEditora.isEmpty()) {
			nomeEditora = (String) JOptionPane.showInputDialog(null, "Preencha com o nome do livro:", "Livro",
					JOptionPane.PLAIN_MESSAGE);
		}

		Livro livro = new Livro();
		Autor autor = new Autor();
		Editora editora = new Editora();

		livro.setAutor(autor);
		livro.setNome(nomeLivro);

		autor.setEditora(editora);
		autor.setNome(nomeAutor);
		List<Livro> livros = new ArrayList<>();
		livros.add(livro);
		autor.setLivros(livros);

		editora.setNome(nomeEditora);
		List<Autor> autores = new ArrayList<>();
		autores.add(autor);
		editora.setAutores(autores);

		Editora editoraSalvo = incluirEditora(em, editora);

		builder.append("ID: " + editoraSalvo.getId() + "\n");
		builder.append("Nome: " + editoraSalvo.getNome() + "\n");
		for (Autor aut : editoraSalvo.getAutores()) {
			builder.append("> Autor: " + aut.getNome() + "\n");
			for (Livro liv : aut.getLivros()) {
				builder.append(">> Livro: " + liv.getNome() + "\n");
			}
		}
		JOptionPane.showMessageDialog(null, builder.toString(), "Alterar Editora", 1);
	}

	private static Editora incluirEditora(EntityManager em, Editora editora) {
		EditoraHelper dao = new EditoraHelper(em);
		try {
			return dao.salvar(editora);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private static void listarEditora(EditoraHelper dao, StringBuilder builder) {
		List<Editora> editoras = dao.listar();

		if (!editoras.isEmpty()) {
			for (Editora item : editoras) {
				builder.append("ID: " + item.getId() + "\n");
				builder.append("Nome: " + item.getNome() + "\n");
				for (Autor aut : item.getAutores()) {
					builder.append("> Autor: " + aut.getNome() + "\n");
					for (Livro liv : aut.getLivros()) {
						builder.append(">> Livro: " + liv.getNome() + "\n");
					}
				}
			}
			JOptionPane.showMessageDialog(null, builder.toString(), "Listar Editora", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Registro não foi encontrado.", "Listar Editora", 1);
		}

	}

	private static void buscarEditora(EditoraHelper dao, StringBuilder builder) {
		int id;
		String cod = "";
		while (cod.isEmpty()) {
			cod = (String) JOptionPane.showInputDialog(null, "Preencha com o id da editora que deseja buscar:",
					"Buscar Editora", JOptionPane.PLAIN_MESSAGE);
		}
		id = new Integer(cod);
		Editora editoraSalvo = dao.buscar(id);

		if (editoraSalvo != null) {
			builder.append("ID: " + editoraSalvo.getId() + "\n");
			builder.append("Nome: " + editoraSalvo.getNome() + "\n");
			for (Autor aut : editoraSalvo.getAutores()) {
				builder.append("> Autor: " + aut.getNome() + "\n");
				for (Livro liv : aut.getLivros()) {
					builder.append(">> Livro: " + liv.getNome() + "\n");
				}
			}
			JOptionPane.showMessageDialog(null, builder.toString(), "Buscar Editora", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Registro não foi encontrado.", "Buscar Editora", 1);
		}
	}

	private static void editaEditora(EditoraHelper dao, StringBuilder builder) {
		int id;
		String cod = "";
		while (cod.isEmpty()) {
			cod = (String) JOptionPane.showInputDialog(null, "Preencha com o id da editora que deseja alterar:",
					"Alterar Editora", JOptionPane.PLAIN_MESSAGE);
		}
		id = new Integer(cod);
		Editora editoraSalvo = dao.buscar(id);

		if (editoraSalvo != null) {

			String nomeEditora = "";
			while (nomeEditora.isEmpty()) {
				nomeEditora = (String) JOptionPane.showInputDialog(null, "Preencha com o novo nome da editora:",
						"Alterar Editora", JOptionPane.PLAIN_MESSAGE);
			}
			editoraSalvo.setNome(nomeEditora);
			dao.editar(editoraSalvo);

			builder.append("ID: " + editoraSalvo.getId() + "\n");
			builder.append("Nome: " + editoraSalvo.getNome() + "\n");
			builder.append("Autores: " + editoraSalvo.getAutores() + "\n");
			JOptionPane.showMessageDialog(null, builder.toString(), "Alterar Editora", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Registro não foi encontrado.", "Alterar Editora", 1);
		}
	}

	private static void removerEditora(EditoraHelper dao) {
		int id;
		String cod = "";
		while (cod.isEmpty()) {
			cod = (String) JOptionPane.showInputDialog(null, "Preencha com o id da editora que deseja remover:",
					"Remover Editora", JOptionPane.PLAIN_MESSAGE);
		}
		id = new Integer(cod);
		Editora editoraSalvo = dao.buscar(id);

		if (editoraSalvo != null) {
			dao.remover(editoraSalvo);
			JOptionPane.showMessageDialog(null, "Registro removido!", "Remover Editora", 1);

		} else {
			JOptionPane.showMessageDialog(null, "Registro não foi encontrado.", "Remover Editora", 1);
		}
	}

}
