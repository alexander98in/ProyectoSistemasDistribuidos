package co.edu.unicauca.distribuidos.core.repositories.dao;

import co.edu.unicauca.distribuidos.core.models.ClsEmpresa;

public interface IntAccesoDatosEmpresaDAO {
	
	public ClsEmpresa consultarInformacionEmpresa();
	public ClsEmpresa actualizarInformacionEmpresa(ClsEmpresa objEmpresa);

}
