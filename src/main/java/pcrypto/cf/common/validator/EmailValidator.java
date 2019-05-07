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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pcrypto.cf.common.validator.annotation.ValidEmail;

import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


/**
 * Validates that supplied string is a valid email address.
 */
public class EmailValidator
      extends AbstractValidator<ValidEmail, String>
{

    private static final Logger logger = LoggerFactory.getLogger( EmailValidator.class );

    private static final String EMAIL_PATTERN =
          "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
          + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;


    @Override
    public void initialize( final ValidEmail email )
    {
        pattern = Pattern.compile( EMAIL_PATTERN );
    }


    @Override
    public boolean isValid( final String email,
                            final ConstraintValidatorContext constraintValidatorContext )
    {

        boolean valid = true;
        if ( StringUtils.isNotBlank( email ) && !pattern.matcher( email ).matches() )
        {
            addViolation( constraintValidatorContext, "Invalid email address." );
            valid = false;
        }


        return valid;
    }
}
