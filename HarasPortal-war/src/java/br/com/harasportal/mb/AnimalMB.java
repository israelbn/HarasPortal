/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.mb;

import br.com.harasportal.ejb.AnimalDAO;
import br.com.harasportal.entidades.Animal;
import br.com.harasportal.entidades.Filiacao;
import br.com.harasportal.enumeration.Classificacao;
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
public class AnimalMB {
    
    @EJB
    private AnimalDAO animalDAO;
    private Animal animal;
    private List<Animal> listaAnimais;
    private StatusTela tela;

    /**
     * Creates a new instance of AnimalMB
     */
    public AnimalMB() {
    }
    
    /*
    * Método para iniciar os atributos do MB
    */
    @PostConstruct
    public void init(){
        tela = StatusTela.listando;
        findByAll();
    }
    
    public void novo(){
        animal = new Animal();
        animal.setAtivo('s');
        animal.setFiliacao(new Filiacao());
        animal.getFiliacao().addAnimal(animal);
        tela = StatusTela.inserindo;
    }
    
    public void findByAll(){
        listaAnimais = animalDAO.findByAll();
    }
    
    public void salvar(){
        animalDAO.persist(animal);
        
        findByAll();
        tela = StatusTela.listando;
    }
    
    public void voltarLista(){
        tela = StatusTela.listando;
    }
    
    // Lista as classificações do animal
    public Classificacao[] getClassificacoes(){
        return Classificacao.values();
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

    public StatusTela getTela() {
        return tela;
    }

    public void setTela(StatusTela tela) {
        this.tela = tela;
    }
    
}
