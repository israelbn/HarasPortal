/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.mb;

import br.com.harasportal.ejb.AnimalDAO;
import br.com.harasportal.entidades.Animal;
import br.com.harasportal.enumeration.StatusTela;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DMADESENV01
 */
@ManagedBean
@ViewScoped
public class VendaMB {
    @EJB
    private AnimalDAO animalDAO;
    private List<Animal> listaAnimaisAVenda;
    private Animal animal;
    private StatusTela tela;
    
    @PostConstruct
    public void init(){
        tela = StatusTela.listando;
        listaAnimais();
    }
    
    public void listaAnimais(){
        listaAnimaisAVenda = animalDAO.listaAnimaisAVenda();
    }
    
    public void comprar(Animal animal){
        this.animal = animal;
        tela = StatusTela.detalhar;
    }
    
    public void confirmarCompra(){
        animal.setComprado('s');
        animalDAO.persist(animal);
        
//        FacesMessage fm = new FacesMessage("Compra realizada com sucesso!");
//        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        
        FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Compra realizada com sucesso"));
        
        listaAnimais();
        tela = StatusTela.listando;
    }

    // get e set
    public List<Animal> getListaAnimaisAVenda() {
        return listaAnimaisAVenda;
    }

    public void setListaAnimaisAVenda(List<Animal> listaAnimaisAVenda) {
        this.listaAnimaisAVenda = listaAnimaisAVenda;
    }

    public StatusTela getTela() {
        return tela;
    }

    public void setTela(StatusTela tela) {
        this.tela = tela;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
