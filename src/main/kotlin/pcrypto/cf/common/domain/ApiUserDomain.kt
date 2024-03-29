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
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Table(name = "api_user")
@Entity
class ApiUserDomain : AbstractAuditableDomain() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_user_id_gen")
    @SequenceGenerator(name = "api_user_id_gen", sequenceName = "api_user_seq", allocationSize = 1)
    var id: Long? = null

    @NotBlank(message = "User name is a required field")
    var userName: String? = null

    @NotBlank(message = "First name is a required field")
    var firstName: String? = null

    @NotBlank(message = "Last name is a required field")
    var lastName: String? = null

    var email: String? = null

    var phone: String? = null

    var locked: Boolean? = null

    var expired: Boolean? = null

    var deletedDate: LocalDateTime? = null

    @NotBlank(message = "Password required")
    var password: String? = null

}
