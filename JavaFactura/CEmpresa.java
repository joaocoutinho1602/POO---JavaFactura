import java.util.ArrayList;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe Contribuinte Empresa (Sub-Classe da Classe Contribuinte).
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class CEmpresa extends Contribuinte implements Serializable, ReducaoImposto
{
    private ArrayList<String> ativEconom;
    private int zonaGeografica;
    private double fatorDedFiscal;
    private double totalFaturado;
    
    /**
     * Construtor por omissão de Contribuinte Empresa.
     */
    public CEmpresa()
    {
        super("","","","","");
        this.ativEconom = new ArrayList <String>();
        this.zonaGeografica = 0;
        this.fatorDedFiscal = 0.0;
        this.totalFaturado = 0.0;
    }
    
    /**
     * Construtor parametrizado de Contribuinte Empresa.
     */
    public CEmpresa(ArrayList <String> cAtivEconom, int cZonaGeografica, double cTotalFaturado, String cNif, String cEmail, String cNome, String cMorada, String cPassword)
    {
        super(cNif, cEmail, cNome, cMorada, cPassword);
        this.ativEconom = new ArrayList<String>();
        this.zonaGeografica = cZonaGeografica;
        for(String cod: cAtivEconom)
        {
            ativEconom.add(cod);
        }
        this.fatorDedFiscal = 1 + reducaoImposto();
        this.totalFaturado = cTotalFaturado;
    }

    /**
     * Construtor de cópia de Contribuinte Empresa.
     */
    public CEmpresa(CEmpresa umCEmpresa)
    {
        super(umCEmpresa.getNif(), umCEmpresa.getEmail(), umCEmpresa.getNome(), umCEmpresa.getMorada(),umCEmpresa.getPassword());
        this.ativEconom = umCEmpresa.getAtivEconom();
        this.fatorDedFiscal = umCEmpresa.getFatorDedFiscal();
        this.totalFaturado = umCEmpresa.getTotalFaturado();
    }
    
    /**
     * Devolve o valor das Atividades Económicas do Contribuinte Empresa.
     */
    public ArrayList<String> getAtivEconom()
    {
        ArrayList<String> ae = new ArrayList<String>();
        
        for(String cod: ativEconom)
        {
            ae.add(cod);
        }
        return ae;
    }
    
    /**
     * Atualiza o valor das Atividades Económicas do Contribuinte Empresa.
     */
    public void setAtivEconom(ArrayList<String> novoAtivEconom)
    {
        this.ativEconom = novoAtivEconom;
    }
    
    /**
     * Devolve o valor do fator de dedução fiscal do Contribuinte Empresa.
     */
    public double getFatorDedFiscal()
    {
        return this.fatorDedFiscal;
    }
    
    /**
     * Atualiza o valor do fator de dedução fiscal do Contribuinte Empresa.
     */
    public void setFatorDedFiscal(double novoFatorDedFiscal)
    {
        this.fatorDedFiscal = novoFatorDedFiscal;
    }
    
    /**
     * Devolve o valor do total acumulado faturado do Contribuinte Empresa.
     */
    public double getTotalFaturado()
    {
        return this.totalFaturado;
    }
    
    /**
     * Atualiza o valor do total acumulado faturado do Contribuinte Empresa.
     */
    public void setTotalFaturado(double novoTotalFaturado)
    {
        this.totalFaturado= novoTotalFaturado;
    }
    
    /**
     * Implementação do método de clonagem de um Contribuinte Empresa.
     */
    public CEmpresa clone()
    {
        return new CEmpresa(this);
    }
    
    /**
     * Implementação do método de igualdade entre dois Contribuintes Empresa.
     */
    public boolean equals(Object o)
    {
        if (this == o) 
            return true;
        if (o == null || this.getClass() != o.getClass() || !super.equals(o)) 
            return false;
            
        CEmpresa cEmpresa = (CEmpresa) o;
        return cEmpresa.getAtivEconom()==this.ativEconom && 
        cEmpresa.getFatorDedFiscal()==this.fatorDedFiscal;
    }
   
    /**
     * Implementação do método toString
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n-----------------------------------------\n");
        sb.append(super.toString()+"\n");
        sb.append("Zona Geográfica: ");
        sb.append(this.zonaGeografica+"\n");
        sb.append("Atividade Económica: ");
        for(String cod: ativEconom)
        {
            sb.append(cod); sb.append(", ");
        }
        sb.append("\n");
        sb.append("Fator Ded. Fiscal: ");
        sb.append(this.fatorDedFiscal+"\n");
        sb.append("\n-----------------------------------------\n");
       
        return sb.toString();
    }
    
    /**
     * Determina o valor do fator de dedução fiscal de um Contribuinte Empresa de acordo com a Zona Geográfica.
     * Foi considerado um fator que é tanto maior quanto o número da zona geografica (este fator indicará o grau de desfavorecimento geográfico.
     * 0-Litoral Sul; 1-Litoral Centro; 2-Litoral Norte; 3-Interior Sul; 4-Interior Centro; 5-Interior Norte.
     */
    public double reducaoImposto()
    {
        if (this.zonaGeografica==3 || this.zonaGeografica==4 || this.zonaGeografica==5)
            return (this.zonaGeografica * 0.01);
        else
            return 0;
    }
}
