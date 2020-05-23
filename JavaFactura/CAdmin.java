/**
 * Classe Administrador (Sub-Classe da Classe Contribuinte).
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class CAdmin extends Contribuinte
{
    /**
     * COnstrutor para objetos da classe CAdmin
     */
    public CAdmin()
    {
        super("","","","","");
    }
    
    /**
     * Construtor parametrizado de Contribuinte Administrador.
     */
    public CAdmin(String cNif, String cEmail, String cNome, String cMorada, String cPassword)
    {
        super(cNif, cEmail, cNome, cMorada, cPassword);
    }

    /**
     * Construtor de cópia de Contribuinte Administrador.
     */
    public CAdmin(CAdmin umAdministrador)
    {
        super(umAdministrador.getNif(), umAdministrador.getEmail(), umAdministrador.getNome(), umAdministrador.getMorada(),umAdministrador.getPassword());
    }
    
    /**
     * Implementação do método de clonagem de um CAdmin.
     * 
     * @return objeto do tipo CAdmin.
     */
    public CAdmin clone()
    {
        return new CAdmin(this);
    }
}
