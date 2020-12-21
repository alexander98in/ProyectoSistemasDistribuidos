package co.edu.unicauca.administrador.servicios;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

import co.edu.unicauca.administrador.models.ClsFactura;

public class ClsFacturaServices {
	
	private String endPoint;
	private Client client;
	
	public ClsFacturaServices() {
		this.endPoint = "http://localhost:8085/api/facturas";
		this.client = ClientBuilder.newClient().register(new JacksonFeature());
	}
	
	/**
	 * Lista las factura registradas.
	 * @return List<ClsFactura>
	 */
	public List<ClsFactura> listarFacturas() {
		List<ClsFactura> listaFacturas = null;
		WebTarget target = this.client.target(this.endPoint);
		listaFacturas = target.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<ClsFactura>>() {});
		return listaFacturas;
	}
	
	/**
	 * Consulta una factura con su id.
	 * @param idFactura
	 * @return ClsFactura
	 */
	public ClsFactura consultarFactura(Integer idFactura) {
		ClsFactura objFactura = null;
		WebTarget target = this.client.target(this.endPoint+"/"+idFactura);
		objFactura = target.request(MediaType.APPLICATION_JSON_TYPE).get(ClsFactura.class);
		return objFactura;
	}
	
	/**
	 * Permite eliminar una factura con su id.
	 * @param idFactura
	 * @return Boolean
	 */
	public Boolean eliminarFactura(Integer idFactura) {
		Boolean seEliminoFactura = false;
		WebTarget target = this.client.target(this.endPoint+"/"+idFactura);
		seEliminoFactura = target.request(MediaType.APPLICATION_JSON_TYPE).delete(Boolean.class);
		return seEliminoFactura;
	}
	
	/**
	 * Permite eliminar todas las facturas registradas.
	 * @return Boolean
	 */
	public Boolean eliminarTodasFacturas() {
		Boolean seEliminaronFacturas = false;
		WebTarget target = this.client.target(this.endPoint);
		seEliminaronFacturas = target.request(MediaType.APPLICATION_JSON_TYPE).delete(Boolean.class);
		return seEliminaronFacturas;
	}
}
