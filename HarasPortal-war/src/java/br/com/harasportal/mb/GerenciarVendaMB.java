/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.mb;

import br.com.harasportal.ejb.AnimalDAO;
import br.com.harasportal.entidades.Animal;
import br.com.harasportal.enumeration.StatusTela;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Israel
 */
@ManagedBean
@ViewScoped
public class GerenciarVendaMB {
    
    @EJB
    private AnimalDAO animalDAO;
    private List<Animal> listaAnimais;
    private Animal animal;
    private StatusTela tela;

    public GerenciarVendaMB() {
    }
    
    @PostConstruct
    public void init(){
        tela = StatusTela.listando;
        findByAll();
    }
    
    public void findByAll(){
        listaAnimais = animalDAO.findByAll();
    }
    
    public void gerenciar(Animal animal){
        this.animal = animal;
        tela = StatusTela.editando;
    }
    
    public void salvar(){
        animalDAO.persist(animal);
        
        tela = StatusTela.listando;
    }
    
    public void cancelar(){
        tela = StatusTela.listando;
    }

    //get e set
    public AnimalDAO getAnimalDAO() {
        return animalDAO;
    }

    public void setAnimalDAO(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }

    public List<Animal> getListaAnimais() {
        return listaAnimais;
    }

    public void setListaAnimais(List<Animal> listaAnimais) {
        this.listaAnimais = listaAnimais;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public StatusTela getTela() {
        return tela;
    }

    public void setTela(StatusTela tela) {
        this.tela = tela;
    }
    
}
