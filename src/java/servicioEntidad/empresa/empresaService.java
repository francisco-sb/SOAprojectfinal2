package servicioEntidad.empresa;

import database.Conexion;
import java.sql.ResultSet;
import javax.jws.WebService;
import org.netbeans.xml.schema.empresaschema.Empresa;

/**
 *
 * @author Sb
 */
@WebService(serviceName = "empresaWSDLService", portName = "empresaWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soaprojectfinal2.empresa.empresawsdl.EmpresaWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOAprojectfinal2/empresa/empresaWSDL", wsdlLocation = "WEB-INF/wsdl/empresaService/empresaWSDL.wsdl")
public class empresaService {

    public boolean create(org.netbeans.xml.schema.empresaschema.Empresa in) {
        //TODO implement this method
        boolean exito = false;

        try {
            Conexion con = new Conexion("root", "root", "juventuddb");

            String sql = "INSERT INTO empresa(nombre,direccion,correo) VALUES('" + in.getNombre() + "','"
                    + in.getDireccion() + "','" + in.getCorreo() + "');";

            exito = con.insertar(sql);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return exito;
    }

    public boolean update(org.netbeans.xml.schema.empresaschema.Empresa in) {
        boolean exito = false;

        try {
            Conexion con = new Conexion("root", "root", "juventuddb");
            String sql = "UPDATE empresa SET direccion='" + in.getDireccion() + "' WHERE nombre='" + in.getNombre()
                    + "' AND correo='" + in.getCorreo() + "';";

            exito = con.actualizar(sql);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return exito;
    }

    public org.netbeans.xml.schema.empresaschema.Empresa read(java.lang.String in) {
        Empresa empresa = new Empresa();
        try {
            Conexion con = new Conexion("root", "root", "juventuddb");

            String sql = "SELECT * from empresa WHERE nombre='" + in + "';";
            ResultSet rs = con.buscar(sql);
            
            empresa.setNombre(rs.getString("Nombre"));
            empresa.setDireccion(rs.getString("Direccion"));
            empresa.setCorreo(rs.getString("Correo"));
            
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return empresa;
    }

}
