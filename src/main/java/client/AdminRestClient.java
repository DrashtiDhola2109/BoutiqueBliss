/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Jersey REST client generated for REST resource:SecureAdminResource
 * [SecureAdmin]<br>
 * USAGE:
 * <pre>
 *        AdminRestClient client = new AdminRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Administrator
 */
public class AdminRestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/boutiqueStore/webresources";

    public AdminRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("SecureAdmin");
    }

    public void updatesize(String sid, String size) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatesize/{0}/{1}", new Object[]{sid, size})).request().put(Entity.entity("", MediaType.APPLICATION_JSON_TYPE));
    }

    public <T> T getorderbyname(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getallorderbyname/{0}", new Object[]{name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getproductbyid(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getproductbyid/{0}", new Object[]{pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T viewalluser(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("viewalluser");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProduct(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("products");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getproductbycid(Class<T> responseType, String cid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getproductbycid/{0}", new Object[]{cid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addsubcategory(String type, String pcid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addsubcategory/{0}/{1}", new Object[]{type, pcid})).request().post(null);
    }

    public void deletecategory(String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletecategory/{0}", new Object[]{cid})).request().delete();
    }
    
    public void retrieveCategory(String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("retrievecategory/{0}", new Object[]{cid})).request().post(null);
    }

    public <T> T getsize(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("allsize");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteproduct(String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteproduct/{0}", new Object[]{pid})).request().delete();
    }

    public <T> T getsizebyid(Class<T> responseType, String sid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getsizebyid/{0}", new Object[]{sid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateproduct(String pid, String cid, String sid, String pname, String quantity, String price, String color, String description, String pimage, String discount, String isavailable, String ishide) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateproduct/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}/{10}/{11}", new Object[]{pid, cid, sid, pname, quantity, price, color, description, pimage, discount, isavailable, ishide})).request().put(null);
    }

    public <T> T getallorder(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallorder");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addparentcategory(String type) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addparentcategory/{0}", new Object[]{type})).request().post(null);
    }

    public void updateparentcategory(String cid, String type) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateparentcategory/{0}/{1}", new Object[]{cid, type})).request().put(Entity.entity("", MediaType.APPLICATION_JSON_TYPE));
    }

    public <T> T viewalladmin(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("viewalladmin");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getproductbycolor(Class<T> responseType, String color) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getproductbycolor/{0}", new Object[]{color}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getcategorybyid(Class<T> responseType, String cid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getcategorybyid/{0}", new Object[]{cid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getCategories(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("categories");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getuser(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getusers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deletesize(String sid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletesize/{0}", new Object[]{sid})).request().delete();
    }
    
    public void retrieveSize(String sid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("retrievesize/{0}", new Object[]{sid})).request().post(null);
    }

    public void addsize(String size) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addsize/{0}", new Object[]{size})).request().post(null);
    }

    public void updatesubcategory(String cid, String type, String pcid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatesubcategory/{0}/{1}/{2}", new Object[]{cid, type, pcid})).request().put(Entity.entity("", MediaType.APPLICATION_JSON_TYPE));
    }

    public void addproduct(String cid, String sid, String pname, String quantity, String price, String color, String description, String pimage, String discount) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addproduct/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{cid, sid, pname, quantity, price, color, description, pimage, discount})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
