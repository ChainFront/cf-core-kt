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
