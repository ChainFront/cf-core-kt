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

package pcrypto.cf.exception

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty


/**
 * Model for all errors that occur during API execution and result in a 4xx or 5xx HTTP status code.
 */
@ApiModel(description = "Representation of an error that occurs during API execution and results in a 4xx or 5xx HTTP status code.")
class ApiError {

    @ApiModelProperty(value = "The value of the HTTP status code.", position = 10)
    var httpStatusCode: Int? = null

    @ApiModelProperty(value = "Description of the HTTP status code.", position = 11)
    var httpMessage: String? = null

    @ApiModelProperty(value = "Description of the error.", position = 20)
    var description: String? = null

    @ApiModelProperty(
        value = "An optional list of ErrorMessage objects which provide further details as to the cause of this error.",
        position = 30
    )
    var details: List<ErrorMessage>? = null

    @ApiModelProperty(
        value = "A reference identifier used by support to assist with certain error messages.",
        position = 40
    )
    var supportReferenceId: String? = null

    override fun toString(): String {
        return "ApiError{" +
                "httpStatusCode=" + httpStatusCode +
                ", httpMessage='" + httpMessage + '\''.toString() +
                ", description='" + description + '\''.toString() +
                ", details=" + details +
                ", supportReferenceId='" + supportReferenceId + '\''.toString() +
                '}'.toString()
    }


    /**
     * Convenience fluent Builder class for constructing ApiError objects.
     */
    class Builder private constructor() {
        private var httpStatusCode: Int? = null
        private var httpMessage: String? = null
        private var description: String? = null
        private var details: List<ErrorMessage>? = null
        private var supportReferenceId: String? = null

        fun withHttpStatusCode(httpStatusCode: Int?): Builder {
            this.httpStatusCode = httpStatusCode
            return this
        }

        fun withHttpMessage(httpMessage: String): Builder {
            this.httpMessage = httpMessage
            return this
        }

        fun withDescription(description: String?): Builder {
            this.description = description
            return this
        }

        fun withDetails(details: List<ErrorMessage>): Builder {
            this.details = details
            return this
        }

        fun withSupportReferenceId(supportReferenceId: String): Builder {
            this.supportReferenceId = supportReferenceId
            return this
        }

        fun build(): ApiError {
            val apiError = ApiError()
            apiError.httpStatusCode = httpStatusCode
            apiError.httpMessage = httpMessage
            apiError.description = description
            apiError.details = details
            apiError.supportReferenceId = supportReferenceId
            return apiError
        }

        companion object {
            fun apiError(): Builder {
                return Builder()
            }
        }
    }
}
