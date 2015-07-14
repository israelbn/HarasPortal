/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.ejb;

import br.com.harasportal.entidades.Animal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

    
}
