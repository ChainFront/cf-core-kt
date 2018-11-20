package pcrypto.cf.common.domain;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


/**
 * Common base object for all tenant-specific entity objects (i.e. pretty much everything).
 *
 * <b>Important note: the tenant information in this object is not used for virtual tenant separation.
 * Separation is handled by using different schemas per-tenant.</b>
 */
@Data
@MappedSuperclass
public abstract class AbstractBaseDomain
{

    @ManyToOne
    @JoinColumn( name = "tenant_id" )
    private TenantDomain tenantDomain;
}
