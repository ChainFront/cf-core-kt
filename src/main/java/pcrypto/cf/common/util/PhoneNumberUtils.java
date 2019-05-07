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

package pcrypto.cf.common.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import pcrypto.cf.exception.BadRequestException;


public class PhoneNumberUtils
{

    /**
     * Formats a phone number in E164 format.
     *
     * @param phone
     * @return
     */
    public static String formatPhoneNumber( final String phone )
    {
        final String formattedPhone;
        try
        {
            final Phonenumber.PhoneNumber parsedPhoneNumber = PhoneNumberUtil.getInstance().parse( phone, "US" );
            formattedPhone = PhoneNumberUtil.getInstance().format( parsedPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164 );
        }
        catch ( final NumberParseException e )
        {
            // This should have been caught by the validator up front, but we still need to catch here as this is a checked exception
            throw new BadRequestException( "Invalid phone number '" + phone + "'." );
        }
        return formattedPhone;
    }

    /**
     * Takes a generic phone number string, and parses it into a Phonenumber.PhoneNumber object, defaulting to US region
     * if country code is not specified.
     *
     * @param phone
     * @return
     */
    public static Phonenumber.PhoneNumber parsePhoneNumber( final String phone )
    {
        final Phonenumber.PhoneNumber parsedPhoneNumber;
        try
        {
            parsedPhoneNumber = PhoneNumberUtil.getInstance().parse( phone, "US" );
        }
        catch ( final NumberParseException e )
        {
            throw new BadRequestException( "Invalid phone number '" + phone + "'." );
        }
        return parsedPhoneNumber;
    }
}
