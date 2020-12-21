package co.edu.unicauca.distribuidos.core.services;

import java.util.List;

import co.edu.unicauca.distribuidos.core.models.ClsFactura;

public interface IntFacturaService {
	
	public List<ClsFactura> listarFacturas();
	public ClsFactura consultarFactura(Integer idFactura);
	public boolean eliminarFactura(Integer idFactura);
	public boolean eliminatTodasFacturas();
}
