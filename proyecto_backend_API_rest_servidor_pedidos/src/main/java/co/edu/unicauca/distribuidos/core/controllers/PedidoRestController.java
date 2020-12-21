package co.edu.unicauca.distribuidos.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.models.ClsFactura;
import co.edu.unicauca.distribuidos.core.models.ClsPedido;
import co.edu.unicauca.distribuidos.core.services.IntPedidoService;

import sop_corba.GestionNotificacion;
import sop_corba.GestionNotificacionHelper;
import sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTO;
import sop_corba.GestionNotificacionPackage.notificacionDTO;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;

@RestController
@RequestMapping("/api")
public class PedidoRestController {
	
	@Autowired
	private IntPedidoService pedidoService;
	GestionNotificacion referenciaObjetoRemotoNotificacion;
	
	@PostMapping("/pedidos")
	public ClsFactura create(@RequestBody ClsPedido objPedido) 
	{
		ClsFactura objFactura = null;
		objFactura = pedidoService.registrarPedido(objPedido);
		obtenerReferenciaRemota();
		enviarNotificacionPedido(objPedido);
		return objFactura;
	}
	
	/**
	 * Obtiene la referencia del servant
	 */
	private void obtenerReferenciaRemota()
    {
		try 
		{
			String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";            
            vec[1] = "localhost";
            vec[2] = "-ORBInitialPort";            
            vec[3] = "2020";
            // crea e inicia el ORB
            ORB orb = ORB.init(vec, null);
            // obtiene la base del naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "gestionNotificacion";
            referenciaObjetoRemotoNotificacion = GestionNotificacionHelper.narrow(ncRef.resolve_str(name));  
        } 
		catch (Exception e)
		{
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
	
	/**
	 * Envia notificacion del pedido
	 * @param objPedio
	 */
	private void enviarNotificacionPedido(ClsPedido objPedio)
	{
		int numeroMesa = objPedio.getNumeroMesa();
		int numeroHamburguesas = objPedio.getListaHamburguesas().size();
		hamburguesaNotificacionDTO[] listaHamburguesas = new hamburguesaNotificacionDTO[numeroHamburguesas];
		hamburguesaNotificacionDTO hamburguesaNotificacion = null;
		
		for(int i=0; i<objPedio.getListaHamburguesas().size(); i++) {
			char tipoHamburguesa = objPedio.getListaHamburguesas().get(i).getTipo();
			int cantidadIngredientesExtra = objPedio.getListaHamburguesas().get(i).getCantidadIngredientesExtra();
			hamburguesaNotificacion = new hamburguesaNotificacionDTO(tipoHamburguesa, cantidadIngredientesExtra);
			listaHamburguesas[i] = hamburguesaNotificacion;
		}

		notificacionDTO objNotificacion = new notificacionDTO(numeroMesa, listaHamburguesas);
		boolean seEnvioNotifiacion = referenciaObjetoRemotoNotificacion.enviarNotificacionPedido(objNotificacion);
		if(seEnvioNotifiacion) {
			System.out.println("Se notifico el pedido con exito.");
		}
		else {
			System.out.println("Se presento un error en la notificacion del pedido.");
		}
	}
}
