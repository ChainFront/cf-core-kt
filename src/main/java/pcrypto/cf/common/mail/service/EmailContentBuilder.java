package pcrypto.cf.common.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
public class EmailContentBuilder
{

    private final TemplateEngine templateEngine;

    @Autowired
    public EmailContentBuilder( final TemplateEngine templateEngine )
    {
        this.templateEngine = templateEngine;
    }

    public String build( final String message,
                         final String templateName )
    {
        final Context context = new Context();
        context.setVariable( "message", message );
        return templateEngine.process( templateName, context );
    }
}
