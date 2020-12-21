package co.edu.unicauca.administrador.servicios;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

import co.edu.unicauca.administrador.models.ClsValores;

public class ClsValoresServices {
	
	private String endPoint;
	private Client client;
	
	public ClsValoresServices() {
		this.endPoint = "http://localhost:8085/api/valores";
		this.client = ClientBuilder.newClient().register(new JacksonFeature());
	}
	
	/**
	 * Consulta los valores de las hamburguesas segun su tipo.
	 * @return ClsValores
	 */
	public ClsValores consultarValores() {
		ClsValores objValores = null;
		WebTarget target = this.client.target(this.endPoint);
		objValores = target.request(MediaType.APPLICATION_JSON_TYPE).get(ClsValores.class);
		return objValores;
	}
	
	/**
	 * Actualiza los valores de las hamburguesas segun su tipo.
	 * @param objValoresActualizar
	 * @return ClsValores
	 */
	public ClsValores actualizarValores(ClsValores objValoresActualizar) {
		ClsValores objValores = null;
		WebTarget target = this.client.target(this.endPoint);
		Entity<ClsValores> data = Entity.entity(objValoresActualizar, MediaType.APPLICATION_JSON_TYPE);
		objValores = target.request(MediaType.APPLICATION_JSON_TYPE).put(data, ClsValores.class);
		return objValores;
	}
}
