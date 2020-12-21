package co.edu.unicauca.distribuidos.core.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.bussines.ClsComputoIVA;
import co.edu.unicauca.distribuidos.core.bussines.ClsConteoHamburguesas;
import co.edu.unicauca.distribuidos.core.models.ClsEmpresa;
import co.edu.unicauca.distribuidos.core.models.ClsFactura;
import co.edu.unicauca.distribuidos.core.models.ClsHamburguesa;
import co.edu.unicauca.distribuidos.core.models.ClsPedido;
import co.edu.unicauca.distribuidos.core.models.ClsValores;
import co.edu.unicauca.distribuidos.core.repositories.dao.IntAccesoDatosEmpresaDAO;
import co.edu.unicauca.distribuidos.core.repositories.dao.IntAccesoDatosFacturaDAO;
import co.edu.unicauca.distribuidos.core.repositories.dao.IntAccesoDatosHamburguesaDAO;
import co.edu.unicauca.distribuidos.core.repositories.dao.IntAccesoDatosValoresDAO;

@Service
public class ClsPedidoServiceImpl implements IntPedidoService {

	@Autowired
	private IntAccesoDatosFacturaDAO servicioAccesoBDFactura;
	
	@Autowired
	private IntAccesoDatosHamburguesaDAO servicioAccesoBDHamburguesa;
	
	@Autowired
	private IntAccesoDatosEmpresaDAO servicioAccesoBDEmpresa;
	
	@Autowired
	private IntAccesoDatosValoresDAO servicioAccesoBDValores;

	@Override
	public ClsFactura registrarPedido(ClsPedido objPedido) {
		ArrayList<ClsHamburguesa> listaHamburguesasCompradas = objPedido.getListaHamburguesas();
		
		ClsFactura objFactura = null;
		objFactura = generarFactura(listaHamburguesasCompradas);
		
		if(objFactura != null) {
			objFactura = this.servicioAccesoBDFactura.registrarFactura(objFactura);
			Integer idFactura = objFactura.getIdFactura();
			registrarHamburguesas(listaHamburguesasCompradas, idFactura);
			
		}
		return objFactura;
	}
	
	private ClsFactura generarFactura(ArrayList<ClsHamburguesa> listaHamburguesasCompradas) 
	{
		ClsFactura objFacturaGenerada = null;
		if(!listaHamburguesasCompradas.isEmpty()) {
			
			ClsEmpresa objEmpresa = servicioAccesoBDEmpresa.consultarInformacionEmpresa();
			ClsValores objValores = servicioAccesoBDValores.consultarValores();
			ClsConteoHamburguesas objConteoHamburguesas = new ClsConteoHamburguesas(listaHamburguesasCompradas);
			ClsComputoIVA objComputoIVA = new ClsComputoIVA(objConteoHamburguesas, objValores);
			
			objFacturaGenerada = new ClsFactura();
			objFacturaGenerada.setIdFactura(0);
			objFacturaGenerada.setIdEmpresa(objEmpresa.getIdEmpresa());
			objFacturaGenerada.setNumHamburguesasPequenias(objConteoHamburguesas.getNumHamburguesasPequenias());
			objFacturaGenerada.setNumHamburguesasMedianas(objConteoHamburguesas.getNumHamburguesasMedianas());
			objFacturaGenerada.setNumHamburguesasGrandes(objConteoHamburguesas.getNumHamburguesasGrandes());
			objFacturaGenerada.setCostoSinIVA(objComputoIVA.getCostoSinIVA());
			objFacturaGenerada.setIVA(objComputoIVA.getIVA());
			objFacturaGenerada.setCostoConIVA(objComputoIVA.getCostoConIVA());	
		}
		
		return objFacturaGenerada;
	}
	
	private void registrarHamburguesas(ArrayList<ClsHamburguesa> listaHamburguesas, Integer idFactura) 
	{
		for(int i=0; i<listaHamburguesas.size(); i++) {
			listaHamburguesas.get(i).setIdFactura(idFactura);
			this.servicioAccesoBDHamburguesa.registrarHamburguesa(listaHamburguesas.get(i));
		}
	}

}
