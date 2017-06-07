package fiap.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import fiap.entity.Autor;
import fiap.entity.Editora;
import fiap.entity.Livro;
import fiap.helper.EditoraHelper;

public class EditoraApp {

	protected static void montarEditora(EntityManager em, StringBuilder builder) {
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

	protected static Editora incluirEditora(EntityManager em, Editora editora) {
		EditoraHelper dao = new EditoraHelper(em);
		try {
			return dao.salvar(editora);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	protected static void listarEditora(EditoraHelper dao, StringBuilder builder) {
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

	protected static void buscarEditora(EditoraHelper dao, StringBuilder builder) {
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

	protected static void editaEditora(EditoraHelper dao, StringBuilder builder) {
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

	protected static void removerEditora(EditoraHelper dao) {
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
