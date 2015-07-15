/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.mb;

import br.com.harasportal.ejb.AnimalDAO;
import br.com.harasportal.entidades.Animal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
    
    @PostConstruct
    public void init(){
        listaAnimais();
    }
    
    public void listaAnimais(){
        listaAnimaisAVenda = animalDAO.listaAnimaisAVenda();
    }

    // get e set
    public List<Animal> getListaAnimaisAVenda() {
        return listaAnimaisAVenda;
    }

    public void setListaAnimaisAVenda(List<Animal> listaAnimaisAVenda) {
        this.listaAnimaisAVenda = listaAnimaisAVenda;
    }
}
