/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.Session;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import record.KeepRecord;


/**
 *
 * @author root
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    @Inject SecurityContext ctx;
    
    private String username;
    private String password;
    private Set<String> roles;
    private static String error;
    private AuthenticationStatus status;

   

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

   

    public String login()
    {
        try {
        FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        status = ctx.authenticate(request, response, withParams().credential(credential));

        if (status.equals(SEND_CONTINUE)) {
            // Authentication mechanism has sent a redirect, should not send anything to response from JSF now.
            // The control will now go into HttpAuthenticationMechanism
            context.responseComplete();
        }

        if (status.equals(SUCCESS)) {
            error = "";
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            if (roles.contains("admin")) {
                return "/admin/adminindex.jsf?faces-redirect=true";
            } else if (roles.contains("customer")) {
                return "/Customer/CustomerIndex.jsf?faces-redirect=true";
            }
        } else {
            setError(error);
            System.err.println(error);
            error = "User Name or Password may be wrong";
            
//            return "/login-signup.jsf";
        }
    } catch (Exception e) {
        System.err.println("2User Name or Password may be wrong");
        error = "User Name or Password may be wrong";
        e.printStackTrace();
    }
    return "";
    }
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        
       // errorstatus="";
    }
    
    public String logout()
    {
        try{
            System.err.println("in logout");
       HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            KeepRecord.setToken("");
            KeepRecord.setPassword("");
            KeepRecord.setUsername("");
       request.logout();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "/login-signup.jsf?faces-redirect=true";
    }
    
}
