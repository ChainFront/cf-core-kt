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

    public String build( final Context context,
                         final String templateName )
    {
        return templateEngine.process( templateName, context );
    }
}
