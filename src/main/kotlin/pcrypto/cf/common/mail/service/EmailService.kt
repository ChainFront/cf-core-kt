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

package pcrypto.cf.common.mail.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import javax.mail.internet.MimeMessage


@Service
class EmailService
@Autowired constructor(
    private val mailSender: JavaMailSender,
    private val emailContentBuilder: EmailContentBuilder
) {

    fun sendTextEmail(
        recipient: String,
        subject: String,
        body: String
    ) {
        val messagePreparator = { mimeMessage: MimeMessage ->
            val messageHelper = MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_NO)
            messageHelper.setFrom(emailFrom)
            messageHelper.setTo(recipient)
            messageHelper.setSubject(subject)
            messageHelper.setText(body, false)
        }

        mailSender.send(messagePreparator)
    }

    fun sendRegistrationEmail(
        recipient: String,
        registrationConfirmationUrl: String
    ) {
        val context = Context()
        context.setVariable("message", "Welcome $recipient")
        context.setVariable("registrationConfirmationUrl", registrationConfirmationUrl)

        prepareAndSend(
            recipient,
            "ChainFront Account Confirmation",
            context,
            "registrationMail"
        )
    }


    @Throws(MailException::class)
    private fun prepareAndSend(
        recipient: String,
        subject: String,
        context: Context,
        templateName: String
    ) {
        // Prepare a MIME email message that works across most of the major email clients.
        val messagePreparator = { mimeMessage: MimeMessage ->
            val messageHelper = MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED)
            messageHelper.setFrom(emailFrom)
            messageHelper.setTo(recipient)
            messageHelper.setSubject(subject)

            val content = emailContentBuilder.build(context, templateName)
            messageHelper.setText(content, true)

            // Add our logo image as an inline image available as cid:chainFrontLogo
            messageHelper.addInline("chainFrontLogo.png", ClassPathResource("images/CF_Purple-transparent.png"))
        }

        mailSender.send(messagePreparator)
    }

    companion object {
        private val emailFrom = "no-reply@chainfront.io"
    }
}


