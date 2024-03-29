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

import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass


/**
 * Common base object for all tenant-specific entity objects (i.e. pretty much everything).
 *
 * **Important note: the tenant information in this object is not used for virtual tenant separation.
 * Separation is handled by using different schemas per-tenant.**
 */
@MappedSuperclass
abstract class AbstractBaseDomain {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    var tenantDomain: TenantDomain? = null

}
