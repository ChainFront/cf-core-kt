package pcrypto.cf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


/**
 * Exception for payment requests where the source account cannot fund the transaction.
 */
@ResponseStatus( HttpStatus.UNPROCESSABLE_ENTITY )
public class InsufficentBalanceException
      extends AbstractApiException
{
    public InsufficentBalanceException( final String msg )
    {
        super( msg );
    }

    public InsufficentBalanceException( final String message,
                                        final List<ErrorMessage> details )
    {
        super( message, details );
    }

    public InsufficentBalanceException( final String message,
                                        final Throwable e )
    {
        super( message, e );
    }

    public InsufficentBalanceException( final String message,
                                        final List<ErrorMessage> details,
                                        final Throwable e )
    {
        super( message, details, e );
    }
}
