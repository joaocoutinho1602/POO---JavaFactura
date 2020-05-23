import java.util.Comparator;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe FaturaValorComparator.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class FaturaValorComparator implements Comparator<Fatura>
{
    /**
     * Implementação do método Compare que ordena um conjunto de Faturas por Valor.
     */
    public int compare(Fatura f1, Fatura f2)
    {
        Double valor1 = (Double) f1.getValor();
        Double valor2 = (Double) f2.getValor();
        int cmpValor=valor1.compareTo(valor2);
        int cmpData=f1.getData().compareTo(f2.getData());
        int cmpNifEmitente=f1.getNifEmitente().compareTo(f2.getNifEmitente());
        int cmpNumero=f1.getNumero().compareTo(f2.getNumero());
       
        if (cmpValor!=0)
            return cmpValor;
        else if(cmpData!=0)
            return cmpData;            
        else if(cmpNifEmitente!=0)
            return cmpNifEmitente;
        else
            return cmpNumero;
    }    
}
