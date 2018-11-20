package pcrypto.cf.common.api.model;



public class LedgerAddress
{
    private LedgerType type;
    private String address;

    public LedgerAddress( final LedgerType type,
                          final String address )
    {
        this.type = type;
        this.address = address;
    }

    public LedgerType getType()
    {
        return type;
    }

    public String getAddress()
    {
        return address;
    }
}
