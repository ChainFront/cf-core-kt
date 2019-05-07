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

package pcrypto.cf.common.validator;

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

        //        if ( StringUtils.isNotBlank( node ) )
        //        {
        //            constraintViolationBuilder.addNode( node );
        //        }

        constraintViolationBuilder.addConstraintViolation();
    }


    protected void addViolation( final ConstraintValidatorContext constraintValidatorContext,
                                 final String message )
    {
        this.addViolation( constraintValidatorContext, message, "" );
    }
}
