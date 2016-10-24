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
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Esteban Perez
 */
@Named(value = "loginBean") /*  aki llega todo */
@SessionScoped  /*  por cuanto tiempo quiero que viva los datos en la variable  aplication scope viwscope request response
                  sesion http */
public class LoginBean implements Serializable /*  no tengo que saberlo */ {

    @EJB
    private LoginFacadeLocal loginFacade;

   
  

    private final static Logger logger = Logger.getLogger(LoginBean.class);

    private String username;
    private String password;

    /**
     * Creates a new instance of LoginBean
     */
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

    public String login() {
        try {
            RequestContext context = RequestContext.getCurrentInstance(); /*  contxt es un request */
            FacesMessage message = null;
            boolean loggedIn = false;

            if (this.getUsername().trim().equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Nombre de usuario obligatorio.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login"; /*  carga de nuevo login */
            } else if (this.getPassword().trim().equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password obligatoria.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
            }
            Login log = loginFacade.getLogin(this.getUsername().trim(), this.getPassword().trim());

            

            if (log == null || log.getPass().trim().equals("") || log.getUsername().trim().equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Nombre de usuario o contraseña no validos.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
            }
            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido!", this.getUsername());
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);
            return "home";
        } catch (Exception e) {
            logger.error("Error grave en proceso de login: ", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Error inesperado validando credenciales.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login";
        }
    }
}
