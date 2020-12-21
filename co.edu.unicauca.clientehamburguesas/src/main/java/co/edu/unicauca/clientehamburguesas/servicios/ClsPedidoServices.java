package co.edu.unicauca.clientehamburguesas.servicios;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

import co.edu.unicauca.clientehamburguesas.models.ClsFactura;
import co.edu.unicauca.clientehamburguesas.models.ClsPedido;

public class ClsPedidoServices {
	
	private String endPoint;
	private Client client;
	
	public ClsPedidoServices() {
		this.endPoint = "http://localhost:8085/api/pedidos";
		this.client = ClientBuilder.newClient().register(new JacksonFeature());
	}
	
	public ClsFactura registrarPedido(ClsPedido objPedidoRegistrar) 
	{
		ClsFactura objFacturaGenerada = null;	
		WebTarget target = this.client.target(this.endPoint);
		Entity<ClsPedido> data = Entity.entity(objPedidoRegistrar, MediaType.APPLICATION_JSON_TYPE);
		objFacturaGenerada = target.request(MediaType.APPLICATION_JSON_TYPE).post(data, ClsFactura.class);
		
		return objFacturaGenerada;
	}
	
}
