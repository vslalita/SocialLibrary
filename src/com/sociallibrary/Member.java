package com.sociallibrary;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.MimeMessage;


public class Member implements Observer {
	String  firstName;
	String lastName;
	String username;
	String password;
	String address;
	String Email;
	int id;

	public Member(String firstName,String lastName,String username,String password,String address,String Email){
		this.firstName=firstName;
		this.lastName=lastName;
		this.username=username;
		this.password=password;
		this.address=address;
        this.Email=Email;

	}
    
	public void setId(int id){
		this.id=id;
	}
	
	private void notifyEmail(String bookname){
		Properties props = new Properties();
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.socketFactory.port", "465");
	   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.port", "465");

	   Session mailSession = Session.getInstance(props, new javax.mail.Authenticator(){
		   protected PasswordAuthentication getPasswordAuthentication(){
			   return new PasswordAuthentication("lalita.vissamsetty@gmail.com", "dattadatta13	");
		   }
	   });

	    try {
	        MimeMessage msg = new MimeMessage(mailSession);
	        msg.setFrom();
	        msg.setRecipients(Message.RecipientType.TO,
	                          "lalita.vissamsetty@gmail.com");
	        msg.setSubject("JavaMail hello world example");
	        //msg.setSentDate(new Date());
	        msg.setText("Hello, "+bookname+"has been added by ");
	        Transport.send(msg);
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
	}
	
	public void notifyNotification(String bookname){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Insert into notifications (member_id,notification) values ("+this.id+", '"+bookname+"has been added by "+CurrentMember.cm.current_member.firstName+"')";
		    st.executeUpdate(sql);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
