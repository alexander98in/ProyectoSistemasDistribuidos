package co.edu.unicauca.distribuidos.core.repositories.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.ClsHamburguesa;
import co.edu.unicauca.distribuidos.core.repositories.conexion.ClsConexionBD;

/**
 * la clase permite listar, registrar, consultar y eliminar datos de las hamburguesas
 * @author Alexander Inagan, Jeison Ortiz
 * @version: 18/12/2020
 */
@Service
public class ClsHamburguesaDAO implements IntAccesoDatosHamburguesaDAO {

	private final ClsConexionBD conexionABaseDeDatos;
	
	/**
	 * Constructor de la clase ClsHamburguesaDAO
	 */
	public ClsHamburguesaDAO() {
		this.conexionABaseDeDatos = new ClsConexionBD();
	}

	@Override
	public ArrayList<ClsHamburguesa> listarHamburguesas()
	{
		System.out.println("Listando hamburguesas...");
        ArrayList<ClsHamburguesa> listaHamburguesas = new ArrayList<>();
        
        conexionABaseDeDatos.conectar();        
        try {            
            PreparedStatement sentencia = null;
            String consulta = "select * from HAMBURGUESASCOMPRADAS";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            ResultSet res = sentencia.executeQuery();
            
            while(res.next())
            {
	            ClsHamburguesa objHamburguesa= new ClsHamburguesa();
	            objHamburguesa.setIdHamburguesa(res.getInt("IDHAMBURGUESA"));
	            objHamburguesa.setIdFactura(res.getInt("IDFACTURA"));
	            objHamburguesa.setNombre(res.getString("NOMBRE"));
	            objHamburguesa.setTipo(res.getString("TIPO").charAt(0));
	            objHamburguesa.setCantidadIngredientesExtra(res.getInt("CANTIDADINGREDIENTESEXTRA"));
	            objHamburguesa.setCosto(res.getFloat("COSTO"));
	                        
	            listaHamburguesas.add(objHamburguesa);
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en el listado: "+e.getMessage());         
        }
        
        return listaHamburguesas;
	}

	@Override
	public ClsHamburguesa registrarHamburguesa(ClsHamburguesa objHamburguesa) {
		System.out.println("registrando hamburguesa...");
    	ClsHamburguesa objHamburguesaAlmacenada=null;
    	int resultado=-1;
    	 
    	try
    	{ 
    		conexionABaseDeDatos.conectar();      
            PreparedStatement sentencia = null;
            String consulta = "insert into hamburguesascompradas(IDFACTURA, NOMBRE, TIPO, CANTIDADINGREDIENTESEXTRA, COSTO) values(?,?,?,?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            
            sentencia.setInt(1, objHamburguesa.getIdFactura());
            sentencia.setString(2, objHamburguesa.getNombre());
            sentencia.setString(3, String.valueOf(objHamburguesa.getTipo()));
            sentencia.setInt(4, objHamburguesa.getCantidadIngredientesExtra());
            sentencia.setFloat(5, objHamburguesa.getCosto());
        
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("Error en la inserción: "+e.getMessage());         
        }
        
        if(resultado == 1)
        {
        	objHamburguesaAlmacenada=objHamburguesa;
        }
        return objHamburguesaAlmacenada;
	}

	@Override
	public ClsHamburguesa consultarHamburguesa(Integer idHamburguesa) {
		System.out.println("Consultar Hamburguesa...");
    	ClsHamburguesa objHamburguesa=null;
      
        conexionABaseDeDatos.conectar();        
        try 
        {            
            PreparedStatement sentencia = null;
            String consulta = "select IDHAMBURGUESA, IDFACTURA, NOMBRE, TIPO, CANTIDADINGREDIENTESEXTRA, COSTO from hamburguesascompradas where IDHAMBURGUESA=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            sentencia.setInt(1, idHamburguesa);
            ResultSet res = sentencia.executeQuery();
            
            while(res.next()){
            	System.out.println("Hamburguesa encontrada");
            	objHamburguesa = new ClsHamburguesa();
            	objHamburguesa.setIdHamburguesa(res.getInt("IDHAMBURGUESA"));
            	objHamburguesa.setIdFactura(res.getInt("IDFACTURA"));
            	objHamburguesa.setNombre(res.getString("NOMBRE"));
            	objHamburguesa.setTipo(res.getString("TIPO").charAt(0));
            	objHamburguesa.setCantidadIngredientesExtra(res.getInt("CANTIDADINGREDIENTESEXTRA"));
            	objHamburguesa.setCosto(res.getFloat("COSTO"));    
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la consulta de un empleado: "+e.getMessage());         
        }
        
        return objHamburguesa;
	}

	@Override
	public boolean eliminarHamburguesa(Integer idHamburguesa) {
		System.out.println("eliminar hamburguesa");
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "delete from hamburguesascompradas where IDHAMBURGUESA=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            sentencia.setInt(1, idHamburguesa);
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la eliminación: "+e.getMessage());         
        }
        
        return resultado == 1;
	}

}
