/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package admin;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 *
 * @author root
 */
@DeclareRoles({"admin", "customer"})
@Stateless
public class SecureBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

@RolesAllowed("customer")  
//@PermitAll  
 //@DenyAll   
public String saySecureHello()
{
    return "Secure Hello from Secure Bean";
}

}
