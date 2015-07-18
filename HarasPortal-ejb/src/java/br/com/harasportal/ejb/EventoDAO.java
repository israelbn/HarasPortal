package br.com.harasportal.ejb;

import br.com.harasportal.entidades.Evento;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author maicon
 */
@Stateless
@LocalBean
public class EventoDAO {
    @PersistenceContext(unitName = "HarasPortalPU")
    private EntityManager em;

    public void persist(Object object) {
        em.merge(object);
    }
    
    public List<Evento> findByAll(){
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Evento.class));
        cq.orderBy(em.getCriteriaBuilder().desc(cq.from(Evento.class).get("data")));
        return em.createQuery(cq).getResultList();
    }
    
    public Evento findById(Long id){
        return em.find(Evento.class, id);
    }
    
}
