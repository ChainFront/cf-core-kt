package pcrypto.cf.exception;

import java.util.ArrayList;
import java.util.List;


abstract class AbstractApiException
      extends RuntimeException
{
    private List<ErrorMessage> details = new ArrayList<>();

    AbstractApiException( final String message )
    {
        super( message );
    }


    AbstractApiException( final String message,
                          final Throwable e )
    {
        super( message, e );
    }


    AbstractApiException( final String message,
                          final List<ErrorMessage> details )
    {
        this( message );
        setDetails( details );
    }

    AbstractApiException( final String message,
                          final List<ErrorMessage> details,
                          final Throwable e )
    {
        super( message, e );
        setDetails( details );
    }


    public List<ErrorMessage> getDetails()
    {
        return details;
    }

    public void setDetails( final List<ErrorMessage> details )
    {
        this.details = details;
    }
}
