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

import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.lang.Nullable
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.vault.VaultException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


/**
 * Controller advice used to map exceptions to consumable error responses.
 * This handler will log all exceptions, including the support reference id.
 */
@ControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {

    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(ResourceAccessException::class)
    @ResponseBody
    protected fun handleResourceAccessException(e: Exception): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.BAD_GATEWAY.reasonPhrase)
            .withHttpStatusCode(HttpStatus.BAD_GATEWAY.value())
            .withDescription("Temporarily unable to connect to backend key service. Please try again in a few minutes.")
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.BAD_GATEWAY)
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    @ResponseBody
    protected fun handleNotFoundException(e: NotFoundException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.NOT_FOUND.reasonPhrase)
            .withHttpStatusCode(HttpStatus.NOT_FOUND.value())
            .withDescription(e.message)
            .withDetails(e.details)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [UnauthorizedException::class])
    @ResponseBody
    protected fun handleUnauthorizedException(e: UnauthorizedException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.UNAUTHORIZED.reasonPhrase)
            .withHttpStatusCode(HttpStatus.UNAUTHORIZED.value())
            .withDescription(e.message)
            .withDetails(e.details)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.UNAUTHORIZED)
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [BadCredentialsException::class])
    @ResponseBody
    protected fun handleBadCredentialsException(e: BadCredentialsException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.UNAUTHORIZED.reasonPhrase)
            .withHttpStatusCode(HttpStatus.UNAUTHORIZED.value())
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.UNAUTHORIZED)
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = [ForbiddenException::class])
    @ResponseBody
    protected fun handleForbiddenException(e: ForbiddenException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.FORBIDDEN.reasonPhrase)
            .withHttpStatusCode(HttpStatus.FORBIDDEN.value())
            .withDescription(e.message)
            .withDetails(e.details)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.FORBIDDEN)
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [BadRequestException::class])
    @ResponseBody
    protected fun handleBadRequestException(e: BadRequestException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.BAD_REQUEST.reasonPhrase)
            .withHttpStatusCode(HttpStatus.BAD_REQUEST.value())
            .withDescription(e.message)
            .withDetails(e.details)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = [ConflictException::class])
    @ResponseBody
    protected fun handleConflictException(e: ConflictException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.CONFLICT.reasonPhrase)
            .withHttpStatusCode(HttpStatus.CONFLICT.value())
            .withDescription(e.message)
            .withDetails(e.details)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.CONFLICT)
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = [InsufficentBalanceException::class])
    @ResponseBody
    protected fun handleInsufficentBalanceException(e: InsufficentBalanceException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.UNPROCESSABLE_ENTITY.reasonPhrase)
            .withHttpStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .withDescription(e.message)
            .withDetails(e.details)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [BlockchainServiceException::class])
    @ResponseBody
    protected fun handleBlockchainServiceException(e: BlockchainServiceException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
            .withHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .withDescription(e.message)
            .withDetails(e.details)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    //@ResponseStatus( value = HttpStatus.SERVICE_UNAVAILABLE )
    @ExceptionHandler(VaultException::class)
    @ResponseBody
    protected fun handleVaultException(e: VaultException): ResponseEntity<ApiError> {
        val httpStatus: HttpStatus
        val description: String

        // Vault isn't very nice with exceptions, so we parse out error codes here to try to translate to something
        // more user friendly
        val vaultMessage = e.message
        if (StringUtils.containsIgnoreCase(vaultMessage, "Status 400")) {
            httpStatus = HttpStatus.BAD_REQUEST
            description = StringUtils.remove(vaultMessage, "Status 400")
        } else {
            httpStatus = HttpStatus.SERVICE_UNAVAILABLE
            description =
                "An error occurred on the backend key service. Please try again in a few minutes. If the error continues, please contact support."
        }

        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(httpStatus.reasonPhrase)
            .withHttpStatusCode(httpStatus.value())
            .withDescription(description)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, httpStatus)
    }

    override fun handleBindException(
        ex: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpStatusCode(status.value())
            .withHttpMessage(status.reasonPhrase)
            .withDetails(parseErrorsFromBindingResult(ex.bindingResult))
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error("HTTP message bind exception: $apiError")

        return ResponseEntity(apiError, headers, status)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpStatusCode(status.value())
            .withHttpMessage(status.reasonPhrase)
            .withDetails(parseErrorsFromBindingResult(ex.bindingResult))
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error("HTTP message method argument not valid: $apiError")

        return ResponseEntity(apiError, headers, status)
    }

    override fun handleHttpMediaTypeNotSupported(
        ex: HttpMediaTypeNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val unsupported = "Unsupported content type: " + ex.contentType!!
        val supported = "Supported content types: " + MediaType.toString(ex.supportedMediaTypes)

        val apiError = ApiError.Builder
            .apiError()
            .withHttpStatusCode(status.value())
            .withHttpMessage(status.reasonPhrase)
            .withDescription("$unsupported -- $supported")
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.info("HTTP message invalid content type: $apiError")

        return ResponseEntity(apiError, headers, status)
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorMessage: String?
        val cause = ex.mostSpecificCause
        errorMessage = cause.message

        val apiError = ApiError.Builder
            .apiError()
            .withHttpStatusCode(status.value())
            .withHttpMessage(status.reasonPhrase)
            .withDescription(errorMessage!!)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.info("HTTP message not readable: $apiError")

        return ResponseEntity(apiError, headers, status)
    }

    /**
     * Override handling of all internal Spring exceptions to return JSON.
     *
     * @param ex      the original exception
     * @param body    pretty much always null from Spring
     * @param headers the original headers
     * @param status  the http status code
     * @param request the original request
     * @return
     */
    override fun handleExceptionInternal(
        ex: java.lang.Exception,
        @Nullable body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpStatusCode(status.value())
            .withHttpMessage(status.reasonPhrase)
            .withDescription(ex.message)
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), ex)

        return super.handleExceptionInternal(ex, apiError, headers, status, request)
    }

    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(UnsupportedOperationException::class)
    @ResponseBody
    protected fun handleUnsupportedOperationException(e: UnsupportedOperationException): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.NOT_IMPLEMENTED.reasonPhrase)
            .withHttpStatusCode(HttpStatus.NOT_IMPLEMENTED.value())
            .withDescription("This feature has not yet been implemented, or is not enabled for your account.")
            .withDetails(listOf(ErrorMessage(e.message ?: "", "")))
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.NOT_IMPLEMENTED)
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    @ResponseBody
    protected fun handleInternalError(e: Exception): ResponseEntity<ApiError> {
        val apiError = ApiError.Builder
            .apiError()
            .withHttpMessage(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
            .withHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .withDescription("An unexpected error has occurred. If the error continues, please contact support.")
            .withSupportReferenceId(UUID.randomUUID().toString())
            .build()

        logger.error(apiError.toString(), e)

        return ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private fun parseErrorsFromBindingResult(bindingResult: BindingResult): List<ErrorMessage> {
        val fieldErrors = bindingResult.fieldErrors
        val globalErrors = bindingResult.globalErrors

        val errors = ArrayList<ErrorMessage>(fieldErrors.size + globalErrors.size)

        for (fieldError in fieldErrors) {
            errors.add(ErrorMessage(buildFieldErrorMessage(fieldError), fieldError.field))
        }

        for (objectError in globalErrors) {
            val errorMessage = objectError.objectName + ", " + objectError.defaultMessage
            errors.add(ErrorMessage(errorMessage, ""))
        }

        return errors
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ControllerExceptionHandler::class.java)

        private fun buildFieldErrorMessage(fieldError: FieldError): String {
            val errorText: String? =
                if (StringUtils.containsIgnoreCase(fieldError.defaultMessage, "No enum constant")) {
                    "Invalid enumerated value: " + fieldError.rejectedValue!!.toString()
                } else {
                    fieldError.defaultMessage
                }
            // BindException is being thrown for request parameters with invalid enum constants.
            // The message is ugly, so cleaning it up manually here.
            return fieldError.field + ", " + errorText
        }
    }

}
