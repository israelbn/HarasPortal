package br.com.harasportal.ejb;

import br.com.harasportal.entidades.Animal;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Israel
 */
@Stateless
public class AnimalDAO {
    @PersistenceContext(unitName = "HarasPortalPU")
    private EntityManager em;

    public void persist(Object object) {
        em.merge(object);
    }
    
    public List<Animal> findByAll(){
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);
            Root<Animal> root = cq.from(Animal.class);
            cq.select(root);
            
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Animal findById(Long id){
        return em.find(Animal.class, id);
    }
    
    public List<Animal> listaAnimaisAVenda(){
        try {
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);
//            Root<Animal> root = cq.from(Animal.class);
//            cq.select(root);
//            
//            Predicate predicate = cb.and(cb.equal(root.get("ativo"), 's'),
//                    cq.);
//            cq.where(predicate);
            
            Query query = em.createQuery("SELECT a FROM Animal a WHERE "
                    + "a.ativo =:ativo AND "
                    + "a.comprado <>:comprado AND "
                    + "a.preco > 0");
            query.setParameter("ativo", 's');
            query.setParameter("comprado", 's');
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
