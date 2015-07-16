/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.harasportal.seguranca;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DMADESENV01
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class ControleAcesso implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if ((session.getAttribute("USUARIOLogado") != null)
                || (req.getRequestURI().endsWith("login.xhtml"))
                || (req.getRequestURI().endsWith("venda/venda.xhtml"))
                || (req.getRequestURI().endsWith("cadastro/contato.xhtml"))
                || (req.getRequestURI().contains("javax.faces.resource/"))
                ) {
            if((req.getRequestURI().endsWith("login.xhtml")) && session.getAttribute("USUARIOLogado") != null)
                redireciona("/HarasPortal-war/admin.xhtml", response);
            chain.doFilter(request, response);
        } else {
            redireciona("/HarasPortal-war/login.xhtml", response);
        }
    }

    private void redireciona(String url, ServletResponse response)
            throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect(url);
    }

    @Override
    public void destroy() {
    }

}
