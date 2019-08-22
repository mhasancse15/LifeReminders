package com.timetable.todolist.Schedule.lifereminders;

/**
 * Created by David Wu on 23/01/2018.
 */

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyAuthenticator extends Authenticator{

    String username = "";
    String password = "";

    public MyAuthenticator (String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication ()
    {
        return new PasswordAuthentication(username, password);
    }

}
