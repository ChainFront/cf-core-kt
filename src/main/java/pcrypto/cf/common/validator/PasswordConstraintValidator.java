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

import org.passay.AlphabeticalSequenceRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.NumericalSequenceRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.QwertySequenceRule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;
import pcrypto.cf.common.validator.annotation.ValidPassword;

import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


public class PasswordConstraintValidator
      extends AbstractValidator<ValidPassword, String>
{
    @Override
    public boolean isValid( final String password,
                            final ConstraintValidatorContext constraintValidatorContext )
    {
        boolean valid = true;

        final PasswordValidator validator = new PasswordValidator( Arrays.asList(
              new LengthRule( 8, 30 ),
              new UppercaseCharacterRule( 1 ),
              new DigitCharacterRule( 1 ),
              new SpecialCharacterRule( 1 ),
              new NumericalSequenceRule( 3, false ),
              new AlphabeticalSequenceRule( 3, false ),
              new QwertySequenceRule( 3, false ),
              new WhitespaceRule() ) );
        final RuleResult result = validator.validate( new PasswordData( password ) );

        if ( !result.isValid() )
        {
            final List<String> messages = validator.getMessages( result );
            for ( final String message : messages )
            {
                addViolation( constraintValidatorContext, message );
            }
            valid = false;
        }

        return valid;
    }
}
