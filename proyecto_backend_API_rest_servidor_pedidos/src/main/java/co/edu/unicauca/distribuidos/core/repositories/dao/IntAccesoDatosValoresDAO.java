package co.edu.unicauca.distribuidos.core.repositories.dao;

import co.edu.unicauca.distribuidos.core.models.ClsValores;

public interface IntAccesoDatosValoresDAO {
	
	public ClsValores consultarValores();
	public ClsValores actualizarValores(ClsValores objValores);

}
