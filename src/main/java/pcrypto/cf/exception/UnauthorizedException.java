package pcrypto.cf.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus( HttpStatus.UNAUTHORIZED )
public class UnauthorizedException
      extends AbstractApiException
{

    public UnauthorizedException( final String msg )
    {
        super( msg );
    }
}
