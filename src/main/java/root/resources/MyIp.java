/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root.resources;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author fv192
 */
@Path("/")
public class MyIp {
@Context
HttpServletRequest request;
@GET
@Path("/ip")
@Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON,})
public Response getMiIP() {
String miip = request.getHeader("X-Forwarded-For");
return getMyIPGenerico(miip);
}
@GET
@Path("/ip/{ip}")
@Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON,})
public Response getMyIPParametro(@PathParam("ip") String miip) {
return getMyIPGenerico(miip);
}
public Response getMyIPGenerico(String miip) {
if (miip == null) {
miip = request.getRemoteAddr();
}
boolean validaLocalHost = "0:0:0:0:0:0:0:1".equals(miip);
String url = "https://api.ip2country.info/ip?" + miip;
Client clienteREST = ClientBuilder.newClient();
JsonObject jsonDatosRespuestaGeo = clienteREST.target(url).request()
.get().readEntity(JsonObject.class);
/**
* Java API for JSON Processing | JSR 374 Specification
* https://javaee.github.io/jsonp/
*/
JsonObject jsonLocalHostNoAplica = Json.createObjectBuilder()
.add("api_geo", "localhost no aplica").build();
JsonObject jsonRespuesta = Json.createObjectBuilder()
.add("myip", miip)
.add("api_geo", validaLocalHost ? jsonLocalHostNoAplica : jsonDatosRespuestaGeo)
.add("request", request.getRequestURL().toString()).build();
return Response.ok(jsonRespuesta.asJsonObject()).build();
}
}