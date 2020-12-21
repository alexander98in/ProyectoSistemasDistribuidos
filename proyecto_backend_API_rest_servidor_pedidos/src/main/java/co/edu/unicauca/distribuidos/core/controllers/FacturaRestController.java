package co.edu.unicauca.distribuidos.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.models.ClsFactura;
import co.edu.unicauca.distribuidos.core.services.IntFacturaService;


@RestController
@RequestMapping("/api")
public class FacturaRestController {
	
	@Autowired
	private IntFacturaService facturaService;
	
	@GetMapping("/facturas")
	public List<ClsFactura> index() {
		return facturaService.listarFacturas();
	}
	
	@GetMapping("/facturas/{id}")
	public ClsFactura show(@PathVariable Integer id) {
		ClsFactura objFactura = null;
		objFactura = facturaService.consultarFactura(id);
		return objFactura;
	}
	
	@DeleteMapping("/facturas/{id}")
	public Boolean delete(@PathVariable Integer id) {
		Boolean bandera = false;
		ClsFactura facturaActual = facturaService.consultarFactura(id);
		if(facturaActual != null) {
			bandera = facturaService.eliminarFactura(id);
		}
		return bandera;
	}
	
	@DeleteMapping("/facturas")
	public Boolean deleteAll() {
		Boolean bandera = false;
		bandera = facturaService.eliminatTodasFacturas();
		return bandera;
	}

}
