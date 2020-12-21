package co.edu.unicauca.distribuidos.core.services;

import co.edu.unicauca.distribuidos.core.models.ClsEmpresa;

public interface IntEmpresaService {

	public ClsEmpresa consultarInformacionEmpresa();
	public ClsEmpresa actualizarInformacionEmpresa(ClsEmpresa objEmpresa);

}
