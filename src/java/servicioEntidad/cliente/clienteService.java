/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioEntidad.cliente;

import database.Conexion;
import javax.jws.WebService;

/**
 *
 * @author Sb
 */
@WebService(serviceName = "clienteWSDLService", portName = "clienteWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soaprojectfinal2.java.clientewsdl.ClienteWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOAprojectfinal2/java/clienteWSDL", wsdlLocation = "WEB-INF/wsdl/clienteService/clienteWSDL.wsdl")
public class clienteService {

    public boolean create(org.netbeans.xml.schema.clienteschema.Cliente in) {
        //TODO implement this method
        boolean exito = false;

        try {
            Conexion con = new Conexion("root", "root", "juventuddb");

            String sql = "INSERT INTO cliente(nombre,apellidos,telefono,direccion,correo) VALUES('"
                    + in.getNombre() + "','" + in.getApellidos() + "','" + in.getTelefono() + "','"
                    + in.getDireccion() + "','" + in.getCorreo() + "');";

            exito = con.insertar(sql);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return exito;
    }

    public boolean update(org.netbeans.xml.schema.clienteschema.Cliente in) {
        //TODO implement this method
        boolean exito = false;

        try {
            Conexion con = new Conexion("root", "root", "juventuddb");
            String sql = "UPDATE cliente SET telefono=" + in.getTelefono() +", direccion='" + in.getDireccion() + 
                    "' WHERE nombre='" + in.getNombre() + "' AND correo='" + in.getCorreo() + "';";

            exito = con.actualizar(sql);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return exito;
    }
    
}
