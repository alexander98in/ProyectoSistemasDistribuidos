package co.edu.unicauca.distribuidos.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.models.ClsValores;
import co.edu.unicauca.distribuidos.core.services.IntValoresService;

@RestController
@RequestMapping("/api")
public class ValoresRestController {
	
	@Autowired 
	private IntValoresService valoresService;
	
	@GetMapping("/valores")
	public ClsValores show() {
		ClsValores objValores = null;
		objValores = valoresService.consultarValores();
		return objValores;
	}
	
	@PutMapping("/valores")
	public ClsValores update(@RequestBody ClsValores objValores) {
		ClsValores objValoresActualizados;
		objValoresActualizados = valoresService.actualizarValores(objValores);
		return objValoresActualizados;
	}
}
