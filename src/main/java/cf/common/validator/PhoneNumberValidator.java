/*
 * Copyright (c) 2014-2017 project44. All rights reserved.
 * project44 PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
            addViolation( constraintValidatorContext, "must be a valid phoneNumber." );
        }

        return valid;
    }
}
