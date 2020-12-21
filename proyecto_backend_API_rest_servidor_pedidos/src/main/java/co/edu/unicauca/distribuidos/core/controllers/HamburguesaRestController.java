package co.edu.unicauca.distribuidos.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.models.ClsHamburguesa;
import co.edu.unicauca.distribuidos.core.services.IntHamburguesaService;

@RestController
@RequestMapping("/api")
public class HamburguesaRestController {
	
	@Autowired
	private IntHamburguesaService hamburguesaService;

	/**
	 * Lista las hamburguesas registradas
	 * @return
	 */
	@GetMapping("/hamburguesas")
	public List<ClsHamburguesa> index() {
		return hamburguesaService.listarHamburguesas();
	}
	
	/**
	 * Registra los datos de una hamburguesa
	 * @param hamburguesa
	 * @return
	 */
	@PostMapping("/hamburguesas")
	public ClsHamburguesa create(@RequestBody ClsHamburguesa hamburguesa) {
		ClsHamburguesa objHamburguesa = null;
		objHamburguesa = hamburguesaService.registrarHamburguesa(hamburguesa);
		return objHamburguesa;
	}
	
	/**
	 * Consulta una hamburguesa por id
	 * @param id
	 * @return
	 */
	@GetMapping("/hamburguesas/{id}")
	public ClsHamburguesa show(@PathVariable Integer id) {
		ClsHamburguesa objHamburguesa = null;		
		objHamburguesa = hamburguesaService.consultarHamburguesa(id);		
		return objHamburguesa;
	}
	
	/**
	 * Elimina una hamburguesa
	 * @param id
	 * @return
	 */
	@DeleteMapping("/hamburguesas/{id}")
	public Boolean delete(@PathVariable Integer id) {				
		Boolean bandera=false;
		ClsHamburguesa hamburguesaActual = hamburguesaService.consultarHamburguesa(id);
		if(hamburguesaActual!=null)		
		{
			bandera = hamburguesaService.eliminarHamburguesa(id);
		}
		return bandera;
	}
}
