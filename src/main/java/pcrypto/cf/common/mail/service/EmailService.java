/*
 * Copyright (c) 2019 ChainFront LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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


    public void sendTextEmail( final String recipient,
                               final String subject,
                               final String body )
    {
        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            final MimeMessageHelper messageHelper = new MimeMessageHelper( mimeMessage, MimeMessageHelper.MULTIPART_MODE_NO );
            messageHelper.setFrom( emailFrom );
            messageHelper.setTo( recipient );
            messageHelper.setSubject( subject );
            messageHelper.setText( body, false );
        };

        mailSender.send( messagePreparator );
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


