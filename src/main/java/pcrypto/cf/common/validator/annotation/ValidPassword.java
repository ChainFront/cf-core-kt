package pcrypto.cf.common.validator.annotation;

import pcrypto.cf.common.validator.PasswordConstraintValidator;

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
@Constraint( validatedBy = { PasswordConstraintValidator.class } )
@Documented
public @interface ValidPassword
{
    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}