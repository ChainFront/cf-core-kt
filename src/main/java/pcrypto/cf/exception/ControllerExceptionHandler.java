package pcrypto.cf.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.vault.VaultException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 * Controller advice used to map exceptions to consumable error responses.
 * This handler will log all exceptions, including the support reference id.
 */
@ControllerAdvice
public class ControllerExceptionHandler
      extends ResponseEntityExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger( ControllerExceptionHandler.class );


    private static String buildFieldErrorMessage( final FieldError fieldError )
    {
        final String errorText;
        // BindException is being thrown for request parameters with invalid enum constants.
        // The message is ugly, so cleaning it up manually here.
        if ( StringUtils.containsIgnoreCase( fieldError.getDefaultMessage(), "No enum constant" ) )
        {
            errorText = "Invalid enumerated value: " + fieldError.getRejectedValue().toString();
        }
        else
        {
            errorText = fieldError.getDefaultMessage();
        }
        return fieldError.getField() + ", " + errorText;
    }

    @ResponseStatus( value = HttpStatus.BAD_GATEWAY )
    @ExceptionHandler( ResourceAccessException.class )
    @ResponseBody
    protected ResponseEntity<ApiError> handleResourceAccessException( final Exception e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.BAD_GATEWAY.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.BAD_GATEWAY.value() )
              .withDescription( "Temporarily unable to connect to backend key service. Please try again in a few minutes." )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.BAD_GATEWAY );
    }

    @ResponseStatus( value = HttpStatus.NOT_FOUND )
    @ExceptionHandler( value = { NotFoundException.class } )
    @ResponseBody
    protected ResponseEntity<ApiError> handleNotFoundException( final NotFoundException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.NOT_FOUND.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.NOT_FOUND.value() )
              .withDescription( e.getMessage() )
              .withDetails( e.getDetails() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.NOT_FOUND );
    }

    @ResponseStatus( value = HttpStatus.UNAUTHORIZED )
    @ExceptionHandler( value = { UnauthorizedException.class } )
    @ResponseBody
    protected ResponseEntity<ApiError> handleUnauthorizedException( final UnauthorizedException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.UNAUTHORIZED.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.UNAUTHORIZED.value() )
              .withDescription( e.getMessage() )
              .withDetails( e.getDetails() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.UNAUTHORIZED );
    }

    @ResponseStatus( value = HttpStatus.UNAUTHORIZED )
    @ExceptionHandler( value = { BadCredentialsException.class } )
    @ResponseBody
    protected ResponseEntity<ApiError> handleBadCredentialsException( final BadCredentialsException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.UNAUTHORIZED.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.UNAUTHORIZED.value() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.UNAUTHORIZED );
    }

    @ResponseStatus( value = HttpStatus.FORBIDDEN )
    @ExceptionHandler( value = { ForbiddenException.class } )
    @ResponseBody
    protected ResponseEntity<ApiError> handleForbiddenException( final ForbiddenException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.FORBIDDEN.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.FORBIDDEN.value() )
              .withDescription( e.getMessage() )
              .withDetails( e.getDetails() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.FORBIDDEN );
    }

    @ResponseStatus( value = HttpStatus.BAD_REQUEST )
    @ExceptionHandler( value = { BadRequestException.class } )
    @ResponseBody
    protected ResponseEntity<ApiError> handleBadRequestException( final BadRequestException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.BAD_REQUEST.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.BAD_REQUEST.value() )
              .withDescription( e.getMessage() )
              .withDetails( e.getDetails() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.BAD_REQUEST );
    }

    @ResponseStatus( value = HttpStatus.CONFLICT )
    @ExceptionHandler( value = { ConflictException.class } )
    @ResponseBody
    protected ResponseEntity<ApiError> handleConflictException( final ConflictException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.CONFLICT.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.CONFLICT.value() )
              .withDescription( e.getMessage() )
              .withDetails( e.getDetails() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.CONFLICT );
    }

    @ResponseStatus( value = HttpStatus.UNPROCESSABLE_ENTITY )
    @ExceptionHandler( value = { InsufficentBalanceException.class } )
    @ResponseBody
    protected ResponseEntity<ApiError> handleInsufficentBalanceException( final InsufficentBalanceException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.UNPROCESSABLE_ENTITY.value() )
              .withDescription( e.getMessage() )
              .withDetails( e.getDetails() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.BAD_REQUEST );
    }

    @ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR )
    @ExceptionHandler( value = { BlockchainServiceException.class } )
    @ResponseBody
    protected ResponseEntity<ApiError> handleBlockchainServiceException( final BlockchainServiceException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.INTERNAL_SERVER_ERROR.value() )
              .withDescription( e.getMessage() )
              .withDetails( e.getDetails() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.INTERNAL_SERVER_ERROR );
    }

    //@ResponseStatus( value = HttpStatus.SERVICE_UNAVAILABLE )
    @ExceptionHandler( VaultException.class )
    @ResponseBody
    protected ResponseEntity<ApiError> handleVaultException( final VaultException e )
    {
        final HttpStatus httpStatus;
        final String description;

        // Vault isn't very nice with exceptions, so we parse out error codes here to try to translate to something
        // more user friendly
        final String vaultMessage = e.getMessage();
        if ( StringUtils.containsIgnoreCase( vaultMessage, "Status 400" ) )
        {
            httpStatus = HttpStatus.BAD_REQUEST;
            description = StringUtils.remove( vaultMessage, "Status 400" );
        }
        else
        {
            httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
            description = "An error occurred on the backend key service. Please try again in a few minutes. If the error continues, please contact support.";
        }

        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( httpStatus.getReasonPhrase() )
              .withHttpStatusCode( httpStatus.value() )
              .withDescription( description )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, httpStatus );
    }

    @Override
    protected ResponseEntity<Object> handleBindException( final BindException ex,
                                                          final HttpHeaders headers,
                                                          final HttpStatus status,
                                                          final WebRequest request )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpStatusCode( status.value() )
              .withHttpMessage( status.getReasonPhrase() )
              .withDetails( parseErrorsFromBindingResult( ex.getBindingResult() ) )
              .withSupportReferenceId( ( UUID.randomUUID().toString() ) )
              .build();

        logger.error( "HTTP message bind exception: {}", apiError.toString() );

        return new ResponseEntity<>( apiError, headers, status );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( final MethodArgumentNotValidException ex,
                                                                   final HttpHeaders headers,
                                                                   final HttpStatus status,
                                                                   final WebRequest request )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpStatusCode( status.value() )
              .withHttpMessage( status.getReasonPhrase() )
              .withDetails( parseErrorsFromBindingResult( ex.getBindingResult() ) )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( "HTTP message method argument not valid: {}", apiError.toString() );

        return new ResponseEntity<>( apiError, headers, status );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported( final HttpMediaTypeNotSupportedException ex,
                                                                      final HttpHeaders headers,
                                                                      final HttpStatus status,
                                                                      final WebRequest request )
    {
        final String unsupported = "Unsupported content type: " + ex.getContentType();
        final String supported = "Supported content types: " + MediaType.toString( ex.getSupportedMediaTypes() );

        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpStatusCode( status.value() )
              .withHttpMessage( status.getReasonPhrase() )
              .withDescription( unsupported + " -- " + supported )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.info( "HTTP message invalid content type: {}", apiError.toString() );

        return new ResponseEntity<>( apiError, headers, status );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable( final HttpMessageNotReadableException ex,
                                                                   final HttpHeaders headers,
                                                                   final HttpStatus status,
                                                                   final WebRequest request )
    {
        final String errorMessage;
        final Throwable cause = ex.getMostSpecificCause();
        if ( null != cause )
        {
            errorMessage = cause.getMessage();
        }
        else
        {
            errorMessage = ex.getMessage();
        }

        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpStatusCode( status.value() )
              .withHttpMessage( status.getReasonPhrase() )
              .withDescription( errorMessage )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.info( "HTTP message not readable: {}", apiError.toString() );

        return new ResponseEntity<>( apiError, headers, status );
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
    @Override
    protected ResponseEntity<Object> handleExceptionInternal( final Exception ex,
                                                              final Object body,
                                                              final HttpHeaders headers,
                                                              final HttpStatus status,
                                                              final WebRequest request )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpStatusCode( status.value() )
              .withHttpMessage( status.getReasonPhrase() )
              .withDescription( ex.getMessage() )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), ex );

        return super.handleExceptionInternal( ex, apiError, headers, status, request );
    }

    @ResponseStatus( value = HttpStatus.NOT_IMPLEMENTED )
    @ExceptionHandler( UnsupportedOperationException.class )
    @ResponseBody
    protected ResponseEntity<ApiError> handleUnsupportedOperationException( final UnsupportedOperationException e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.NOT_IMPLEMENTED.value() )
              .withDescription( "This feature has not yet been implemented, or is not enabled for your account." )
              .withDetails( Collections.singletonList( new ErrorMessage( e.getMessage(), null ) ) )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.NOT_IMPLEMENTED );
    }

    @ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR )
    @ExceptionHandler( Exception.class )
    @ResponseBody
    protected ResponseEntity<ApiError> handleInternalError( final Exception e )
    {
        final ApiError apiError = ApiError.Builder
              .apiError()
              .withHttpMessage( HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() )
              .withHttpStatusCode( HttpStatus.INTERNAL_SERVER_ERROR.value() )
              .withDescription( "An unexpected error has occurred. If the error continues, please contact support." )
              .withSupportReferenceId( UUID.randomUUID().toString() )
              .build();

        logger.error( apiError.toString(), e );

        return new ResponseEntity<>( apiError, HttpStatus.INTERNAL_SERVER_ERROR );
    }

    private List<ErrorMessage> parseErrorsFromBindingResult( final BindingResult bindingResult )
    {
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        final List<ObjectError> globalErrors = bindingResult.getGlobalErrors();

        final List<ErrorMessage> errors = new ArrayList<>( fieldErrors.size() + globalErrors.size() );

        for ( final FieldError fieldError : fieldErrors )
        {
            errors.add( new ErrorMessage( buildFieldErrorMessage( fieldError ), fieldError.getField() ) );
        }

        for ( final ObjectError objectError : globalErrors )
        {
            final String errorMessage = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add( new ErrorMessage( errorMessage, null ) );
        }

        return errors;
    }
}
