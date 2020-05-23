import java.time.LocalDate;
import java.util.ArrayList;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe Fatura.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class Fatura implements Comparable<Fatura>, Serializable
{
    private String numero;
    private String nifEmitente;
    private String desigEmitente;
    private LocalDate data;
    private String nifCliente;
    private String descricaoDespesa;
    private String natureza;
    private double valor;
    private double valorDeducao;
    private String estado;
    private ArrayList <String> registosEstado;
    
    /**
     * Construtor por omissão de Fatura.
     */
    public Fatura()
    {
        this.numero = "";
        this.nifEmitente = "";
        this.desigEmitente = "";
        this.data = data.now();
        this.nifCliente = "";
        this.descricaoDespesa = "";
        this.natureza = "";
        this.valor = 0.0;
        this.valorDeducao =0.0;
        this.estado = "";
        this.registosEstado = new ArrayList <String>();
    }
    
    /**
     * Construtor parametrizado de Fatura.
     */
    public Fatura(String num, String nifEmit, String desEmit, LocalDate data, String nifCliente, String desDesp, String nat, Double valor, Double vDeducao, String estado, ArrayList <String> cRegistosEstado)
    {
        this.numero = num;
        this.nifEmitente = nifEmit;
        this.desigEmitente = desEmit;
        this.data = data;
        this.nifCliente = nifCliente;
        this.descricaoDespesa = desDesp;
        this.natureza = nat;
        this.valor = valor;
        this.valorDeducao = vDeducao;
        this.estado = estado;
        
        this.registosEstado = new ArrayList <String>();
        for(String est: cRegistosEstado)
        {
            registosEstado.add(est);
        }
        
    }

    /**
     * Construtor de cópia de Fatura.
     */
    public Fatura(Fatura umaFatura)
    {
        this.numero = umaFatura.getNumero();
        this.nifEmitente = umaFatura.getNifEmitente();
        this.desigEmitente = umaFatura.getDesigEmitente();
        this.data = umaFatura.getData();
        this.nifCliente = umaFatura.getNifCliente();
        this.descricaoDespesa = umaFatura.getDescricaoDesp();
        this.natureza = umaFatura.getNatureza();
        this.valor = umaFatura.getValor();
        this.valorDeducao = umaFatura.getVDeducao();
        this.estado = umaFatura.getEstado();
        this.registosEstado = umaFatura.getRegistosEstado();
    }

    /**
     * Devolve o valor do Numero da Fatura.
     */
    public String getNumero()
    {
        return this.numero;
    }
    
    /**
     * Atualiza o valor do Numero da Fatura.
     */
    public void setNumero(String novoNumero)
    {
        this.numero = novoNumero;
    }    
    
    /**
     * Devolve o valor do NIF do Emitente da Fatura.
     */
    public String getNifEmitente()
    {
        return this.nifEmitente;
    }
    
    /**
     * Atualiza o valor do NIF do Emitente da Fatura.
     */
    public void setNifEmitente(String novoNifEmitente)
    {
        this.nifEmitente = novoNifEmitente;
    }
    
    /**
     * Devolve o valor da Designação do Emitente da Fatura.
     */
    public String getDesigEmitente()
    {
        return this.desigEmitente;
    }
    
    /**
     * Atualiza o valor da Designação do Emitente da Fatura.
     */
    public void setDesigEmitente(String novoDesEmitente)
    {
        this.desigEmitente = novoDesEmitente;
    }
    
    /**
     * Devolve o valor da Data da Fatura.
     */
    public LocalDate getData()
    {
        return this.data;
    }
    
    /**
     * Atualiza o valor da Data da Fatura.
     */
    public void setNovaData(LocalDate novaData)
    {
        this.data = novaData;
    }
    
    /**
     * Devolve o valor do NIF do Cliente da Fatura.
     */
    public String getNifCliente()
    {
        return this.nifCliente;
    }
    
    /**
     * Atualiza o valor do NIF do Cliente da Fatura.
     */
    public void setNifCliente(String novoNifCliente)
    {
        this.nifCliente = novoNifCliente;
    }
    
    /**
     * Devolve o valor da Descrição da Despesa da Fatura.
     */
    public String getDescricaoDesp()
    {
        return this.descricaoDespesa;
    }

    /**
     * Atualiza o valor da Descrição da Despesa da Fatura.
     */
    public void setdescricaoDespesa(String novoDesDesp)
    {
        this.descricaoDespesa = novoDesDesp;
    }
  
    /**
     * Devolve o valor da Natureza da Fatura.
     */
    public String getNatureza()
    {
        return this.natureza;
    }
    
    /**
     * Atualiza o valor da Natureza da Fatura.
     */
    public void setNatureza(String novaNatureza)
    {
        this.natureza = novaNatureza;
    }
    
    /**
     * Devolve o valor da Fatura.
     */
    public double getValor()
    {
        return this.valor;
    }
    
    /**
     * Atualiza o valor da Fatura.
     */
    public void setValor(double novoValor)
    {
        this.valor = novoValor;
    }
    
    /**
     * Devolve o valor da Dedução da Fatura.
     */
    public double getVDeducao()
    {
        return this.valorDeducao;
    }
    
    /**
     * Atualiza o valor da Dedução da Fatura.
     */
    public void setVDeducao(double novoVDeducao)
    {
        this.valorDeducao = novoVDeducao;
    }
    
    /**
     * Devolve o estado da Fatura.
     */ 
    public String getEstado()
    {
        return this.estado;
    }
    
    /**
     * Atualiza o estado da Fatura.
     */
    public void setEstado(String novoEstado)
    {
        this.estado = novoEstado;
    }
    
    /**
     * Devolve o valor dos Registos de Estado.
     */
    public ArrayList <String> getRegistosEstado()
    {
        ArrayList<String> reg = new ArrayList<String>();
        
        for(String est: registosEstado)
        {
            reg.add(est);
        }
        return reg;
    }
    
    /**
     * Atualiza o valor dos Registos de Estado.
     */
    public void setRegistosEstado(ArrayList<String> novoRegistosEstado)
    {
        this.registosEstado = novoRegistosEstado;
    }
    
    /**
     * Atualiza o valor dos Registos de Estado recebendo como parâmetro uma nova Natureza de
     * Atividade Económica.
     */
    public void setRegistosEstado(String natureza)
    {
        this.registosEstado.add(natureza);
    }
    
    /**
     * Implementação do método de clonagem de uma Fatura.
     */
    public Fatura clone()
    {
        return new Fatura(this);
    }
    
    /**
     * Implementação do método de igualdade entre duas Faturas.
     */
    public boolean equals(Object o)
    {
        if (this == o) 
            return true;
        if (o == null || this.getClass() != o.getClass()) 
            return false;
            
        Fatura fatura = (Fatura) o;
        return fatura.getNumero()==this.numero && fatura.getNifEmitente()==this.nifEmitente && fatura.getDesigEmitente()==this.desigEmitente && fatura.getData()==this.data 
        && fatura.getNifCliente()==this.nifCliente && fatura.getDescricaoDesp()==this.descricaoDespesa &&
        fatura.getNatureza()==this.natureza && fatura.getValor()==this.valor && fatura.getVDeducao()==this.valorDeducao 
        && fatura.getEstado()==this.estado && fatura.getRegistosEstado()==this.registosEstado;
    }
   
    /**
     * Implementação do método toString.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n-----------------------------------------\n");
        sb.append("Numero Fatura: ");
        sb.append(this.numero+"\n");
        sb.append("NIF do Emitente: ");
        sb.append(this.nifEmitente+"\n");
        sb.append("Designação Emitente: ");
        sb.append(this.desigEmitente+"\n");
        sb.append("Data da Despesa: ");
        sb.append(this.data+"\n");
        sb.append("NIF Cliente: ");
        sb.append(this.nifCliente+"\n");
        sb.append("Descrição da Despesa: ");
        sb.append(this.descricaoDespesa+"\n");
        sb.append("Valor da Despesa: ");
        sb.append(this.valor+"\n");
        sb.append("Natureza da Despesa: ");
        sb.append(this.natureza+"\n");
        sb.append("Valor da Dedução: ");
        sb.append(this.valorDeducao+"\n");
        sb.append("Estado: ");
        sb.append(this.estado+"\n");
        sb.append("Registos de Estado: ");
        for(String estado: registosEstado)
        {
            sb.append(estado); sb.append(", ");
        }
        sb.append("\n-----------------------------------------\n");
        
        return sb.toString();
    }
  
    /**
     * Implementação do método compare que ordena, por defeito, as Faturas por Data.
     */
    public int compareTo (Fatura fat)
    {
        int cmpData=this.data.compareTo(fat.getData());
        int cmpNifEmitente=this.nifEmitente.compareTo(fat.getNifEmitente());
        int cmpNumero=this.numero.compareTo(fat.getNumero());
       
        if (cmpData!=0)
            return cmpData;
        else if(cmpNifEmitente!=0)
            return cmpNifEmitente;
        else
            return cmpNumero;
    }
}
