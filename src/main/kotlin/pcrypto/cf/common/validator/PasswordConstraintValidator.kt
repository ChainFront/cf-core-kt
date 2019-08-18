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

package pcrypto.cf.common.validator

import org.passay.*
import pcrypto.cf.common.validator.annotation.ValidPassword
import java.util.*
import javax.validation.ConstraintValidatorContext


class PasswordConstraintValidator : AbstractValidator<ValidPassword, String>() {

    override fun isValid(
        password: String,
        constraintValidatorContext: ConstraintValidatorContext
    ): Boolean {
        var valid = true

        val validator = PasswordValidator(
            Arrays.asList(
                LengthRule(8, 30),
                UppercaseCharacterRule(1),
                DigitCharacterRule(1),
                SpecialCharacterRule(1),
                NumericalSequenceRule(3, false),
                AlphabeticalSequenceRule(3, false),
                QwertySequenceRule(3, false),
                WhitespaceRule()
            )
        )
        val result = validator.validate(PasswordData(password))

        if (!result.isValid) {
            val messages = validator.getMessages(result)
            for (message in messages) {
                addViolation(constraintValidatorContext, message)
            }
            valid = false
        }

        return valid

    }
}
