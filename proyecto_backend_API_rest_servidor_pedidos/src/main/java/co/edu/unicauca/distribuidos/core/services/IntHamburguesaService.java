package co.edu.unicauca.distribuidos.core.services;

import java.util.List;

import co.edu.unicauca.distribuidos.core.models.ClsHamburguesa;

public interface IntHamburguesaService {
	
	public List<ClsHamburguesa> listarHamburguesas();
	public ClsHamburguesa registrarHamburguesa(ClsHamburguesa hamburguesa);
	public ClsHamburguesa consultarHamburguesa(Integer idHamburguesa);	
	public boolean eliminarHamburguesa(Integer idHmaburguesa);

}
