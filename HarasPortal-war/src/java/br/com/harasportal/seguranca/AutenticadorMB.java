/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.seguranca;

import br.com.harasportal.ejb.EmpresaDAO;
import br.com.harasportal.entidades.Empresa;
import br.com.harasportal.util.SessionUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author DMADESENV01
 */
@ManagedBean
@RequestScoped
public class AutenticadorMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EmpresaDAO empresaDAO;
    private String usuario;
    private String senha;

    public String autentica() {
        // busca a empresa que está no banco de dados
        List<Empresa> lista = verificaEmpresaCadastrada();
        if (lista != null) {
            Empresa empresa = lista.get(0);
            if (usuario.equals(empresa.getUsuario()) && senha.equals(empresa.getSenha())) {
                // adiciona usuário na session
                Object b = new Object();
                SessionUtil.setParam("USUARIOLogado", b);

                return "/admin.xhtml?faces-redirect=true";
            }
        }
        return null;
    }

    /**
     * Método que efetua o logout
     * @return 
     */
    public String registraSaida() {
        //remove usuário da session
        SessionUtil.remove("USUARIOLogado");
        return "/login?faces-redirect=true";
    }

    public boolean isUsuarioLogado() {
        return SessionUtil.getParam("USUARIOLogado") != null;
    }
    
    public List<Empresa> verificaEmpresaCadastrada(){
        Empresa empresa = new Empresa();
        List<Empresa> lista = new ArrayList<>();
        lista = empresaDAO.findByAll();

        if (!lista.isEmpty())
            return lista;
        return null;
    }

    // get e set
    public String getSenha() {
        return senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
