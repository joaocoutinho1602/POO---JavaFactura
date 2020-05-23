import java.util.Comparator;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe TotalFaturadooComparator.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class TotalFaturadoComparator implements Comparator<CEmpresa>
{
    /**
     * Implementação do método Compare que ordena um conjunto de Contribuintes Empresa por 
     * Valor Total Faturado.
     */
    public int compare(CEmpresa c1, CEmpresa c2)
    {
        Double totalFaturado1 = (Double) c1.getTotalFaturado();
        Double totalFaturado2 = (Double) c2.getTotalFaturado();
        int cmpTotalFaturado=totalFaturado1.compareTo(totalFaturado2);
        int cmpNif=c1.getNif().compareTo(c2.getNif());
       
        if (cmpTotalFaturado!=0)
            return cmpTotalFaturado;
        else
            return cmpNif;
    }
}
