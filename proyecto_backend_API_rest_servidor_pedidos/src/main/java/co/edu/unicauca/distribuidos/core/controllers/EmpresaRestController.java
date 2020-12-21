package co.edu.unicauca.distribuidos.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.models.ClsEmpresa;
import co.edu.unicauca.distribuidos.core.services.IntEmpresaService;

@RestController
@RequestMapping("/api")
public class EmpresaRestController {
	
	@Autowired
	private IntEmpresaService empresaService;
	
	@GetMapping("/empresas")
	public ClsEmpresa show() {
		ClsEmpresa objEmpresa = null;
		objEmpresa = empresaService.consultarInformacionEmpresa();
		return objEmpresa;
	}
	
	@PutMapping("/empresas")
	public ClsEmpresa update(@RequestBody ClsEmpresa objEmpresa) {
		ClsEmpresa objEmpresaActualizada = null;
		objEmpresaActualizada = empresaService.actualizarInformacionEmpresa(objEmpresa);
		return objEmpresaActualizada;
	}
}
