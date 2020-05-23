import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe Gestão de Atividades Económicas.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class GestaoAtivEconomicas implements Serializable
{
    private Map<Integer,AtivEconomica> atividades;

    /**
     * Construtor para objetos da classe Gestão de Atividades Económicas
     */
    public GestaoAtivEconomicas()
    {
        this.atividades = new HashMap<>();
    }

    /**
     * Construtor parametrizado da classe Gestão de Atividades Económicas
     */
    public GestaoAtivEconomicas(Map<Integer,AtivEconomica> ativs) 
    {
        this.atividades = ativs.values().stream().collect(Collectors.toMap((a) -> a.getCodigo(),(a) -> a.clone()));
    }
  
    /**
     * Construtor de cópia para objetos da classe Gestão de Atividades Económicas
     */
    public GestaoAtivEconomicas(GestaoAtivEconomicas ga) 
    {
        this.atividades = ga.getAtividades();    
    }
    
    /**
    * Devolve o valor do conjunto de Atividades Económicas disponíveis.
    */
    public Map<Integer,AtivEconomica> getAtividades() 
    {
        return this.atividades.values().stream().collect(Collectors.toMap((a) -> a.getCodigo(),(a) -> a.clone())); 
    }
    
    /**
    * Implementação do método de clonagem de um Sistema de Gestão de Atividades Económicas.
    */
    public GestaoAtivEconomicas clone() 
    {
        return new GestaoAtivEconomicas(this); 
    }  
   
    /**
    * Implementação do método de igualdade entre dois Sistemas de Gestão de Atividades Económicas.
    */
    public boolean equals(Object o) 
    {
        if (this == o) 
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        GestaoAtivEconomicas ga = (GestaoAtivEconomicas) o;
        return this.atividades.equals(ga.getAtividades());   
    } 
    
    /**
     * Implementação do método toString.
     */
    public String toString() 
    {
        StringBuffer sb = new StringBuffer();
        for (AtivEconomica a: this.atividades.values())
        sb.append(a.toString() + "\n");
        return sb.toString(); 
    }
   
    /**
     * Adiciona nova Atividades Económica.
     */
    public void addAtividade(AtivEconomica ativ) 
    {
        this.atividades.put(ativ.getCodigo(), ativ.clone());    
    }
    
    /**
     * Devolve o coeficiente de dedução de determinada Natureza de Atividade Económica.
     */
    public double getDeducaoNat(String natureza) 
    { 
       for(AtivEconomica ae: this.atividades.values())
       {
           if(ae.getNatureza()==natureza)
                return ae.getCoefDeducao();
       }
       return -1;
    }
}
