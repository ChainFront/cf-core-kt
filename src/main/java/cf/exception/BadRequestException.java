package pcrypto.cf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@ResponseStatus( HttpStatus.BAD_REQUEST )
public class BadRequestException
      extends AbstractApiException
{
    public BadRequestException( final String msg )
    {
        super( msg );
    }

    public BadRequestException( final String message,
                                final Throwable e )
    {
        super( message, e );
    }

    public BadRequestException( final String message,
                                final List<ErrorMessage> details )
    {
        super( message, details );
    }
}
