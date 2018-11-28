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

    private String schemaName;

    private boolean locked;

    private OffsetDateTime createdDate;

    private OffsetDateTime lastModifiedDate;

    private LocalDateTime deletedDate;
}
