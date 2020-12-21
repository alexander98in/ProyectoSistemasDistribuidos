package co.edu.unicauca.distribuidos.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.ClsHamburguesa;
import co.edu.unicauca.distribuidos.core.repositories.dao.IntAccesoDatosHamburguesaDAO;

@Service
public class ClsHamburguesaServiceImpl implements IntHamburguesaService{

	@Autowired
	private IntAccesoDatosHamburguesaDAO servicioAccesoBaseDatos;
	
	@Override
	public List<ClsHamburguesa> listarHamburguesas() {
		return this.servicioAccesoBaseDatos.listarHamburguesas();
	}

	@Override
	public ClsHamburguesa registrarHamburguesa(ClsHamburguesa hamburguesa) {
		return this.servicioAccesoBaseDatos.registrarHamburguesa(hamburguesa);
	}

	@Override
	public ClsHamburguesa consultarHamburguesa(Integer idHamburguesa) {
		return this.servicioAccesoBaseDatos.consultarHamburguesa(idHamburguesa);
	}

	@Override
	public boolean eliminarHamburguesa(Integer idHmaburguesa) {
		return this.servicioAccesoBaseDatos.eliminarHamburguesa(idHmaburguesa);
	}
}
