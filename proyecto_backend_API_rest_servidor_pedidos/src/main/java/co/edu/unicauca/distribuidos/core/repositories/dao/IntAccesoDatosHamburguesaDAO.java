package co.edu.unicauca.distribuidos.core.repositories.dao;

import java.util.ArrayList;

import co.edu.unicauca.distribuidos.core.models.ClsHamburguesa;

public interface IntAccesoDatosHamburguesaDAO {
	
	public ArrayList<ClsHamburguesa> listarHamburguesas(); 
	
	public ClsHamburguesa registrarHamburguesa(ClsHamburguesa objHamburguesa);
    
    public ClsHamburguesa consultarHamburguesa(Integer idHamburguesa);   
     
    public boolean eliminarHamburguesa(Integer idHamburguesa);
    
}
