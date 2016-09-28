/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Daniel
 */
public class MailUteis {

    
    public static boolean enviaEmail(String destinatario, final String remetente, final String senha,String assunto,
            String mensagem){
        
        Properties props = new Properties();
        
            /** Parâmetros de conexão com servidor Hotmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
            @Override
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication(remetente, senha);
                             }
                        });

            /** Ativa Debug para sessão */
            //session.setDebug(true);

            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress(remetente)); //Remetente

                  message.setRecipients(Message.RecipientType.TO, 
                                    InternetAddress.parse(destinatario)); //Destinatário(s)
                  
                  message.setSubject(assunto);//Assunto
                  
                  message.setText(mensagem);
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                    System.out.println("E-mail enviado com sucesso");
                  return true;
                  

             } catch (MessagingException e) {
                 return false;
                  
            }
      }
    
      public static boolean enviaEmailUsuario(String destinatario, String usuario, String senha){
        
        Properties props = new Properties();
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        Date date = new Date();

        String data = df.format(date);
        
            /** Parâmetros de conexão com servidor Hotmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
            @Override
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("escolaoficinadamusica@hotmail.com", "oficina12");
                             }
                        });

            /** Ativa Debug para sessão */
            //session.setDebug(true);

            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("escolaoficinadamusica@hotmail.com")); //Remetente
                  

                  message.setRecipients(Message.RecipientType.TO, 
                                    InternetAddress.parse(destinatario)); //Destinatário(s)
                  
                  message.setSubject("Envio/Recuperação/Alteração de Senha Sistema Oficina da Música");//Assunto
                  
                  
                  
                  message.setText("Olá,\r\n"
                                 +"Seu nome de usuário é : "+usuario+"\r\n"
                                 +"Sua senha é :"+senha+"\r\n"
                                 +"\r\n"
                                 +"Assis - "+data+"\r\n"
                                 +"\r\n"
                                 +"Products Factory - System\r\n"
                                 +"\r\n"
                                 +"\r\n"
                                 +"Este é um e-mail automático, não responda este e-mail.");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                    System.out.println("E-mail enviado com sucesso");
                  return true;
                  

             } catch (MessagingException e) {
                 return false;
                  
            }
      }


}
