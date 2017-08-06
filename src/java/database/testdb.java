/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sb
 */
public class testdb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Conexion conn = new Conexion("root","root","juventuddb");
            
            String instruccion;
            instruccion = "insert into cliente(nombre,apellidos,telefono,direccion,correo) values('Jorge','Aguilar',123456789,'C Sumidero','fmsb@gmail.com');";
            conn.insertar(instruccion);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(testdb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
