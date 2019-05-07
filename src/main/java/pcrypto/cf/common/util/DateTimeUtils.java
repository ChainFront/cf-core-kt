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


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


/**
 * Utility class for common translator utility methods
 */
public class DateTimeUtils
{

    private static final Logger logger = LoggerFactory.getLogger( DateTimeUtils.class );


    /**
     * Converts a Date to a LocalDateTime with the system default timezone
     */
    public static LocalDateTime toLocalDateTime( final Date date )
    {
        LocalDateTime localDateTime = null;
        if ( null != date )
        {
            localDateTime = LocalDateTime.ofInstant( date.toInstant(), ZoneId.systemDefault() );
        }
        return localDateTime;
    }


    public static LocalDate toLocalDate( final Date date )
    {
        final LocalDateTime localDateTime = toLocalDateTime( date );

        final LocalDate localDate;

        if ( localDateTime != null )
        {
            localDate = localDateTime.toLocalDate();
        }
        else
        {
            localDate = null;
        }

        return localDate;
    }


    /**
     * Converts a LocalDateTime to a java.util.Date with the system default timezone
     */
    public static Date toDate( final LocalDateTime localDateTime )
    {
        Date date = null;
        if ( null != localDateTime )
        {
            date = Date.from( localDateTime.atZone( ZoneId.systemDefault() ).toInstant() );
        }
        return date;
    }


    /**
     * Converts a LocalTime to a java.util.Date with the system default timezone
     */
    public static Date toDate( final LocalTime localTime,
                               final LocalDate localDate )
    {
        Date date = null;
        if ( null != localTime )
        {
            date = Date.from( localTime.atDate( localDate ).atZone( ZoneId.systemDefault() ).toInstant() );
        }
        return date;
    }


    /**
     * Converts a LocalDate to a java.util.Date with the system default timezone
     */
    public static Date toDate( final LocalDate localDate )
    {
        return toDate( localDate, ZoneId.systemDefault() );
    }


    /**
     * Converts a LocalDate to a java.util.Date with the supplied zone id.
     */
    public static Date toDate( final LocalDate localDate,
                               final ZoneId zoneId )
    {
        Date date = null;
        if ( null != localDate )
        {
            date = Date.from( localDate.atStartOfDay( zoneId ).toInstant() );
        }
        return date;
    }



    /**
     * Converts a LocalTime object to a military time string
     */
    public static String toTimeString( final LocalTime localTime )
    {
        String timeString = null;

        if ( localTime != null )
        {
            timeString = localTime.format( DateTimeFormatter.ofPattern( "HH:mm" ) );
        }

        return timeString;
    }



    /**
     * Converts a military time string to a LocalTime object.
     */
    public static LocalTime toLocalTime( final String timeString )
    {
        LocalTime time = null;

        if ( StringUtils.isNotBlank( timeString ) )
        {
            try
            {
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "HH:mm" );
                time = LocalTime.parse( timeString, formatter );
            }
            catch ( final DateTimeParseException | IllegalArgumentException e )
            {
                logger.error( "Failed to parse time string " + timeString, e );
            }
        }

        return time;
    }



    /**
     * Converts a date string and time string using a date format string and time format string that can be accepted
     * by java.time.format.DateTimeFormatter. The time string may be blank, in which case the time will default to
     * 00:00 (midnight).
     * <p>
     * Returns null if either of the two strings are not formatted properly.
     */
    public static LocalDateTime toLocalDateTime( final String dateString,
                                                 final String dateFormat,
                                                 final String timeString,
                                                 final String timeFormat )
    {
        if ( StringUtils.isNotBlank( dateString ) )
        {
            String dateTimeString = dateString;
            String dateTimeFormat = dateFormat;

            if ( StringUtils.isNotBlank( timeString ) )
            {
                dateTimeString += " " + timeString;
                dateTimeFormat += " " + timeFormat;
            }

            return toLocalDateTime( dateTimeString, dateTimeFormat );
        }
        else
        {
            return null;
        }
    }


    /**
     * Converts a date time string using a date time format string that can be accepted by java.time.format.DateTimeFormatter.
     * The date time string and format can be limited to just date information and not have time information.
     * <p>
     * Returns null if either of the two strings are not formatted properly.
     */
    public static LocalDateTime toLocalDateTime( final String dateTimeString,
                                                 final String dateTimeFormat )
    {

        LocalDateTime dateTime = null;

        if ( StringUtils.isNotBlank( dateTimeString ) && StringUtils.isNotBlank( dateTimeFormat ) )
        {
            DateTimeFormatter formatter = null;

            try
            {
                formatter = DateTimeFormatter.ofPattern( dateTimeFormat );
            }
            catch ( final IllegalArgumentException e )
            {
                logger.error( "Failed to parse date time format string: " + dateTimeFormat, e );
            }

            if ( formatter != null )
            {
                try
                {
                    // First try to parse the date time string as a LocalDateTime. Both date and time must be included.
                    dateTime = LocalDateTime.parse( dateTimeString, formatter );
                }
                catch ( final DateTimeParseException e )
                {
                    // Date and time not both included, or malformatted date time string. Try again before logging.
                }


                if ( dateTime == null )
                {
                    try
                    {
                        // If parsing as a LocalDateTime failed, try parsing as just a LocalDate.
                        final LocalDate date = LocalDate.parse( dateTimeString, formatter );
                        dateTime = LocalDateTime.of( date, LocalTime.MIDNIGHT );
                    }
                    catch ( final DateTimeParseException e )
                    {
                        logger.error( "Failed to parse date time string " + dateTimeString + " using format string " + dateTimeFormat, e );
                    }
                }
            }
        }

        return dateTime;
    }
}

