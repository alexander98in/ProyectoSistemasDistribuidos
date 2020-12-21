package co.edu.unicauca.clientehamburguesas.servicios;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

import co.edu.unicauca.clientehamburguesas.models.ClsValores;

public class ClsValoresServices {
	
	private String endPoint;
	private Client client;
	
	public ClsValoresServices() {
		this.endPoint = "http://localhost:8085/api/valores";
		this.client = ClientBuilder.newClient().register(new JacksonFeature());
	}
	
	public ClsValores consultarValores() {
		ClsValores objValores = null;
		WebTarget target = this.client.target(this.endPoint);
		objValores = target.request(MediaType.APPLICATION_JSON_TYPE).get(ClsValores.class);
		return objValores;
	}

}
