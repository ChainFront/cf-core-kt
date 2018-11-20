package pcrypto.cf.common.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;


/**
 * Abstract validator holding common functionality
 */
public abstract class AbstractValidator<ANNOTATION extends Annotation, TYPE>
      implements ConstraintValidator<ANNOTATION, TYPE>
{
    protected void addViolation( final ConstraintValidatorContext constraintValidatorContext,
                                 final String message,
                                 final String node )
    {
        constraintValidatorContext.disableDefaultConstraintViolation();

        final ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder =
              constraintValidatorContext.buildConstraintViolationWithTemplate( message );

        if ( StringUtils.isNotBlank( node ) )
        {
            constraintViolationBuilder.addNode( node );
        }

        constraintViolationBuilder.addConstraintViolation();
    }


    protected void addViolation( final ConstraintValidatorContext constraintValidatorContext,
                                 final String message )
    {
        this.addViolation( constraintValidatorContext, message, "" );
    }
}
