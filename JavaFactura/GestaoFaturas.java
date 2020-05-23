import java.util.HashMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe Gestao de Faturas.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class GestaoFaturas implements Serializable
{
   private HashMap<String, TreeSet <Fatura>> faturasCI;
   private HashMap<String, TreeSet <Fatura>> faturasCE;
   
   /**
    * Construtor por omissão de Gestão de Faturas.
    */
   public GestaoFaturas()
   {
       this.faturasCI = new HashMap<>();
       this.faturasCE = new HashMap<>();
   }
    
   /**
    * Construtor parametrizado de Gestão de Faturas.
    */
   public GestaoFaturas(HashMap<String, TreeSet <Fatura>> fatsCI, HashMap<String, TreeSet <Fatura>> fatsCE) 
   {
       for(TreeSet<Fatura> fCI : fatsCI.values())
       {
           String nifCI = fCI.first().getNifCliente();
           TreeSet<Fatura> arvCI = fCI.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
           this.faturasCI.put(nifCI,arvCI);
       }
       
       for(TreeSet<Fatura> fCE : fatsCE.values())
       {
           String nifCE = fCE.first().getNifEmitente();
           TreeSet<Fatura> arvCE = fCE.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
           this.faturasCE.put(nifCE,arvCE);
       }
   }
     
   /**
    * Construtor de cópia de Gestão de Faturas.
    */
   public GestaoFaturas(GestaoFaturas gf) 
   {
       this.faturasCI = gf.getFaturasCI();    
       this.faturasCE = gf.getFaturasCE();   
   }
   
   /**
    * Devolve o valor do conjunto de Faturas relativas ao Contribuinte Individual.
    */
   public HashMap<String, TreeSet <Fatura>> getFaturasCI() 
   {
       HashMap<String, TreeSet <Fatura>> res = new HashMap<>();
       for(TreeSet<Fatura> f : this.faturasCI.values())
       {
           String nifC = f.first().getNifCliente();
           TreeSet<Fatura> arv = f.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
           res.put(nifC,arv);
       }
       return res;
   }
   
   /**
    * Devolve o valor do conjunto de Faturas relativas ao Contribuinte Empresa.
    */
   public HashMap<String, TreeSet <Fatura>> getFaturasCE() 
   {
       HashMap<String, TreeSet <Fatura>> res = new HashMap<>();
       for(TreeSet<Fatura> f : this.faturasCE.values())
       {
           String nifC = f.first().getNifEmitente();
           TreeSet<Fatura> arv = f.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
           res.put(nifC,arv);
       }
       return res;
   }

   /**
    * Implementação do método de clonagem de um Sistema de Gestão de Faturas.
    */
   public GestaoFaturas clone() 
   {
       return new GestaoFaturas(this); 
   } 
   
   /**
    * Implementação do método de igualdade entre dois Sistemas de Gestão de Faturas.
    */
   public boolean equals(Object o) 
   {
       if (this == o) 
           return true;
       if ((o == null) || (this.getClass() != o.getClass()))
           return false;
       GestaoFaturas gf = (GestaoFaturas) o;
       return this.faturasCI.equals(gf.getFaturasCI()) && this.faturasCE.equals(gf.getFaturasCE());    
   }     
    
   /**
    * Implementação do método toString para as Faturas dos Contribuintes Individuais
    */
   public String toStringCI() 
   {
       StringBuffer sb = new StringBuffer();
       
       for(TreeSet<Fatura> arv : this.faturasCI.values())
       {
           for (Fatura fat: arv)
                sb.append(fat.toString() + "\n----------\n");
       }
       return sb.toString(); 
   }
   
   /**
    * Implementação do método toString para as Faturas dos Contribuintes Empresa
    */
   public String toStringCE() 
   {
       StringBuffer sb = new StringBuffer();
       
       for(TreeSet<Fatura> arv : this.faturasCE.values())
       {
           for (Fatura fat: arv)
                sb.append(fat.toString() + "\n----------\n");
       }
       return sb.toString(); 
   }
   
   /**
    * Adiciona nova Fatura.
    */
   public void addFatura (Fatura fat)
   { 
       if(this.faturasCI.containsKey(fat.getNifCliente()))
       {
           this.faturasCI.get(fat.getNifCliente()).add(fat);
       }
       else
       {
           TreeSet<Fatura> newArvI = new TreeSet<Fatura>();
           this.faturasCI.put(fat.getNifCliente(),newArvI);
           this.faturasCI.get(fat.getNifCliente()).add(fat);
       }
       
        if(this.faturasCE.containsKey(fat.getNifEmitente()))
       {
           this.faturasCE.get(fat.getNifEmitente()).add(fat);
       }
       else
       {
           TreeSet<Fatura> newArvI = new TreeSet<Fatura>();
           this.faturasCE.put(fat.getNifEmitente(),newArvI);
           this.faturasCE.get(fat.getNifEmitente()).add(fat);
       }
   }
   
   /**
    * Atualiza Fatura existente.
    */
   public void setFatura(String nifCI, String nifEmitente, String nrFatura, String natureza, double vd, String estado)
   {
       ArrayList<Fatura> listFat = new ArrayList<>();
       listFat = this.faturasCI.get(nifCI).stream().filter(f -> f.getNifEmitente().equals(nifEmitente) && f.getNumero().equals(nrFatura)).collect(Collectors.toCollection(ArrayList::new));
       
       listFat.get(0).setNatureza(natureza);
       listFat.get(0).setVDeducao(vd);
       listFat.get(0).setEstado(estado);
       listFat.get(0).setRegistosEstado(natureza);
   }
}
