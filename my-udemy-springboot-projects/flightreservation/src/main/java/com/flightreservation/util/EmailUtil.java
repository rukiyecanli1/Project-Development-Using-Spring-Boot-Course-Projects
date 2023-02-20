package com.flightreservation.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.flightreservation.services.ReservationServiceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {

	//Spring will inject that values from application.properties into these values at runtime
	@Value("${com.flightreservation.email.subject}")
	private String EMAIL_SUBJECT ;
	@Value("${com.flightreservation.email.body}")
	private String EMAIL_BODY ;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
	
	@Autowired
	private JavaMailSender sender;
	
	
	//We will send pdf file to the user email address, when he completed
	//his reservation and submit the confirm button
	public void sendItinerary(String toAddress, String filePath) {
		LOGGER.info("Inside sendItinerary()");
		MimeMessage message = sender.createMimeMessage();
		
//  I dont use real gmail address in application.properties so this part can't work
//	try {
//		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
//		messageHelper.setTo(toAddress);
//		messageHelper.setSubject(EMAIL_SUBJECT);
//		messageHelper.setText(EMAIL_BODY);
//		//Whatever we pass here will be attached to the email
//		messageHelper.addAttachment("Itinerary", new File(filePath));
//		sender.send(message);
//	} catch (MessagingException e) {
//		LOGGER.error("Exception inside sendItinerary(): "+e);
//	}
	}
}
