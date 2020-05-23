import java.util.Comparator;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe TotalGastoComparator.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class TotalGastoComparator implements Comparator<CIndividual>
{
    /**
     * Implementação do método Compare que ordena um conjunto de Contribuintes Individuais por 
     * Valor Total Gasto.
     */
    public int compare(CIndividual c1, CIndividual c2)
    {
        Double totalGasto1 = (Double) c1.getTotalGasto();
        Double totalGasto2 = (Double) c2.getTotalGasto();
        int cmpTotalGasto=totalGasto1.compareTo(totalGasto2);
        int cmpNif=c1.getNif().compareTo(c2.getNif());
       
        if (cmpTotalGasto!=0)
            return cmpTotalGasto;
        else
            return cmpNif;
    }
}
