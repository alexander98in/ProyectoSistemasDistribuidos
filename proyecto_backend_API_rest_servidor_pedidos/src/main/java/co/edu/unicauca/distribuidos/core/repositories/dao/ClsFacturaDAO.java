package co.edu.unicauca.distribuidos.core.repositories.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.ClsFactura;
import co.edu.unicauca.distribuidos.core.repositories.conexion.ClsConexionBD;

@Service
public class ClsFacturaDAO implements IntAccesoDatosFacturaDAO {
	
	private final ClsConexionBD conexionABaseDeDatos;
	
	public ClsFacturaDAO() {
		this.conexionABaseDeDatos = new ClsConexionBD();
	}

	@Override
	public ArrayList<ClsFactura> listarFacturas() {
		System.out.println("Listando facturas...");
        ArrayList<ClsFactura> listaFacturas = new ArrayList<>();
        
        conexionABaseDeDatos.conectar();        
        try {            
            PreparedStatement sentencia = null;
            String consulta = "select * from FACTURAS";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            ResultSet res = sentencia.executeQuery();
            
            while(res.next())
            {
            	ClsFactura objFactura = new ClsFactura();
            	objFactura.setIdFactura(res.getInt("IDFACTURA"));
            	objFactura.setIdEmpresa(res.getInt("IDEMPRESA"));
            	objFactura.setNumHamburguesasPequenias(res.getInt("HAMBURGUESASPEQUENIAS"));
            	objFactura.setNumHamburguesasMedianas(res.getInt("HAMBURGUESASMEDIANAS"));
            	objFactura.setNumHamburguesasGrandes(res.getInt("HAMBURGUESASGRANDES"));
            	objFactura.setCostoSinIVA(res.getFloat("COSTOSINIVA"));
            	objFactura.setIVA(res.getFloat("IVA"));
            	objFactura.setCostoConIVA(res.getFloat("COSTOCONIVA"));
            	
                listaFacturas.add(objFactura);
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la inserción: "+e.getMessage());         
        }
        
        return listaFacturas;
	}

	@Override
	public ClsFactura registrarFactura(ClsFactura objFactura) {
		System.out.println("registrando factura...");
    	ClsFactura objFacturaAlmacenada=null;
    	int resultado=-1;
    	 
    	try
    	{ 
    		conexionABaseDeDatos.conectar();
                  
            PreparedStatement sentencia = null;
            String consulta = "insert into facturas(IDEMPRESA, HAMBURGUESASPEQUENIAS, HAMBURGUESASMEDIANAS, HAMBURGUESASGRANDES, COSTOSINIVA, IVA, COSTOCONIVA) "
            		+ "values(?,?,?,?,?,?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            
            sentencia.setInt(1, objFactura.getIdEmpresa());
            sentencia.setInt(2, objFactura.getNumHamburguesasPequenias());
            sentencia.setInt(3, objFactura.getNumHamburguesasMedianas());
            sentencia.setInt(4, objFactura.getNumHamburguesasGrandes());
            sentencia.setFloat(5, objFactura.getCostoSinIVA());
            sentencia.setFloat(6, objFactura.getIVA());
            sentencia.setFloat(7, objFactura.getCostoConIVA());
        
            resultado = sentencia.executeUpdate(); 
            
            //Obtenemos el id de la factura registrada
            String consulta_last_ID = "select last_insert_id() AS ULTIMO_ID";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta_last_ID);
            Integer idFactura=0;
            
            ResultSet res_last_ID = sentencia.executeQuery();
            
            while(res_last_ID.next()){
            	System.out.println("Ultimo ID encontrado...");
            	idFactura = res_last_ID.getInt("ULTIMO_ID");	   
            }
            
            objFactura.setIdFactura(idFactura);
            
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("Error en la inserción: "+e.getMessage());         
        }
        
        if(resultado == 1)
        {
        	objFacturaAlmacenada = objFactura;
        }
        return objFacturaAlmacenada;
	}

	@Override
	public ClsFactura consultarFactura(Integer idFactura) {
		System.out.println("Consultar Factura...");
    	ClsFactura objFactura=null;
      
        conexionABaseDeDatos.conectar();        
        try 
        {            
            PreparedStatement sentencia = null;
            String consulta = "select IDFACTURA, IDEMPRESA, HAMBURGUESASPEQUENIAS, HAMBURGUESASMEDIANAS, HAMBURGUESASGRANDES, COSTOSINIVA, IVA, COSTOCONIVA "
            		+ "from facturas where IDFACTURA=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            sentencia.setInt(1, idFactura);
            ResultSet res = sentencia.executeQuery();
            
            while(res.next()){
            	System.out.println("Factura encontrada");
            	objFactura = new ClsFactura();
            	
            	objFactura.setIdFactura(res.getInt("IDFACTURA"));
            	objFactura.setIdEmpresa(res.getInt("IDEMPRESA"));
            	objFactura.setNumHamburguesasPequenias(res.getInt("HAMBURGUESASPEQUENIAS"));
            	objFactura.setNumHamburguesasMedianas(res.getInt("HAMBURGUESASMEDIANAS"));
            	objFactura.setNumHamburguesasGrandes(res.getInt("HAMBURGUESASGRANDES"));
            	objFactura.setCostoSinIVA(res.getFloat("COSTOSINIVA"));
            	objFactura.setIVA(res.getFloat("IVA"));
            	objFactura.setCostoConIVA(res.getFloat("COSTOCONIVA"));
            	    
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la consulta de un empleado: "+e.getMessage());         
        }
        
        return objFactura;
	}

	@Override
	public boolean eliminarFactura(Integer idFactura) {
		System.out.println("Eliminar factua...");
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "delete from facturas where IDFACTURA=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            sentencia.setInt(1, idFactura);
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } 
        catch (SQLException e) {
        	System.out.println("error en la eliminación: "+e.getMessage());         
        }
        
        return resultado == 1;
	}

	@Override
	public boolean borrarFacturas() {
		System.out.println("Eliminar todas las facturas");
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "DELETE FROM FACTURAS";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("Error en la eliminación: "+e.getMessage());         
        }
        
        return resultado >= 1;
	}
}
