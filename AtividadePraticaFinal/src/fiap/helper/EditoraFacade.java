package fiap.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fiap.entity.Editora;

public class EditoraFacade extends AbstractFacade<Editora> {

    @PersistenceContext(unitName = "atividade-pratica-final")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EditoraFacade() {
        super(Editora.class);
    }
    
}
