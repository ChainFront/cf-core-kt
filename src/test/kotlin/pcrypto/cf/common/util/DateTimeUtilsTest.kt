package pcrypto.cf.common.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Month
import java.util.*


internal class DateTimeUtilsTest {

    @Test
    fun toLocalDate() {
        val localDateTime = referenceDate.toLocalDate()
        assertEquals(2019, localDateTime.year.toLong())
        assertEquals(8, localDateTime.monthValue.toLong())
        assertEquals(Month.AUGUST, localDateTime.month)
        assertEquals(17, localDateTime.dayOfMonth.toLong())
    }

    @Test
    fun toLocalDateTime() {
        val localDateTime = referenceDate.toLocalDateTime()
        assertEquals(2019, localDateTime.year.toLong())
        assertEquals(8, localDateTime.monthValue.toLong())
        assertEquals(Month.AUGUST, localDateTime.month)
        assertEquals(17, localDateTime.dayOfMonth.toLong())
        assertEquals(22, localDateTime.hour.toLong())
        assertEquals(1, localDateTime.minute.toLong())
    }

    @Test
    fun toOffsetDateTime() {
        val localDateTime = referenceDate.toLocalDateTime()
        val offsetDateTime = localDateTime.toOffsetDateTime()
        assertEquals(2019, offsetDateTime.year.toLong())
        assertEquals(8, offsetDateTime.monthValue.toLong())
        assertEquals(Month.AUGUST, offsetDateTime.month)
        assertEquals(17, offsetDateTime.dayOfMonth.toLong())
        assertEquals(22, offsetDateTime.hour.toLong())
        assertEquals(1, offsetDateTime.minute.toLong())
    }


    private val referenceDate: Date
        get() {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = 1566097288000L
            return calendar.time
        }
}