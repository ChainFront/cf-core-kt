package pcrypto.cf.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus( HttpStatus.NOT_FOUND )
public class NotFoundException
      extends AbstractApiException
{

    public NotFoundException( final String msg )
    {
        super( msg );
    }
}
