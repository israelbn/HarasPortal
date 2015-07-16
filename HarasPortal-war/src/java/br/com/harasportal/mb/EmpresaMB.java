package br.com.harasportal.mb;


import br.com.harasportal.ejb.EmpresaDAO;
import br.com.harasportal.entidades.Empresa;
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
public class EmpresaMB {
    
    @EJB
    private EmpresaDAO empresaDAO;
    
    private Empresa empresa;
    private List<Empresa> listaEmpresas;
    private StatusTela tela;
    
    
    public EmpresaMB() {
    }
    
    @PostConstruct
    public void init(){
        novo();
    }
    
    public void login(){}
    
    public void novo(){
        empresa = new Empresa();
        tela = StatusTela.inserindo;
    }
    
    public void salvar(){
        empresaDAO.persist(empresa);
        novo();
    }
    
    public void confirmSenhaValidator(FacesContext fc, UIComponent ui, Object value) throws ValidatorException{
        if(!((String) value).equals(empresa.getSenha())){
            
            FacesMessage fm = new FacesMessage("As senhas não conferem");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            empresa.setSenha("");
            empresa.setConfirmaSenha("");
            
            throw new ValidatorException(fm);
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas = empresaDAO.findByAll();
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public StatusTela getTela() {
        return tela;
    }

    public void setTela(StatusTela tela) {
        this.tela = tela;
    }
    
}