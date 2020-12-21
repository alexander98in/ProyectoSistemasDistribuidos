package servidor;

import javax.swing.JFrame;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import sop_corba.GestionNotificacion;
import sop_corba.GestionNotificacionHelper;
import vista.GUINotificacion;

public class ServidorDeObjetos {

    public static void main(String args[]) {
        try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";
            //System.out.println("Ingrese la dirección IP donde escucha el n_s");
            vec[1] = "localhost";
            vec[2] = "-ORBInitialPort";
            //System.out.println("Ingrese el puerto donde escucha el n_s");
            vec[3] = "2020";
         
            // crea e inicia el ORB
            ORB orb = ORB.init(vec, null);      
            POA rootpoa =  POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            
            //*** crea una instancia del servant
            GestionNotificacionImpl objRemoto = new GestionNotificacionImpl();

            //*** se genera la referencia del servant
            org.omg.CORBA.Object obj = rootpoa.servant_to_reference(objRemoto);
            GestionNotificacion href = GestionNotificacionHelper.narrow(obj);

            // se obtiene una referencia al name service
            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

            // *** se realiza el binding de la referencia del servant en el N_S ***
            String name = "gestionNotificacion";
            NameComponent path[] = ncref.to_name(name);
            ncref.rebind(path, href);

            System.out.println("El Servidor esta listo y esperando ...");

            // esperan por las invocaciones desde los clientes
            orb.run();
        } 
        catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("Servidor: Saliendo ...");
    }
}
