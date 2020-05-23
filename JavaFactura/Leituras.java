import java.util.Scanner;
import static java.lang.System.out;
import java.time.LocalDate;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Classe Leituras.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class Leituras
{
    public static Scanner input = new Scanner(System.in);

    /**
     * Lê valor e valida-o se for do tipo inteiro. Caso contrário devolve um erro.
     */
    public static int leInteiro(String s)
    {
        out.println(s);
        while(true)
        {
            try
            {
                int x = input.nextInt();
                input.nextLine();
                return x;
            }
            catch(Exception e)
            {
                out.println("Valor Inválido! Por favor introduza um inteiro.");
                input.nextLine();
                //System.out.flush();   
            }
        }
    }
    
    /**
     * Lê valor e valida-o se for do tipo double. Caso contrário devolve um erro.
     */
    public static double leDouble(String s)
    {
        out.println(s);
        while(true)
        {
            try
            {
                //x = input.nextInt();
                return input.nextDouble(); //return x;
            }
            catch(Exception e)
            {
                out.println("Valor Inválido! Por favor introduza um número.");
                input.nextLine();
                //System.out.flush();   
            }
        }
    }
    
    /**
     * Lê uma string e devolve-a.
     */
    public static String leString(String s)
    {
        out.println(s);
        return input.nextLine();
    }
    
    /**
     * Lê valor e valida-o se for do tipo data. Caso contrário devolve um erro.
     */
    public static LocalDate leData(String s)
    {
        out.println(s);
        while(true)
        {
            try
            {
                //x = input.nextInt();
                return LocalDate.parse(input.nextLine());
            }
            catch(Exception e)
            {
                out.println("Valor Inválido! Por favor introduza a data no formato indicado.");
                //input.nextLine();
            }
        }
    }
}

    /*
/**
* read a floating point number from the console. 
* The input is terminated by a newline
* @param prompt the prompt string to display
* @return the input value as a double
* @exception NumberFormatException if bad input
/

public static double readDouble(String prompt)
{  while(true)
{  printPrompt(prompt);
try
{  return Double.valueOf
(readString().trim()).doubleValue();
} catch(NumberFormatException e)
{  System.out.println
("Not a floating point number. Please try again!");
}
}
}

public static void printPrompt(String prompt)
{  System.out.print(prompt + " ");
System.out.flush();
}
*/

