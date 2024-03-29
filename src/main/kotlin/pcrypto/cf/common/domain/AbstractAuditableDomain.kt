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

package pcrypto.cf.common.domain

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


/**
 * Common base object for all tenant-specific entity objects requiring audit data.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractAuditableDomain : AbstractBaseDomain() {

    @CreatedBy
    @Column(updatable = false)
    var createdBy: String? = null

    @CreatedDate
    @Column(updatable = false)
    var createdDate: OffsetDateTime? = null

    @LastModifiedBy
    var lastModifiedBy: String? = null

    @LastModifiedDate
    var lastModifiedDate: OffsetDateTime? = null

}
