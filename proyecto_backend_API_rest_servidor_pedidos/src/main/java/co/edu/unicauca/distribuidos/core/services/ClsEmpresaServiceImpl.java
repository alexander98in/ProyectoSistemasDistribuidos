package co.edu.unicauca.distribuidos.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.ClsEmpresa;
import co.edu.unicauca.distribuidos.core.repositories.dao.IntAccesoDatosEmpresaDAO;

@Service
public class ClsEmpresaServiceImpl implements IntEmpresaService{
	
	@Autowired
	private IntAccesoDatosEmpresaDAO servicioAccesoBDEmpresa;

	@Override
	public ClsEmpresa consultarInformacionEmpresa() {
		return this.servicioAccesoBDEmpresa.consultarInformacionEmpresa();
	}

	@Override
	public ClsEmpresa actualizarInformacionEmpresa(ClsEmpresa objEmpresa) {
		return this.servicioAccesoBDEmpresa.actualizarInformacionEmpresa(objEmpresa);
	}
}
