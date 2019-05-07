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

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pcrypto.cf.common.validator.annotation.ValidPhoneNumber;

import javax.validation.ConstraintValidatorContext;


/**
 * Validates that supplied string is a valid phone number.
 */
public class PhoneNumberValidator
      extends AbstractValidator<ValidPhoneNumber, String>
{

    private static final Logger logger = LoggerFactory.getLogger( PhoneNumberValidator.class );


    @Override
    public boolean isValid( final String phoneNumber,
                            final ConstraintValidatorContext constraintValidatorContext )
    {
        final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        boolean valid = true;

        final Phonenumber.PhoneNumber parsed;
        try
        {
            parsed = phoneNumberUtil.parse( phoneNumber, "US" );
            valid = phoneNumberUtil.isValidNumber( parsed );
        }
        catch ( final NumberParseException e )
        {
            valid = false;
        }

        if ( !valid )
        {
            addViolation( constraintValidatorContext, "Invalid phone number." );
        }

        return valid;
    }
}
