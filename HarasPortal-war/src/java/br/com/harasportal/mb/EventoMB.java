package br.com.harasportal.mb;

import br.com.harasportal.ejb.EventoDAO;
import br.com.harasportal.entidades.Evento;
import br.com.harasportal.enumeration.StatusTela;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author maicon
 */
@ManagedBean
@ViewScoped
public class EventoMB {
    
    @EJB
    private EventoDAO eventoDAO;
    
    private Evento evento;
    private List<Evento> listEventos;
    private StatusTela tela;
    
    /**
     * Creates a new instance of EventoMB
     */
    public EventoMB() {}
    
    @PostConstruct
    public void init(){
        tela = StatusTela.listando;
        findByAll();
    }
    
    public void novo(){
        evento = new Evento();
        tela = StatusTela.inserindo;
    }
    
    public void editar(){
        tela = StatusTela.inserindo;
    }
    
    public void salvar(){
        eventoDAO.persist(evento);
        if(tela == StatusTela.inserindo)
            findByAll();
        tela = StatusTela.listando;
    }
    
    public void detalhar(){
        tela = StatusTela.detalhar;
    }
    
    public void voltarLista(){
        tela = StatusTela.listando;
    }
    
    public void findByAll(){
        listEventos = eventoDAO.findByAll();
    }
    
        //Médoto usado para validar a quantidade de palavras digitadas pelo cliente
    public void messageValidator(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        int countWords = ((String) value).split("[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ]+").length;
        if (countWords > 100) {
            FacesMessage msg = new FacesMessage("A descrição deve conter no máximo 100 palavras, foram encontradas: "+countWords);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ValidatorException(msg);
        }
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getListEventos() {
        return listEventos;
    }

    public void setListEventos(List<Evento> listEventos) {
        this.listEventos = listEventos;
    }

    public StatusTela getTela() {
        return tela;
    }

    public void setTela(StatusTela tela) {
        this.tela = tela;
    }
    
}