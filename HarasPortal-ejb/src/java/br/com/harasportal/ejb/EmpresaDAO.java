package br.com.harasportal.ejb;

import br.com.harasportal.entidades.Empresa;
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
public class EmpresaDAO {
    @PersistenceContext(unitName = "HarasPortalPU")
    private EntityManager em;

    public void persist(Object object) {
        em.merge(object);
    }
    
    public List<Empresa> findByAll(){
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Empresa.class));
        return em.createQuery(cq).getResultList();
    }
    
    public Empresa findById(Long id){
        return em.find(Empresa.class, id);
    }
    

}
