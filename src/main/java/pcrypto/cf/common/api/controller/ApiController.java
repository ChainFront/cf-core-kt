package pcrypto.cf.common.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Custom annotation to fix the base path for all API controllers. Use this instead of @RestController.
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
@RestController
@RequestMapping( "/api/v1" )
public @interface ApiController
{
}
