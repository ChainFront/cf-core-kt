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

package pcrypto.cf.common.util


import java.time.*
import java.util.*


// This file contains utilities for common datetime conversion methods


fun Date.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate = toLocalDateTime(zoneId).toLocalDate()

fun Date.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    val instant = Instant.ofEpochMilli(time)
    return LocalDateTime.ofInstant(instant, zoneId)
}

fun LocalDateTime.toOffsetDateTime(zoneId: ZoneId = ZoneId.systemDefault()): OffsetDateTime =
    atZone(zoneId).toOffsetDateTime()

