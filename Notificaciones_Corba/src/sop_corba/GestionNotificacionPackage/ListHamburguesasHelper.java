package sop_corba.GestionNotificacionPackage;


/**
* sop_corba/GestionNotificacionPackage/ListHamburguesasHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* domingo 20 de diciembre de 2020 10:47:51 PM COT
*/

abstract public class ListHamburguesasHelper
{
  private static String  _id = "IDL:sop_corba/GestionNotificacion/ListHamburguesas:1.0";

  public static void insert (org.omg.CORBA.Any a, sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTO[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTO[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTOHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (sop_corba.GestionNotificacionPackage.ListHamburguesasHelper.id (), "ListHamburguesas", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTO[] read (org.omg.CORBA.portable.InputStream istream)
  {
    sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTO value[] = null;
    int _len0 = istream.read_long ();
    value = new sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTO[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTOHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTO[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTOHelper.write (ostream, value[_i0]);
  }

}