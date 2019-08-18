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

@file:JvmName("PhoneNumberUtils")

package pcrypto.cf.common.util

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import pcrypto.cf.exception.BadRequestException


/**
 * Formats a phone number in E164 format.
 *
 * @param phone
 * @return
 */
fun String.formatPhoneNumber(): String {
    val formattedPhone: String
    try {
        val parsedPhoneNumber = PhoneNumberUtil.getInstance().parse(this, "US")
        formattedPhone =
            PhoneNumberUtil.getInstance().format(parsedPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
    } catch (e: NumberParseException) {
        // This should have been caught by the validator up front, but we still need to catch here as this is a checked exception
        throw BadRequestException("Invalid phone number '$this'.")
    }

    return formattedPhone
}

/**
 * Takes a generic phone number string, and parses it into a Phonenumber.PhoneNumber object, defaulting to US region
 * if country code is not specified.
 *
 * @param phone
 * @return
 */
fun String.parsePhoneNumber(): Phonenumber.PhoneNumber {
    val parsedPhoneNumber: Phonenumber.PhoneNumber
    try {
        parsedPhoneNumber = PhoneNumberUtil.getInstance().parse(this, "US")
    } catch (e: NumberParseException) {
        throw BadRequestException("Invalid phone number '$this'.")
    }

    return parsedPhoneNumber
}
