package sop_corba.GestionNotificacionPackage;


/**
* sop_corba/GestionNotificacionPackage/hamburguesaNotificacionDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* domingo 20 de diciembre de 2020 10:47:51 PM COT
*/

public final class hamburguesaNotificacionDTO implements org.omg.CORBA.portable.IDLEntity
{
  public char tipo = (char)0;
  public int cantidadIngredientesExtra = (int)0;

  public hamburguesaNotificacionDTO ()
  {
  } // ctor

  public hamburguesaNotificacionDTO (char _tipo, int _cantidadIngredientesExtra)
  {
    tipo = _tipo;
    cantidadIngredientesExtra = _cantidadIngredientesExtra;
  } // ctor

} // class hamburguesaNotificacionDTO
