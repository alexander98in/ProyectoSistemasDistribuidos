package sop_corba.GestionNotificacionPackage;

/**
* sop_corba/GestionNotificacionPackage/notificacionDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* domingo 20 de diciembre de 2020 10:47:51 PM COT
*/

public final class notificacionDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.GestionNotificacionPackage.notificacionDTO value = null;

  public notificacionDTOHolder ()
  {
  }

  public notificacionDTOHolder (sop_corba.GestionNotificacionPackage.notificacionDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.GestionNotificacionPackage.notificacionDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.GestionNotificacionPackage.notificacionDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.GestionNotificacionPackage.notificacionDTOHelper.type ();
  }

}
