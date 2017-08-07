/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioUtilidad.notificacion;

import javax.jws.WebService;
import java.util.Properties;
import javax.jws.WebService;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Jorge
 */
@WebService(serviceName = "notificacionWSDLService", portName = "notificacionWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soaprojectfinal2.notificacion.notificacionwsdl.NotificacionWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOAprojectfinal2/notificacion/notificacionWSDL", wsdlLocation = "WEB-INF/wsdl/notificacionService/notificacionWSDL.wsdl")
public class notificacionService {
 
    public java.lang.String notificacion(org.netbeans.xml.schema.notificacionlschema.Correo in) throws AddressException, MessagingException {
        String smtpServer = "smtp.gmail.com";
        int port = 587;
        final String userid = "jorgeluis9412@gmail.com";//change accordingly
        final String password = "luisja12";//change accordingly
        String contentType = "text/html";
        // String subject = datos.getTipo();
        String from = "jorgeluis9412@gmail.com";
        // String to = datos.getReceptor();//some invalid address
        String bounceAddr = "jorgeluis9412@gmail.com";//change accordingly
        // String body = datos.getMensaje() ;

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "587");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.from", bounceAddr);

        Session mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userid, password);
            }
        });

        MimeMessage message = new MimeMessage(mailSession);
        message.addFrom(InternetAddress.parse(from));
        message.setRecipients(Message.RecipientType.TO, in.getReceptor());
        message.setSubject(in.getTipo());
        message.setContent(in.getMensaje(), contentType);

        Transport transport = mailSession.getTransport();
        try {
            System.out.println("Sending ....");
            transport.connect(smtpServer, port, userid, password);
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            System.out.println("Sending done ...");
            return "Mensaje Enviado";
        } catch (Exception e) {
            System.out.println("Error Sending: ");
            e.printStackTrace();

        }
        transport.close();
        return null;
    }// end function main()
        
     
        
    
}
