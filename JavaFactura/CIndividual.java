import java.util.ArrayList;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe Contribuinte Individual (Sub-Classe da Classe Contribuinte).
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class CIndividual extends Contribuinte implements Serializable, ReducaoImposto
{
    private int numDepend;
    private ArrayList <String> nifAF;
    private double coefFiscal;
    private ArrayList <String> naturezaAtivEcon;
    private double totalGasto;

    /**
     * Construtor por omissão de Contribuinte Individual.
     */
    public CIndividual()
    {
        super("","","","","");
        this.numDepend = 0;
        this.nifAF = new ArrayList <String>();
        this.coefFiscal = 0.0;
        this.naturezaAtivEcon = new ArrayList <String>();
        this.totalGasto = 0.0;
    }
    
    /**
     * Construtor parametrizado de Contribuinte Individual.
     */
    public CIndividual(int cNumDepend, ArrayList <String> cNifAF, ArrayList <String> cNaturezaAtivEcon, double cTotalGasto, String cNif, String cEmail, String cNome, String cMorada, String cPassword)
    {
        super(cNif, cEmail, cNome, cMorada, cPassword);
        this.numDepend = cNumDepend;
        this.coefFiscal = calculaCoefFiscal()+reducaoImposto();
        
        this.nifAF = new ArrayList <String>();
        for(String nif: cNifAF)
        {
            nifAF.add(nif);
        }

        this.naturezaAtivEcon = new ArrayList <String>();
        for(String natureza: cNaturezaAtivEcon)
        {
            naturezaAtivEcon.add(natureza);
        }
        
        this.totalGasto = cTotalGasto;
    }
    
    /**
     * Construtor de cópia de Contribuinte Individual.
     */
    public CIndividual(CIndividual umCIndividual)
    {
        super(umCIndividual.getNif(), umCIndividual.getEmail(), umCIndividual.getNome(), umCIndividual.getMorada(),umCIndividual.getPassword());
        this.numDepend = umCIndividual.getNumDepend();
        this.nifAF = umCIndividual.getNifAF();
        this.coefFiscal = umCIndividual.getCoefFiscal();
        this.naturezaAtivEcon = umCIndividual.getNaturezaAtivEcon();
        this.totalGasto = umCIndividual.getTotalGasto();
    }
    
    /**
     * Devolve o valor do número de dependentes do Contribuinte Individual.
     */
    public int getNumDepend()
    {
        return this.numDepend;
    }
    
    /**
     * Atualiza o valor do número de dependentes do Contribuinte Individual.
     */
    public void setNumDepend(int novoNumDepend)
    {
        this.numDepend = novoNumDepend;
    }
    
    /**
     * Devolve o valor dos NIF's do Agregado Familiar do Contribuinte Individual.
     */
    public ArrayList <String> getNifAF()
    {
        ArrayList<String> naf = new ArrayList<String>();
        
        for(String nif: nifAF)
        {
            naf.add(nif);
        }
        return naf;
    }
    
    /**
     * Atualiza o valor dos NIF's do Agregado Familiar do Contribuinte Individual.
     */
    public void setNifAF(ArrayList<String> novoNifAF)
    {
        this.nifAF = novoNifAF;
    }

    /**
     * Devolve o valor do coeficiente de dedução fiscal do Contribuinte Individual.
     */
    public double getCoefFiscal()
    {
        return this.coefFiscal;
    }
    
    /**
     * Atualiza o valor do coeficiente de dedução fiscal dos NIF's do Agregado Familiar do Contribuinte Individual.
     */
    public void setCoefFiscal(int novoCoefFiscal)
    {
        this.coefFiscal = novoCoefFiscal;
    }
    
    /**
     * Devolve o valor da natureza das Atividades Económicas do Contribuinte Individual.
     */
    public ArrayList<String> getNaturezaAtivEcon()
    {
        ArrayList<String> cae = new ArrayList<String>();
        
        for(String cod: naturezaAtivEcon)
        {
            cae.add(cod);
        }
        return cae;
    }
    
    /**
     * Atualiza o valor da natureza das Atividades Económicas do Contribuinte Individual.
     */
    public void setNaturezaAtivEcon(ArrayList<String> novaNaturezaAtivEcon)
    {
        this.naturezaAtivEcon = novaNaturezaAtivEcon;
    }
    
    /**
     * Devolve o valor do total acumulado gasto pelo Contribuinte Individual.
     */
    public double getTotalGasto()
    {
        return this.totalGasto;
    }
    
    /**
     * Atualiza o valor do total acumulado gasto pelo Contribuinte Individual.
     */
    public void setTotalGasto(double novoTotalGasto)
    {
        this.totalGasto = novoTotalGasto;
    }
    
    /**
     * Implementação do método de clonagem de um CIndividual.
     */
    public CIndividual clone()
    {
        return new CIndividual(this);
    }
    
    /**
     * Implementação do método de igualdade entre dois Contribuintes Individuais.
     */
    public boolean equals(Object o)
    {
        if (this == o) 
            return true;
        if (o == null || this.getClass() != o.getClass() || !super.equals(o)) 
            return false;
            
        CIndividual cIndividual = (CIndividual) o;
        return cIndividual.getNumDepend()==this.numDepend && cIndividual.getNifAF()==this.nifAF 
        && cIndividual.getCoefFiscal()==this.coefFiscal && cIndividual.getNaturezaAtivEcon()==this.naturezaAtivEcon;
    }
   
    /**
     * Implementação do método toString
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n-----------------------------------------\n");
        sb.append(super.toString()+"\n");
        sb.append("Num. Dependentes: ");
        sb.append(this.numDepend+"\n");
        sb.append("NIF Agreg. Familiar: ");
        for(String nif: nifAF)
        {
            sb.append(nif); sb.append(", ");
        }
        sb.append("\n");
        sb.append("Coef. Fiscal: ");
        sb.append(this.coefFiscal+"\n");
        sb.append("Ativ. Economica: ");
        for(String natureza: naturezaAtivEcon)
        {
            sb.append(natureza); sb.append(", ");
        }
        sb.append("\n");
        sb.append("Total Gasto: ");
        sb.append(this.totalGasto+"\n");
        sb.append("\n-----------------------------------------\n");
        
        return sb.toString();
    }
    
    /**
     * Determina o valor do coeficiente de dedução fiscal de um Contribuinte Individual de acordo com o número de dependentes.
     */
    
    public double calculaCoefFiscal()
    {
        return (1 + this.numDepend * 0.02);
    }
    
    public double reducaoImposto()
    {
        if (this.numDepend >= 4 && this.numDepend <8)
            return ((this.numDepend-3) * 0.05); 
        else
            return 0;
    }
}
