/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioEntidad.vacante;

import database.Conexion;
import java.sql.ResultSet;
import java.util.List;
import javax.jws.WebService;
import org.netbeans.xml.schema.vacanteschema.SelectAllVacantes;
import org.netbeans.xml.schema.vacanteschema.Vacante;

/**
 *
 * @author Sb
 */
@WebService(serviceName = "VacanteWSDLService", portName = "VacanteWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soaprojectfinal2.vacate.vacantewsdl.VacanteWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOAprojectfinal2/vacate/VacanteWSDL", wsdlLocation = "WEB-INF/wsdl/vacanteService/vacanteWSDL.wsdl")
public class vacanteService {

    public boolean create(org.netbeans.xml.schema.vacanteschema.Vacante in) {
        boolean exito = false;

        try {
            Conexion con = new Conexion("root", "root", "juventuddb");

            String sql = "INSERT INTO vacante(nombrepuesto,actividades) VALUES('" + in.getNombrePuesto()
                    + "','" + in.getActividades() + "');";

            exito = con.insertar(sql);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return exito;
    }

    public boolean update(org.netbeans.xml.schema.vacanteschema.Vacante in) {
        //TODO implement this method
        boolean exito = false;

        try {
            Conexion con = new Conexion("root", "root", "juventuddb");
            String sql = "UPDATE vacante SET actividades='" + in.getActividades() + "' WHERE nombre='" + in.getNombrePuesto()
                    + "';";

            exito = con.actualizar(sql);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return exito;
    }

    public org.netbeans.xml.schema.vacanteschema.Vacante read(java.lang.String in) {
        Vacante vacante = new Vacante();
        try {
            Conexion con = new Conexion("root", "root", "juventuddb");

            String sql = "SELECT * from vacante WHERE nombrePuesto='" + in + "';";
            ResultSet rs = con.buscar(sql);
            
//            vacante.setIdVacante(rs.getInt("idVacante"));
            vacante.setNombrePuesto(rs.getString("nombrePuesto"));
            vacante.setActividades(rs.getString("Actividades"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return vacante;
    }

    public org.netbeans.xml.schema.vacanteschema.SelectAllVacantes readAll(java.lang.String in) {
        SelectAllVacantes allVacantes = new SelectAllVacantes();

        try {
            Conexion con = new Conexion("root", "root", "juventuddb");
            
            String sql = "SELECT * from vacante";
            ResultSet rs = con.buscar(sql);
            
            while(rs.next()){
                Vacante vacante = new Vacante();
                vacante.setNombrePuesto(rs.getString("nombrePuesto"));
                vacante.setActividades(rs.getString("actividades"));
                allVacantes.getSelectAllVacantes().add(vacante);
            }
                        
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        
        return allVacantes;
    }
    
}
