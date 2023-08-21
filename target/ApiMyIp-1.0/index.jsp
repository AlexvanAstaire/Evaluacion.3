<%-- 
    Document   : index
    Created on : 20-08-2023, 18:15:34
    Author     : fv192
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String miip = request.getHeader("X-Forwarded-For");
String urlBase = request.getRequestURL().toString() + "api/ip/";
if (miip == null) {
miip = request.getRemoteAddr();
}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
crossorigin="anonymous">
<title>Mi IP</title>
</head>
<style>
div.center{
margin-left: auto;
margin-right: auto;
text-align: center;
}
</style>
<div class="center">
<br><img src="https://cdn2.iconfinder.com/data/icons/whcompare-servers-web-hosting/50/dedicated-ip-
address-512.png" width="120" height="120" alt="ip"/>
<br>
<h1>IP: <%= miip%></h1>
<br>
<div class="card text-white bg-dark mb-3 center" style="max-width: 30rem;">
<div class="card-header center">Formas de usar la API de consulta IP</div>
<div class="card-body center">
<h5 class="card-title">Para consultar mi IP</h5>
<a href= <%=urlBase%>><%=urlBase%></a>
<h5 class="card-title">Consultar por otra IP</h5>
<p class="card-title">Ejemplo: Uso con 1.1.1.1 DNS </p>
<a href= <%=urlBase + "1.1.1.1"%> > <%=urlBase + "/1.1.1.1"%> </a>
</div>
</div>
<p>Para la Geolocalización de las IP es usado el servicio externo de deip2country </p>
</div>
</body>
</html>