package pcrypto.cf.exception;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * Simple representation of an api message.
 */
@ApiModel( description = "Simple representation of an error message." )
public class ErrorMessage
      implements Serializable
{
    private static final long serialVersionUID = -8470489093537747778L;


    @ApiModelProperty( value = "Message informational text.",
                       position = 10 )
    private String message;


    @ApiModelProperty( value = "Optional diagnostic information.",
                       position = 20 )
    private String diagnostic;


    public ErrorMessage()
    {
    }


    public ErrorMessage( final String message,
                         final String diagnostic )
    {
        this.message = message;
        this.diagnostic = diagnostic;
    }


    public String getMessage()
    {
        return message;
    }


    public void setMessage( final String message )
    {
        this.message = message;
    }



    public String getDiagnostic()
    {
        return diagnostic;
    }


    public void setDiagnostic( final String diagnostic )
    {
        this.diagnostic = diagnostic;
    }



    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer( "Message{" );
        sb.append( "message='" ).append( message ).append( '\'' );
        sb.append( ", diagnostic='" ).append( diagnostic ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }
}
