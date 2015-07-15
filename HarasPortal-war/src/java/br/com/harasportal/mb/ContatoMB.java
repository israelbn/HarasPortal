package br.com.harasportal.mb;

import br.com.harasportal.ejb.ContatoDAO;
import br.com.harasportal.entidades.Contato;
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
public class ContatoMB {

    @EJB
    private ContatoDAO contatoDAO;

    private Contato contato;
    private List<Contato> listaContatos;
    private StatusTela tela;

    /**
     * Creates a new instance of ContatoMB
     */
    public ContatoMB() {
    }

    @PostConstruct
    public void init() {
       novo();
    }

    public void findAll() {
        listaContatos = contatoDAO.findByAll();
    }

    public void novo() {
        contato = new Contato();
        tela = StatusTela.inserindo;
    }

    public void salvar() {
        contatoDAO.persist(contato);

        if (tela == StatusTela.inserindo) {
            findAll();
        }
        tela = StatusTela.listando;
    }

    public void remover() {
        tela = StatusTela.deletando;
        contato.setLida(true);
    }

    public void voltarLista() {
        tela = StatusTela.listando;
    }

    //Médoto usado para validar a quantidade de palavras digitadas pelo cliente
    public void messageValidator(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        int countWords = ((String) value).split("[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ]+").length;
        if (countWords > 500) {
            FacesMessage msg = new FacesMessage("A mensagem deve conter no máximo 500 palavras: "+countWords);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ValidatorException(msg);
        }
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<Contato> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public StatusTela getTela() {
        return tela;
    }

    public void setTela(StatusTela tela) {
        this.tela = tela;
    }

}
