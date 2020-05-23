import java.io.*;
import java.io.ObjectOutputStream;
/**
 * classe Atividade Economica
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class AtivEconomica implements Serializable
{
    Integer codigo;
    private String natureza;
    private double coefDeducao;
    /**
     * Construtor por omissão de Atividade Economica.
     */
    public AtivEconomica()
    {
        this.codigo = 0;
        this.natureza = "";
        this.coefDeducao = 0.0;
    }
    
    /**
     * Construtor parametrizado de Atividade Economica.
     */
    public AtivEconomica(int codigo, String natureza, double coefDeducao)
    {
        this.codigo = codigo;
        this.natureza = natureza;
        this.coefDeducao = coefDeducao;
    }

    /**
     * Construtor de cópia de Atividade Economica.
     */
    public AtivEconomica(AtivEconomica umaAtividade)
    {
        this.codigo = umaAtividade.getCodigo();
        this.natureza = umaAtividade.getNatureza();
        this.coefDeducao = umaAtividade.getCoefDeducao();
    }
    
    /**
     * Devolve o valor do Codigo da Atividade Economica.
     */
    public int getCodigo()
    {
        return this.codigo;
    }

    /**
     * Atualiza o valor do Codigo da Atividade Economica.
     */
    public void setCodigo(String novoCodigo)
    {
        this.natureza = novoCodigo;
    }
    
    /**
     * Devolve o valor da Natureza da Atividade Economica.
     */
    public String getNatureza()
    {
        return this.natureza;
    }

    /**
     * Atualiza o valor da Natureza da Atividade Economica.
     */
    public void setNatureza(String novaNatureza)
    {
        this.natureza = novaNatureza;
    }
    
    /**
     * Devolve o valor do Coeficiente de Dedução relativo à Atividade Economica.
     */
    public double getCoefDeducao()
    {
        return this.coefDeducao;
    }
    
    /**
     * Atualiza o valor do Coeficiente de Dedução relativo à Atividade Economica.
     */
    public void setCoefDeducao(double novoCoefDeducao)
    {
        this.coefDeducao = novoCoefDeducao;
    }
    
    /**
     * Implementação do método de clonagem de uma Atividade.
     */
    public AtivEconomica clone()
    {
        return new AtivEconomica(this);
    }
    
    /**
     * Implementação do método de igualdade entre duas Atividade.
     */
    public boolean equals(Object o)
    {
        if (this == o) 
            return true;
        if (o == null || this.getClass() != o.getClass()) 
            return false;
            
        AtivEconomica atividade = (AtivEconomica) o;
        return atividade.getCodigo()==this.codigo && atividade.getNatureza()==this.natureza && atividade.getCoefDeducao()==this.coefDeducao;
    }
   
    /**
     * Implementação do método toString.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
      
        sb.append("Codigo: ");
        sb.append(this.codigo+"\n");
        sb.append("Natureza: ");
        sb.append(this.natureza+"\n");
        sb.append("Coeficiente de Dedução: ");
        sb.append(this.coefDeducao+"\n");

        return sb.toString();
    }
}
