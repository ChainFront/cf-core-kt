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

import java.time.LocalDateTime
import java.time.OffsetDateTime
import javax.persistence.*


@Entity
@Table(name = "tenant", schema = "master")
class TenantDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tenant_id_gen")
    @SequenceGenerator(name = "tenant_id_gen", sequenceName = "tenant_seq", allocationSize = 1)
    private val id: Long? = null

    private val code: String? = null

    private val name: String? = null

    private val email: String? = null

    private val phone: String? = null

    private val schemaName: String? = null

    private val locked: Boolean = false

    private val createdDate: OffsetDateTime? = null

    private val lastModifiedDate: OffsetDateTime? = null

    private val deletedDate: LocalDateTime? = null

}
