package pcrypto.cf.common.validator.annotation;

import pcrypto.cf.common.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target( { ElementType.METHOD,
           ElementType.FIELD } )
@Retention( RetentionPolicy.RUNTIME )
@Constraint( validatedBy = { PhoneNumberValidator.class } )
@Documented
public @interface ValidPhoneNumber
{
    String message() default "Invalid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
