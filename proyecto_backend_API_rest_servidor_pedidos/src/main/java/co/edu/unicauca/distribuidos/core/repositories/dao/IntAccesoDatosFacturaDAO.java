package co.edu.unicauca.distribuidos.core.repositories.dao;

import java.util.ArrayList;

import co.edu.unicauca.distribuidos.core.models.ClsFactura;

public interface IntAccesoDatosFacturaDAO {
	
	public ArrayList<ClsFactura> listarFacturas();
	
	public ClsFactura registrarFactura(ClsFactura objFactura);
	
	public ClsFactura consultarFactura(Integer idFactura);
	
	public boolean eliminarFactura(Integer idFactura);
	
	public boolean borrarFacturas();

}
