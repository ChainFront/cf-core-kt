package pcrypto.cf.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;


/**
 * Common base object for all tenant-specific entity objects requiring audit data.
 */
@Data
@EqualsAndHashCode( callSuper = true )
@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )
public abstract class AbstractAuditableDomain
      extends AbstractBaseDomain
{
    @CreatedBy
    @Column( updatable = false )
    private String createdBy;

    @CreatedDate
    @Column( updatable = false )
    private OffsetDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private OffsetDateTime lastModifiedDate;
}
