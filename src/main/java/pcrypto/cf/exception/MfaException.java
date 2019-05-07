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
