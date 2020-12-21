package servidor;

import javax.swing.JFrame;
import sop_corba.GestionNotificacionPOA;
import sop_corba.GestionNotificacionPackage.notificacionDTO;
import vista.GUINotificacion;

public class GestionNotificacionImpl extends GestionNotificacionPOA{
    
    private final GUINotificacion vistaNotificacion; 
  
    public GestionNotificacionImpl() {
        vistaNotificacion = new GUINotificacion();
        vistaNotificacion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vistaNotificacion.setSize(780, 560);
        vistaNotificacion.setVisible(true);
        vistaNotificacion.setLocationRelativeTo(null);
    }
    
    @Override
    public boolean enviarNotificacionPedido(notificacionDTO objNotificacion) {
        System.out.println("Invocando al metodo enviar notificacion pedido.");
        boolean seNotifico = false;   
        if(objNotificacion != null) {
            //System.out.println("\n\t\t = = = Nuevo pedido = = = \n\t\t");
            //System.err.println("\n No de Mesa: " + objNotificacion.numeroMesa);
            //int numHam = 0;
            //for(hamburguesaNotificacionDTO hamburguesa: objNotificacion.listaHamburguesas) {
            //    System.out.println("\nHamburguesa No: " + (numHam+1));
            //    System.out.println("Tipo: " + hamburguesa.tipo);
            //    System.out.println("Cantidad de ingredientes extra: " + hamburguesa.cantidadIngredientesExtra);
            //}
            this.vistaNotificacion.mostrarNotificacion(objNotificacion);
            seNotifico = true;    
        }
        return seNotifico;
    }
}
