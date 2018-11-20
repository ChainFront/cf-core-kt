package pcrypto.cf.common.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pcrypto.cf.exception.ApiError;
import springfox.documentation.annotations.ApiIgnore;


/**
 * Handler for returning custom XML, JSON, or HTML responses to 404 (Not Found) situations. This needs to be a separate
 * controller (rather than part of ControllerExceptionHandler) as the 404 will be generated prior to
 * spring mvc's exception handling.
 */
@ApiIgnore
@Controller
@RequestMapping( value = "/resourceNotFound",
                 produces = { MediaType.APPLICATION_JSON_VALUE,
                              MediaType.TEXT_HTML_VALUE } )
public class ResourceNotFoundController
{
    @RequestMapping( produces = { MediaType.APPLICATION_JSON_VALUE } )
    @ResponseBody
    public ResponseEntity<ApiError> handleResourceNotFound()
    {
        final ApiError apiError = new ApiError();
        apiError.setHttpStatusCode( HttpStatus.NOT_FOUND.value() );
        apiError.setHttpMessage( HttpStatus.NOT_FOUND.getReasonPhrase() );
        return new ResponseEntity<>( apiError, new HttpHeaders(), HttpStatus.NOT_FOUND );
    }


    @RequestMapping( produces = { MediaType.TEXT_HTML_VALUE } )
    @ResponseStatus( HttpStatus.NOT_FOUND )
    public String handleResourceNotFoundHtml()
    {
        return "error/404";
    }
}