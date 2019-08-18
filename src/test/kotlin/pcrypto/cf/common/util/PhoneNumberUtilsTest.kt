package pcrypto.cf.common.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class PhoneNumberUtilsTest {

    @Test
    fun formatPhoneNumber() {
        val phoneNumber = "(555)555-5555"
        val formattedPhoneNumber = phoneNumber.formatPhoneNumber()
        assertEquals("+15555555555", formattedPhoneNumber)
    }

    @Test
    fun parsePhoneNumber() {
        val phoneNumber = "(555) 555-5555"
        val parsedPhoneNumber = phoneNumber.parsePhoneNumber()
        assertEquals(1, parsedPhoneNumber.countryCode)
        assertEquals(5555555555, parsedPhoneNumber.nationalNumber)
    }
}