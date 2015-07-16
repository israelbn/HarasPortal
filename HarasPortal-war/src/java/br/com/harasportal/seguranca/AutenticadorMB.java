/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.seguranca;

import br.com.harasportal.util.SessionUtil;
import java.io.Serializable;
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

    private String usuario;
    private String senha;

    public String autentica() {
        System.out.println("autentica..");
        if (usuario.equals("admin") && senha.equals("admin")) {
            System.out.println("Confirmou  usuario e senha ...");

            // adiciona usuário na session
            Object b = new Object();
            SessionUtil.setParam("USUARIOLogado", b);

            return "/admin.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    /**
     * Método que efetua o logout
     */
    public String registraSaida() {
        //remove usuário da session
        SessionUtil.remove("USUARIOLogado");
        return "/login?faces-redirect=true";
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
