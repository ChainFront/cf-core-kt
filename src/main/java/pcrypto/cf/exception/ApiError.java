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

import java.util.List;


/**
 * Model for all errors that occur during API execution and result in a 4xx or 5xx HTTP status code.
 */
@ApiModel( description = "Representation of an error that occurs during API execution and results in a 4xx or 5xx HTTP status code." )
public class ApiError
{
    @ApiModelProperty( value = "The value of the HTTP status code.",
                       position = 10 )
    private Integer httpStatusCode;

    @ApiModelProperty( value = "Description of the HTTP status code.",
                       position = 11 )
    private String httpMessage;

    @ApiModelProperty( value = "Description of the error.",
                       position = 20 )
    private String description;

    @ApiModelProperty( value = "An optional list of ErrorMessage objects which provide further details as to the cause of this error.",
                       position = 30 )
    private List<ErrorMessage> details;

    @ApiModelProperty( value = "A reference identifier used by support to assist with certain error messages.",
                       position = 40 )
    private String supportReferenceId;


    public ApiError()
    {
        super();
    }

    public Integer getHttpStatusCode()
    {
        return httpStatusCode;
    }


    public void setHttpStatusCode( final Integer httpStatusCode )
    {
        this.httpStatusCode = httpStatusCode;
    }


    public String getHttpMessage()
    {
        return httpMessage;
    }


    public void setHttpMessage( final String httpMessage )
    {
        this.httpMessage = httpMessage;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription( final String description )
    {
        this.description = description;
    }


    public List<ErrorMessage> getDetails()
    {
        return details;
    }


    public void setDetails( final List<ErrorMessage> details )
    {
        this.details = details;
    }


    public String getSupportReferenceId()
    {
        return supportReferenceId;
    }


    public void setSupportReferenceId( final String supportReferenceId )
    {
        this.supportReferenceId = supportReferenceId;
    }

    @Override
    public String toString()
    {
        return "ApiError{" +
               "httpStatusCode=" + httpStatusCode +
               ", httpMessage='" + httpMessage + '\'' +
               ", description='" + description + '\'' +
               ", details=" + details +
               ", supportReferenceId='" + supportReferenceId + '\'' +
               '}';
    }


    /**
     * Convenience fluent Builder class for constructing ApiError objects.
     */
    public static final class Builder
    {
        private Integer httpStatusCode;
        private String httpMessage;
        private String description;
        private List<ErrorMessage> details;
        private String supportReferenceId;

        private Builder()
        {
        }

        public static Builder apiError()
        {
            return new Builder();
        }

        public Builder withHttpStatusCode( final Integer httpStatusCode )
        {
            this.httpStatusCode = httpStatusCode;
            return this;
        }

        public Builder withHttpMessage( final String httpMessage )
        {
            this.httpMessage = httpMessage;
            return this;
        }

        public Builder withDescription( final String description )
        {
            this.description = description;
            return this;
        }

        public Builder withDetails( final List<ErrorMessage> details )
        {
            this.details = details;
            return this;
        }

        public Builder withSupportReferenceId( final String supportReferenceId )
        {
            this.supportReferenceId = supportReferenceId;
            return this;
        }

        public ApiError build()
        {
            final ApiError apiError = new ApiError();
            apiError.setHttpStatusCode( httpStatusCode );
            apiError.setHttpMessage( httpMessage );
            apiError.setDescription( description );
            apiError.setDetails( details );
            apiError.setSupportReferenceId( supportReferenceId );
            return apiError;
        }
    }
}
