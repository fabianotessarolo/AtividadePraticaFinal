package fiap.app;

import java.util.List;

import javax.swing.JOptionPane;

import fiap.entity.Livro;
import fiap.helper.LivroHelper;

public class LivroApp {
	

	protected static void listarLivro(LivroHelper dao, StringBuilder builder) {
		List<Livro> livros = dao.listar();

		if (!livros.isEmpty()) {
			for (Livro item : livros) {
				builder.append("ID: " + item.getId() + "\n");
				builder.append("Nome: " + item.getNome() + "\n");
				
			}
			JOptionPane.showMessageDialog(null, builder.toString(), "Listar Livro", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Registro não foi encontrado.", "Listar Livro", 1);
		}

	}

	protected static void buscarLivro(LivroHelper dao, StringBuilder builder) {
		int id;
		String cod = "";
		while (cod.isEmpty()) {
			cod = (String) JOptionPane.showInputDialog(null, "Preencha com o id da livro que deseja buscar:",
					"Buscar Livro", JOptionPane.PLAIN_MESSAGE);
		}
		id = new Integer(cod);
		Livro livroSalvo = dao.buscar(id);

		if (livroSalvo != null) {
			builder.append("ID: " + livroSalvo.getId() + "\n");
			builder.append("Nome: " + livroSalvo.getNome() + "\n");
			JOptionPane.showMessageDialog(null, builder.toString(), "Buscar Livro", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Registro não foi encontrado.", "Buscar Livro", 1);
		}
	}
	
}
