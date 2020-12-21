package co.edu.unicauca.distribuidos.core.repositories.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.ClsValores;
import co.edu.unicauca.distribuidos.core.repositories.conexion.ClsConexionBD;

@Service
public class ClsValoresDAO implements IntAccesoDatosValoresDAO {
	
	private final ClsConexionBD conexionABaseDeDatos;
	
	public ClsValoresDAO() {
		this.conexionABaseDeDatos = new ClsConexionBD();
	}

	@Override
	public ClsValores consultarValores() {
		System.out.println("Consultar valores o precios...");
    	ClsValores objValores=null;
      
        conexionABaseDeDatos.conectar();        
        try 
        {            
            PreparedStatement sentencia = null;
            String consulta = "select IDVALOR, IDEMPRESA, VALORINGREDIENTEEXTRA, VALORHAMBURGUESAPEQUENIA,"
            		+ " VALORHAMBURGUESAMEDIANA, VALORHAMBURGUESAGRANDE from valores where IDVALOR=1";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            ResultSet res = sentencia.executeQuery();
            
            while(res.next()){
            	System.out.println("Valores encontrados");
            	objValores = new ClsValores();
            	objValores.setIdValor(res.getInt("IDVALOR"));
            	objValores.setIdEmpresa(res.getInt("IDEMPRESA"));
            	objValores.setValorIngredienteExtra(res.getFloat("VALORINGREDIENTEEXTRA"));
            	objValores.setValorHamburguesaPequenia(res.getFloat("VALORHAMBURGUESAPEQUENIA"));
            	objValores.setValorHamburguesaMediana(res.getFloat("VALORHAMBURGUESAMEDIANA"));
            	objValores.setValorHamburgeusaGrande(res.getFloat("VALORHAMBURGUESAGRANDE"));
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("Error en la consulta de los valores: "+e.getMessage());         
        }
        
        return objValores;
	}


	@Override
	public ClsValores actualizarValores(ClsValores objValores) {
		System.out.println("Actualizar valores");
    	ClsValores objValoresActualizados=null;
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "update valores set VALORINGREDIENTEEXTRA=?,"
                                                 + "VALORHAMBURGUESAPEQUENIA=?,"
                                                 + "VALORHAMBURGUESAMEDIANA=?,"
                                                 + "VALORHAMBURGUESAGRANDE=?"
                                                 + " where IDVALOR = ?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            
            sentencia.setFloat(1, objValores.getValorIngredienteExtra());
            sentencia.setFloat(2, objValores.getValorHamburguesaPequenia());
            sentencia.setFloat(3, objValores.getValorHamburguesaMediana());
            sentencia.setFloat(4, objValores.getValorHamburgeusaGrande());
            sentencia.setInt(5, objValores.getIdValor());
            
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("Error en la actualización: "+e.getMessage());         
        }
        
        if(resultado == 1)
        {
        	objValoresActualizados = objValores;
        }
        return objValoresActualizados;
	}

}
