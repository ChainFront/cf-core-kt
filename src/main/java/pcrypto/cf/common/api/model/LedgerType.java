package pcrypto.cf.common.api.model;

public enum LedgerType
{
    STELLAR( "Stellar" ),
    ETHEREUM( "Ethereum" );

    private String description;

    LedgerType( final String description )
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
}
