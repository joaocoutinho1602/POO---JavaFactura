import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe Gestão de Contribuintes. 
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class GestaoContribuintes implements Serializable
{
   private Map<String,Contribuinte> contribuintes;
   /**
    * Construtor por omissão de Gestão de Contribuintes.
    */
   public GestaoContribuintes()
   {
       this.contribuintes = new HashMap<>();
   }

   /**
    * Construtor parametrizado de Contribuinte Empresa.
    */
   public GestaoContribuintes(Map<String,Contribuinte> conts) 
   {
       this.contribuintes = conts.values().stream().collect(Collectors.toMap((c) -> c.getNif(),(c) -> c.clone()));
   }
   
   /**
    * Construtor de cópia de Contribuinte Empresa.
    */
   public GestaoContribuintes(GestaoContribuintes gc) 
   {
       this.contribuintes = gc.getContribuintes();    
   }
   
   /**
    * Devolve o valor do conjunto de Contribuintes.
    */
   public Map<String,Contribuinte> getContribuintes() 
   {
       return this.contribuintes.values().stream().collect(Collectors.toMap((c) -> c.getNif(),(c) -> c.clone())); 
   }
   
   /**
    * Implementação do método de clonagem de um Contribuinte Empresa.
    */
   public GestaoContribuintes clone() 
   {
       return new GestaoContribuintes(this); 
   } 
   
   /**
    * Implementação do método de igualdade entre dois Contribuintes Empresa.
    */
   public boolean equals(Object o) 
   {
       if (this == o) 
            return true;
       if ((o == null) || (this.getClass() != o.getClass()))
            return false;
       
       GestaoContribuintes gc = (GestaoContribuintes) o;
       return this.contribuintes.equals(gc.getContribuintes());
   } 
   
   /**
    * Implementação do método toString
    */
   public String toString() 
   {
       StringBuffer sb = new StringBuffer();
       for (Contribuinte c: this.contribuintes.values())
       sb.append(c.toString() + "\n");
       return sb.toString(); 
   }
   
   /**
    * Adiciona novo Contribuinte.
    */
   public void addContribuinte(Contribuinte cont) 
   {
       this.contribuintes.put(cont.getNif(), cont.clone());    
   }
   
   /**
    * Atualiza o valor do total gasto por contribuinte Individual aquando da criação de uma
    * nova fatura.
    */
   public void atualizaTotalGasto(String nifCI, double totalGastoFatura)
   {
      CIndividual cont = (CIndividual) this.contribuintes.get(nifCI);
      double total = totalGastoFatura + cont.getTotalGasto();
      cont.setTotalGasto(total);
   }
   
   /**
    * Atualiza o valor do total faturado por um Contribuinte Empresa aquando da criação de uma
    * nova fatura.
    */
   public void atualizaTotalFaturado(String nifCE, double totalGastoFatura)
   {
      CEmpresa cont = (CEmpresa) this.contribuintes.get(nifCE);
      double total = totalGastoFatura + cont.getTotalFaturado();
      cont.setTotalFaturado(total);
   }
}