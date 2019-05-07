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

package pcrypto.cf.common.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Data
@Entity
@Table( name = "tenant",
        schema = "master" )
public class TenantDomain
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
                     generator = "tenant_id_gen" )
    @SequenceGenerator( name = "tenant_id_gen",
                        sequenceName = "tenant_seq",
                        allocationSize = 1 )
    private Long id;

    private String code;

    private String name;

    private String email;

    private String phone;

    private String schemaName;

    private boolean locked;

    private OffsetDateTime createdDate;

    private OffsetDateTime lastModifiedDate;

    private LocalDateTime deletedDate;
}
