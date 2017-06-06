package fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fiap.entity.Editora;

public class EditoraHelper {
	
	private EntityManager em;

	public EditoraHelper(EntityManager em) {
		this.em = em;
	}

	public void salvar(Editora editora) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(editora);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
			// } finally {
			// em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Editora> listar() {
		Query query = em.createQuery("select f from Editora f");
		return query.getResultList();
	}

	public Editora buscar(int id) {
		Editora f = em.find(Editora.class, id);
		return f;
	}

	public Editora editar(Editora editora) {
		em.getTransaction().begin();
		Editora f = em.merge(editora);
		em.getTransaction().commit();
		return f;
	}

	public void remover(Editora editora) {
		em.getTransaction().begin();
		em.remove(editora);
		em.getTransaction().commit();
	}
}