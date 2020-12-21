package co.edu.unicauca.distribuidos.core.services;

import co.edu.unicauca.distribuidos.core.models.ClsValores;

public interface IntValoresService {
	
	public ClsValores consultarValores();
	public ClsValores actualizarValores(ClsValores objValores);

}
