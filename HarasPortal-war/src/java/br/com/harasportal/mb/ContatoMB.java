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

    public void novo() {
        contato = new Contato();
        tela = StatusTela.inserindo;
    }

    public void salvar() {
        contatoDAO.persist(contato);
        FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Contato enviado com sucesso!!!"));
        novo();
    }

    public void voltarLista() {
        tela = StatusTela.listando;
    }
    
    public void detalhar(){
        tela = StatusTela.detalhar;
        
        //altero a mensagem para lida e altera o valor no banco
        contato.setLida(true);
        contatoDAO.persist(contato);
    }

    //Médoto usado para validar a quantidade de palavras digitadas pelo cliente
    public void messageValidator(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        int countWords = ((String) value).split("[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ]+").length;
        if (countWords > 500) {
            FacesMessage msg = new FacesMessage("A mensagem deve conter no máximo 500 palavras, foram encontradas: "+countWords);
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
        return contatoDAO.findByAll();
    }

    public StatusTela getTela() {
        return tela;
    }

    public void setTela(StatusTela tela) {
        this.tela = tela;
    }

}
