package co.edu.unicauca.administrador.servicios;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

import co.edu.unicauca.administrador.models.ClsEmpresa;

public class ClsEmpresaServices {
	
	private String endPoint;
	private Client client;
	
	public ClsEmpresaServices() {
		this.endPoint = "http://localhost:8085/api/empresas";
		this.client = ClientBuilder.newClient().register(new JacksonFeature());
		
	}
	
	/**
	 * Consulta la informacion de la empresa.
	 * @return ClsEmpresa
	 */
	public ClsEmpresa consultarInformacionEmpresa() {
		ClsEmpresa objEmpresa = null;
		WebTarget target = this.client.target(this.endPoint);
		objEmpresa = target.request(MediaType.APPLICATION_JSON_TYPE).get(ClsEmpresa.class);
		return objEmpresa;
	}
	
	/**
	 * Actualiza la informacion de la empresa.
	 * @param objEmpresaActualizar
	 * @return ClsEmpresa
	 */
	public ClsEmpresa actualizarInformacionEmpresa(ClsEmpresa objEmpresaActualizar) {
		ClsEmpresa objEmpresa = null;
		WebTarget target = client.target(this.endPoint);
		Entity<ClsEmpresa> data = Entity.entity(objEmpresaActualizar, MediaType.APPLICATION_JSON_TYPE);
		objEmpresa = target.request(MediaType.APPLICATION_JSON_TYPE).put(data, ClsEmpresa.class);
		return objEmpresa;
	}

}
