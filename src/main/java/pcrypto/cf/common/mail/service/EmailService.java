package pcrypto.cf.common.mail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


@Slf4j
@Service
public class EmailService
{
    private static String emailFrom = "no-reply@chainfront.io";

    private final JavaMailSender mailSender;

    private final EmailContentBuilder emailContentBuilder;


    @Autowired
    public EmailService( final JavaMailSender mailSender,
                         final EmailContentBuilder emailContentBuilder )
    {
        this.mailSender = mailSender;
        this.emailContentBuilder = emailContentBuilder;
    }



    public void sendRegistrationEmail( final String recipient,
                                       final String registrationConfirmationUrl )
    {
        final Context context = new Context();
        context.setVariable( "message", "Welcome " + recipient );
        context.setVariable( "registrationConfirmationUrl", registrationConfirmationUrl );

        prepareAndSend( recipient,
                        "ChainFront Account Confirmation",
                        context,
                        "registrationMail" );
    }



    private void prepareAndSend( final String recipient,
                                 final String subject,
                                 final Context context,
                                 final String templateName )
          throws MailException
    {
        // Prepare a MIME email message that works across most of the major email clients.
        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            final MimeMessageHelper messageHelper = new MimeMessageHelper( mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED );
            messageHelper.setFrom( emailFrom );
            messageHelper.setTo( recipient );
            messageHelper.setSubject( subject );

            final String content = emailContentBuilder.build( context, templateName );
            messageHelper.setText( content, true );

            // Add our logo image as an inline image available as cid:chainFrontLogo
            messageHelper.addInline( "chainFrontLogo.png", new ClassPathResource( "images/CF_Purple-transparent.png" ) );
        };

        mailSender.send( messagePreparator );
    }
}


