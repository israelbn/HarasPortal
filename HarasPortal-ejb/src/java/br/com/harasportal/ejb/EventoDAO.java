package br.com.harasportal.ejb;

import br.com.harasportal.entidades.Evento;
import br.com.harasportal.entidades.Evento_;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
//        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(Evento.class));
//        return em.createQuery(cq).getResultList();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(Evento.class);
        Root<Evento> evento = cq.from(Evento.class);
        cq.where(builder.lessThanOrEqualTo(evento.<Calendar>get(Evento_.data), Calendar.getInstance()));
        return em.createQuery(cq).getResultList();
    }
    
    public Evento findById(Long id){
        return em.find(Evento.class, id);
    }
    
}
