package fiap.app;

import java.util.List;

import javax.swing.JOptionPane;

import fiap.entity.Autor;
import fiap.entity.Livro;
import fiap.helper.AutorHelper;

public class AutorApp {
	

	protected static void listarAutor(AutorHelper dao, StringBuilder builder) {
		List<Autor> autors = dao.listar();

		if (!autors.isEmpty()) {
			for (Autor item : autors) {
				builder.append("ID: " + item.getId() + "\n");
				builder.append("Nome: " + item.getNome() + "\n");
				for (Livro liv : item.getLivros()) {
					builder.append("> Livros: " + liv.getNome() + "\n");
				}
			}
			JOptionPane.showMessageDialog(null, builder.toString(), "Listar Autor", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Registro não foi encontrado.", "Listar Autor", 1);
		}

	}

	protected static void buscarAutor(AutorHelper dao, StringBuilder builder) {
		int id;
		String cod = "";
		while (cod.isEmpty()) {
			cod = (String) JOptionPane.showInputDialog(null, "Preencha com o id da autor que deseja buscar:",
					"Buscar Autor", JOptionPane.PLAIN_MESSAGE);
		}
		id = new Integer(cod);
		Autor autorSalvo = dao.buscar(id);

		if (autorSalvo != null) {
			builder.append("ID: " + autorSalvo.getId() + "\n");
			builder.append("Nome: " + autorSalvo.getNome() + "\n");
			for (Livro liv : autorSalvo.getLivros()) {
				builder.append("> Livros: " + liv.getNome() + "\n");
			}
			JOptionPane.showMessageDialog(null, builder.toString(), "Buscar Autor", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Registro não foi encontrado.", "Buscar Autor", 1);
		}
	}
	
}
