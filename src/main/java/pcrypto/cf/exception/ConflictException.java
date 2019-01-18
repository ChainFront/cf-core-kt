package pcrypto.cf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@ResponseStatus( HttpStatus.CONFLICT )
public class ConflictException
      extends AbstractApiException
{
    public ConflictException( final String msg )
    {
        super( msg );
    }

    public ConflictException( final String message,
                              final Throwable e )
    {
        super( message, e );
    }

    public ConflictException( final String message,
                              final List<ErrorMessage> details )
    {
        super( message, details );
    }
}
