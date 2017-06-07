package fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fiap.entity.Livro;

public class LivroHelper {
	
	private EntityManager em;

	public LivroHelper(EntityManager em) {
		this.em = em;
	}

	
	public Livro salvar(Livro livro) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(livro);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
			// } finally {
			// em.close();
		}
		return livro;
	}

	@SuppressWarnings("unchecked")
	public List<Livro> listar() {
		Query query = em.createQuery("select f from Livro f");
		return query.getResultList();
	}

	public Livro buscar(int id) {
		Livro f = em.find(Livro.class, id);
		return f;
	}

	public Livro editar(Livro livro) {
		em.getTransaction().begin();
		Livro f = em.merge(livro);
		em.getTransaction().commit();
		return f;
	}

	public void remover(Livro livro) {
		em.getTransaction().begin();
		em.remove(livro);
		em.getTransaction().commit();
	}
	
}
