import java.io.*;
import java.io.ObjectOutputStream;

/**
 * Classe Contribuinte
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class Contribuinte implements Serializable
{
    private String nif;
    private String email;
    private String nome;
    private String morada;
    private String password;

    /**
     * Construtor por omissão de Contribuinte.
     */
    public Contribuinte()
    {
        this.nif = "";
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.password = "";
    }
    
    /**
     * Construtor parametrizado de Contribuinte.
     */
    public Contribuinte(String cNif, String cEmail, String cNome, String cMorada, String cPassword)
    {
        this.nif = cNif;
        this.email = cEmail;
        this.nome = cNome;
        this.morada = cMorada;
        this.password = cPassword;
    }

    /**
     * Construtor de cópia de Contribuinte.
     */
    public Contribuinte(Contribuinte umContribuinte)
    {
        this.nif = umContribuinte.getNif();
        this.email = umContribuinte.getEmail();
        this.nome = umContribuinte.getNome();
        this.morada = umContribuinte.getMorada();
        this.password = umContribuinte.getPassword();
    }
    
    /**
     * Devolve o valor do NIF do Contribuinte.
     */
    public String getNif()
    {
        return this.nif;
    }
    
    /**
     * Atualiza o valor do NIF do Contribuinte.
     */
    public void setNif(String novoNif)
    {
        this.nif = novoNif;
    }
    
     /**
     * Devolve o valor do Email do Contribuinte.
     */
    public String getEmail()
    {
        return this.email;
    }
    
    /**
     * Atualiza o valor do Email do Contribuinte.
     */
    public void setEmail(String novoEmail)
    {
        this.email = novoEmail;
    }   
    
    /**
     * Devolve o valor do Nome do Contribuinte.
     */
    public String getNome()
    {
        return this.nome;
    }
    
    /**
     * Atualiza o valor do Nome do Contribuinte.
     */
    public void setNome(String novoNome)
    {
        this.nome = novoNome;
    }    
    
    /**
     * Devolve o valor da Morada do Contribuinte.
     */
    public String getMorada()
    {
        return this.morada;
    }
    
    /**
     * Atualiza o valor da Morada do Contribuinte.
     */
    public void setMorada(String novaMorada)
    {
        this.morada = novaMorada;
    }     
    
    /**
     * Devolve o valor da Password do Contribuinte.
     */
    public String getPassword()
    {
        return this.password;
    }
    
    /**
     * Atualiza o valor da Password do Contribuinte.
     */
    public void setPassword(String novaPassword)
    {
        this.password = novaPassword;
    }  
    
    /**
     * Implementação do método de clonagem de um Contribuinte.
     */
    public Contribuinte clone()
    {
        return new Contribuinte(this);
    }
    
   
    /**
     * Implementação do método de igualdade entre dois Contribuintes.
     */
    public boolean equals(Object o)
    {
        if (this == o) 
            return true;
        if (o == null || this.getClass() != o.getClass()) 
            return false;
            
        Contribuinte contribuinte = (Contribuinte) o;
        return contribuinte.getNif()==this.nif && contribuinte.getEmail()==this.email && contribuinte.getNome()==this.nome 
        && contribuinte.getMorada()==this.morada && contribuinte.getPassword()==this.password;
    }
   
    /**
     * Implementação do método toString
    */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("--------------------------------\n");
        sb.append("NIF: ");
        sb.append(this.nif+"\n");
        sb.append("E-Mail: ");
        sb.append(this.email+"\n");
        sb.append("Nome: ");
        sb.append(this.nome+"\n");
        sb.append("Morada: ");
        sb.append(this.morada+"\n");
        sb.append("Password: ");
        sb.append(this.password+"\n");
        
        return sb.toString();
    }
}
