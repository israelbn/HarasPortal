/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.mb;

import br.com.harasportal.ejb.AnimalDAO;
import br.com.harasportal.entidades.Animal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Israel
 */
@ManagedBean
@ViewScoped
public class AnimalMB {
    
    @EJB
    private AnimalDAO animalDAO;
    private Animal animal;
    private List<Animal> listaAnimais;

    /**
     * Creates a new instance of AnimalMB
     */
    public AnimalMB() {
    }
    
    public void findByAll(){
        listaAnimais = animalDAO.findByAll();
    }

    // get e set
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public List<Animal> getListaAnimais() {
        return listaAnimais;
    }

    public void setListaAnimais(List<Animal> listaAnimais) {
        this.listaAnimais = listaAnimais;
    }
    
}
