package pcrypto.cf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


/**
 * Exception for MFA related exceptions.
 */
@ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
public class MfaException
      extends AbstractApiException
{
    public MfaException( final String msg )
    {
        super( msg );
    }

    public MfaException( final String message,
                         final List<ErrorMessage> details )
    {
        super( message, details );
    }

    public MfaException( final String message,
                         final Throwable e )
    {
        super( message, e );
    }

    public MfaException( final String message,
                         final List<ErrorMessage> details,
                         final Throwable e )
    {
        super( message, details, e );
    }
}
