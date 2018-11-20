package pcrypto.cf.common.domain;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Entity
@Immutable
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

    private boolean locked;

    private OffsetDateTime createdDate;

    private OffsetDateTime lastModifiedDate;

    private LocalDateTime deletedDate;

    public Long getId()
    {
        return id;
    }

    public void setId( final Long id )
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode( final String code )
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName( final String name )
    {
        this.name = name;
    }

    public boolean isLocked()
    {
        return locked;
    }

    public void setLocked( final boolean locked )
    {
        this.locked = locked;
    }

    public OffsetDateTime getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate( final OffsetDateTime createdDate )
    {
        this.createdDate = createdDate;
    }

    public OffsetDateTime getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate( final OffsetDateTime lastModifiedDate )
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public LocalDateTime getDeletedDate()
    {
        return deletedDate;
    }

    public void setDeletedDate( final LocalDateTime deletedDate )
    {
        this.deletedDate = deletedDate;
    }
}
