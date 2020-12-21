package co.edu.unicauca.distribuidos.core.repositories.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.ClsEmpresa;
import co.edu.unicauca.distribuidos.core.repositories.conexion.ClsConexionBD;

@Service
public class ClsEmpresaDAO implements IntAccesoDatosEmpresaDAO {
	
	private final ClsConexionBD conexionABaseDeDatos;
	
	/**
	 * Constructor de la clase ClsEmpresaDAO
	 */
	public ClsEmpresaDAO() {
		this.conexionABaseDeDatos = new ClsConexionBD();
	}
	
	@Override
	public ClsEmpresa consultarInformacionEmpresa() {
		System.out.println("Consultar informacion empresa...");
    	ClsEmpresa objEmpresa=null;
      
        conexionABaseDeDatos.conectar();        
        try 
        {            
            PreparedStatement sentencia = null;
            String consulta = "select IDEMPRESA, NOMBRE, NIT from empresas where IDEMPRESA=1";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            ResultSet res = sentencia.executeQuery();
            
            while(res.next()){
            	System.out.println("Empresa encontrada");
            	objEmpresa = new ClsEmpresa();
            	objEmpresa.setIdEmpresa(res.getInt("IDEMPRESA"));
            	objEmpresa.setNombre(res.getString("NOMBRE"));
            	objEmpresa.setNIT(res.getString("NIT"));
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("Error en la consulta de la informacion de la empresa: "+e.getMessage());         
        }
        
        return objEmpresa;
	}

	@Override
	public ClsEmpresa actualizarInformacionEmpresa(ClsEmpresa objEmpresa) {
		System.out.println("Actualizar informacion de la empresa");
    	ClsEmpresa objEmpresaActualizada=null;
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "update empresas set NOMBRE=?,"
                                                 + "NIT=?"
                                                 + "where IDEMPRESA = 1";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            
            sentencia.setString(1, objEmpresa.getNombre());
            sentencia.setString(2, objEmpresa.getNIT());
     
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("Error en la actualización: "+e.getMessage());         
        }
        
        if(resultado == 1)
        {
        	objEmpresaActualizada=objEmpresa;
        }
        return objEmpresaActualizada;        
	}

}
