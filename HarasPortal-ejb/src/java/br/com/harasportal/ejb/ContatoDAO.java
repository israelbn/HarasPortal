package br.com.harasportal.ejb;

import br.com.harasportal.entidades.Contato;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author maicon
 */
@Stateless
public class ContatoDAO{
    @PersistenceContext(unitName = "HarasPortalPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Contato> findByAll(){
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Contato.class));
        
        return em.createQuery(cq).getResultList();
    }
    
    public Contato findById(Long id){
        return em.find(Contato.class, id);
    }
    
    
}
