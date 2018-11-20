package pcrypto.cf.common.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class EmailService
{
    private static final Logger logger = LoggerFactory.getLogger( EmailService.class );

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailContentBuilder emailContentBuilder;

    private String emailFrom = "danderson@pcrypto.io";



    public void sendRegistrationEmail( final String recipient )
          throws MessagingException
    {
        prepareAndSend( recipient, "ChainFront account confirmation", "Welcome " + recipient, "registrationMail" );
    }



    private void prepareAndSend( final String recipient,
                                 final String subject,
                                 final String message,
                                 final String templateName )
          throws MailException
    {
        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            final MimeMessageHelper messageHelper = new MimeMessageHelper( mimeMessage );
            messageHelper.setFrom( emailFrom );
            messageHelper.setTo( recipient );
            messageHelper.setSubject( subject );

            final String content = emailContentBuilder.build( message, templateName );
            messageHelper.setText( content, true );
        };

        mailSender.send( messagePreparator );
    }
}


