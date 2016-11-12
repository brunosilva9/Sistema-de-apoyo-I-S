/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Login;
import cl.revengers.sb.LoginFacadeLocal;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *

 */
@Named(value = "loginBean")
/*  aki llega todo */
 /* Nombre por el cual el bean debe ser referenciado en las vistas */
@SessionScoped /* Los valores guardados en el Bean durarán durante toda la sesion HTTP */
/*  */
public class LoginBean implements Serializable /*  no tengo que saberlo */ /*Si debes saberlo, se deja Serializable para que el mismo bean
y los objetos que viven dentro de el puedan enviarse por bytes y reconstruirse en la vista*/ {

    @EJB
    private LoginFacadeLocal loginFacade;

    private final static Logger logger = Logger.getLogger(LoginBean.class);

    private String username;
    private String password;
    private Login usuarioSessionado;

    public LoginBean() {
        this.username = "";
        this.password = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login getUsuarioSessionado() {
        return usuarioSessionado;
    }

    public void setUsuarioSessionado(Login usuarioSessionado) {
        this.usuarioSessionado = usuarioSessionado;
    }

    public String login() {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            /*  contxt es un request */
            FacesMessage message = null;
            boolean loggedIn = false;

            if (this.getUsername().trim().equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Nombre de usuario obligatorio.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
                /*  carga de nuevo login */
            } else if (this.getPassword().trim().equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password obligatoria.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
            }
            Login log = loginFacade.getLogin(this.getUsername().trim(), this.getPassword().trim());

            /*
           
            * Dejaremos el usuario sesionado en esta variable para poder acceder a este desde otros bean
             */
            this.setUsuarioSessionado(log);

            if (log == null || log.getPass().trim().equals("") || log.getUsername().trim().equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Nombre de usuario o contraseña no validos.", "Nombre de usuario o contraseña no validos.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
            }

            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido " + this.getUsuarioSessionado().getUsername().trim() + "!", this.getUsername());
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);
            return "home";
        } catch (Exception e) {
            logger.error("Error grave en proceso de login: ", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error inesperado validando credenciales.", "Error inesperado validando credenciales.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login";
        }
    }

    public String logout() {
        try {
            /*
             * Para el caso del logout dejamos la sesion HTTP del usuario invalida, dejamos el login
            *  de este Bean en null y volvemos al Login
            */
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            this.password = "";
            this.username = "";
            this.usuarioSessionado = null;
            return "logout";
        } catch (Exception e) {
            logger.error("Error grave en proceso de logout");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error inesperado en proceso de logout.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "logout";
        }
    }
}
