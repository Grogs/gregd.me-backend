package me.gregd.www.resource

import javax.ws.rs._
import java.util._
import java.io._
import javax.mail._
import javax.mail.internet._
import javax.ws.rs.core.MediaType
import scala.collection.JavaConverters
import scala.collection.JavaConversions._
import me.gregd.www.model._
import me.gregd.www.data._

@Path("/contactform")
class ContacForm {
  val mailProps = new Properties()
  mailProps.load(new FileInputStream("/etc/mail.properties"))
  
  @POST
  @Consumes(Array(MediaType.APPLICATION_FORM_URLENCODED))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def update(@FormParam("email") email:String, @FormParam("body") body:String) = {
    try {
	  sendEmail(from=email,body=body);
      """{"success": "Your message has been sent to my personal inbox. I'll get back to you soon."}"""
    } catch {case ex:MessagingException =>
      System.err.println("Cannot send email. " + ex);
      """{"problem": "Cannot send email. Please email me directly at greg@dorrell.me"}"""
    }
  }
  
  def sendEmail( //Using JavaMail API
    from:String, to:String = "greg@dorrell.me",
     subject:String = "Contact Form::", body:String 
  ) = {
    val message = new MimeMessage(
        Session.getInstance(mailProps,
        	new Authenticator() {override def getPasswordAuthentication() = new PasswordAuthentication(
        		mailProps.getProperty("gmail.username"),mailProps.getProperty("gmail.password")
        	)}
        ));
      message.addFrom( Array(new InternetAddress(from) ));
      message.setReplyTo(Array(new InternetAddress(from) ))
      message.addRecipient(
        Message.RecipientType.TO, new InternetAddress(to)
      );
      message.setSubject( subject );
      message.setText( body );
      Transport.send( message );
  }

  
}

