package co.edu.unicauca.distribuidos.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.ClsValores;
import co.edu.unicauca.distribuidos.core.repositories.dao.IntAccesoDatosValoresDAO;

@Service
public class ClsValoresServiceImpl implements IntValoresService {
	
	@Autowired
	private IntAccesoDatosValoresDAO servicioAccesoBDValores;

	@Override
	public ClsValores consultarValores() {
		return this.servicioAccesoBDValores.consultarValores();
	}

	@Override
	public ClsValores actualizarValores(ClsValores objValores) {
		return this.servicioAccesoBDValores.actualizarValores(objValores);
	}

}
