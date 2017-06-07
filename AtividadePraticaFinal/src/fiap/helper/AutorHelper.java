package fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fiap.entity.Autor;

public class AutorHelper {
	
	private EntityManager em;

	public AutorHelper(EntityManager em) {
		this.em = em;
	}

	
	public Autor salvar(Autor autor) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(autor);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
			// } finally {
			// em.close();
		}
		return autor;
	}

	@SuppressWarnings("unchecked")
	public List<Autor> listar() {
		Query query = em.createQuery("select f from Autor f");
		return query.getResultList();
	}

	public Autor buscar(int id) {
		Autor f = em.find(Autor.class, id);
		return f;
	}

	public Autor editar(Autor autor) {
		em.getTransaction().begin();
		Autor f = em.merge(autor);
		em.getTransaction().commit();
		return f;
	}

	public void remover(Autor autor) {
		em.getTransaction().begin();
		em.remove(autor);
		em.getTransaction().commit();
	}
	
}
