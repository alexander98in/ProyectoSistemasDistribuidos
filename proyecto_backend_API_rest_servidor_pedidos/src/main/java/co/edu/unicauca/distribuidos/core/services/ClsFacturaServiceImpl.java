package co.edu.unicauca.distribuidos.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.ClsFactura;
import co.edu.unicauca.distribuidos.core.repositories.dao.IntAccesoDatosFacturaDAO;

@Service
public class ClsFacturaServiceImpl implements IntFacturaService {
	
	@Autowired
	private IntAccesoDatosFacturaDAO servicioAccesoBDFactura; 

	@Override
	public List<ClsFactura> listarFacturas() {
		return this.servicioAccesoBDFactura.listarFacturas();
	}

	@Override
	public ClsFactura consultarFactura(Integer idFactura) {
		return this.servicioAccesoBDFactura.consultarFactura(idFactura);
	}

	@Override
	public boolean eliminarFactura(Integer idFactura) {
		return this.servicioAccesoBDFactura.eliminarFactura(idFactura);
	}

	@Override
	public boolean eliminatTodasFacturas() {
		return this.servicioAccesoBDFactura.borrarFacturas();
	}

}
