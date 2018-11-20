package pcrypto.cf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


/**
 * Exception for service errors when communicating with an external blockchain network.
 */
@ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
public class BlockchainServiceException
      extends AbstractApiException
{
    public BlockchainServiceException( final String msg )
    {
        super( msg );
    }

    public BlockchainServiceException( final String message,
                                       final List<ErrorMessage> details )
    {
        super( message, details );
    }

    public BlockchainServiceException( final String message,
                                       final Throwable e )
    {
        super( message, e );
    }

    public BlockchainServiceException( final String message,
                                       final List<ErrorMessage> details,
                                       final Throwable e )
    {
        super( message, details, e );
    }
}
