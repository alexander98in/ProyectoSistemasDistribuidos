package co.edu.unicauca.clientehamburguesas.servicios;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

import co.edu.unicauca.clientehamburguesas.models.ClsEmpresa;

public class ClsEmpresaServices {
	
	private String endPoint;
	private Client client;
	
	public ClsEmpresaServices() {
		this.endPoint = "http://localhost:8085/api/empresas";
		this.client = ClientBuilder.newClient().register(new JacksonFeature());
	}
	
	public ClsEmpresa consultarInformacionEmpresa() {
		ClsEmpresa objEmpresa = null;
		WebTarget target = this.client.target(this.endPoint);
		objEmpresa = target.request(MediaType.APPLICATION_JSON_TYPE).get(ClsEmpresa.class);
		return objEmpresa;
	}
	
}
