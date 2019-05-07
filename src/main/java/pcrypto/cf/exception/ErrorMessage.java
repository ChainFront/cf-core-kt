/*
 * Copyright (c) 2019 ChainFront LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
