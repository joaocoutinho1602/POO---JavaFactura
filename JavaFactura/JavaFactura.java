import java.util.Scanner;
import static java.lang.System.out;
import java.lang.String;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.*;
import java.io.*;
/**
 * Esta classe implementa o Método Main.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class JavaFactura
{
    private static GestaoGeral gg = new GestaoGeral();
    /**
     * Main
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        int opcao;
        gg.carregaListaAE();
        //gg.carregaListaCI();
        //gg.carregaListaCE(); 
        //gg.carregaListaAdmin();
        //gg.carregaFaturas();
    
        do
        {
            Menu.menuPrincipal(); 
            opcao=Leituras.leInteiro("Escolha uma opção: ");
            switch(opcao)
            {
                case 1:
                {
                    int opcaoRegistar;
                    do
                    {
                        Menu.subMenuRegistar(); opcaoRegistar=Leituras.leInteiro("Escolha uma opção: ");
                        switch(opcaoRegistar)
                        {
                            case 1:
                            {
                                String nif=Leituras.leString("NIF:");
                                String email=Leituras.leString("E-Mail:");
                                String nome=Leituras.leString("Nome:");
                                String morada=Leituras.leString("Morada:");
                                String password=Leituras.leString("Password:");
                                int numDependentes=Leituras.leInteiro("Nr. Dependentes:");
                               
                                ArrayList<String> nifAF = new ArrayList<>();
                                for(int i=0; i<numDependentes; i++)
                                {
                                    String nifDep=Leituras.leString("NIF Dependente "+(i+1)+":");
                                    nifAF.add(nifDep);
                                }
                                
                                gg.registaCI(nif, email, nome, morada, password, numDependentes, nifAF);
                                break;
                            }
                            case 2:
                            {
                                String nif=Leituras.leString("NIF:");
                                String email=Leituras.leString("E-Mail:");
                                String nome=Leituras.leString("Nome:");
                                String morada=Leituras.leString("Morada:");
                                String password=Leituras.leString("Password:");
                                /* Foi considerado um fator que é tanto maior quanto o número da zona geografica (este fator indicará o grau de desfavorecimento geográfico.
                                0-Litoral Sul; 1-Litoral Centro; 2-Litoral Norte; 3-Interior Sul; 4-Interior Centro; 5-Interior Norte.*/
                                out.println("0-Litoral Sul; 1-Litoral Centro; 2-Litoral Norte;");
                                out.println("3-Interior Sul; 4-Interior Centro; 5-Interior Norte;");
                                int zonaGeografica;
                                
                                do
                                {
                                    zonaGeografica=Leituras.leInteiro("Zona Geográfica: ");
                                    if (zonaGeografica<0 || zonaGeografica>5)
                                        out.println("Essa opção não existe, introduza uma opção válida (0 a 5).");
                                    else
                                        break;
                                }
                                while(zonaGeografica<0 || zonaGeografica>5);
                                gg.registaCE(nif, email, nome, morada, password, zonaGeografica); 
                                break;
                            }
                            case 0:
                            {
                                break;
                            }
                            default:
                            {
                                out.println("A opção selecionada não existe! Introduza uma opção de 1 a 2.");
                                break;
                            }
                        }
                    }
                    while(opcaoRegistar != 0);
                    break;
                }
                case 2:
                {
                    String nif=Leituras.leString("Introduza NIF:");
                    String password=Leituras.leString("Password:");
                    int resLogin=gg.login(nif, password);
                    if (resLogin==0) 
                    {
                        out.println("Password errada");
                        break;
                    }
                    else if (resLogin==1)
                    {
                        int opcaoCI;
                        do
                        {
                            Menu.subMenuCIndividual(); opcaoCI=Leituras.leInteiro("Escolha uma opção: ");
                            switch(opcaoCI)
                            {
                                case 1:
                                {   
                                    gg.despesasEmitidas(nif);
                                    gg.despesasEmitidasAF(nif);
                                    break;
                                }
                                case 2:
                                {
                                    gg.deducaoFiscalAcum(nif);
                                    gg.deducaoFiscalAcumAF(nif);
                                    break;                                       
                                }
                                case 3:
                                {
                                    gg.associarAE(nif);
                                    break;
                                }
                                case 4:
                                {
                                    gg.corrigirAE(nif);
                                    break;
                                }
                                case 5:
                                {
                                    int opcaoC5;
                                    do
                                    {
                                        Menu.subMenuFaturasCIEmpresa(); opcaoC5=Leituras.leInteiro("Escolha uma opção: ");
                                        switch(opcaoC5)
                                        {
                                            case 1:
                                            {
                                                String nifEmpresa=Leituras.leString("Introduza NIF Empresa: ");

                                                gg.faturasCIEmpresaData(nif, nifEmpresa);
                                                break;
                                            }
                                            case 2:
                                            {
                                                String nifEmpresa=Leituras.leString("Introduza NIF Empresa: ");
                                                
                                                gg.faturasCIEmpresaValor(nif, nifEmpresa);
                                                break;
                                            }
                                            case 0:
                                            {   
                                                break;
                                            }
                                            default:
                                            {                                    
                                                out.println("A opção selecionada não existe! Introduza uma opção de 1 a 2.");
                                                break;
                                            }
                                        }
                                    }
                                    while(opcaoC5 != 0);
                                    break;
                                }
                                case 0:
                                {
                                    break;
                                }
                                default:
                                {
                                    out.println("A opção selecionada não existe! Introduza uma opção de 1 a 5.");
                                    break;
                                }
                            }
                        }
                        while(opcaoCI != 0);
                        break;
                    }
                    else if (resLogin==2) 
                    {
                        int opcaoCE;
                        do
                        {
                            Menu.subMenuCEmpresa(); opcaoCE=Leituras.leInteiro("Escolha uma opção: ");
                            switch(opcaoCE)
                            {
                                case 1:
                                {
                                   String num=Leituras.leString("Numero: ");
                                   String nifCliente=Leituras.leString("NIF Cliente: ");
                                   LocalDate data= LocalDate.now();
                                   String desDesp=Leituras.leString("Descrição da Despesa: ");
                                   double valor=Leituras.leDouble("Valor: ");
                                   
                                   gg.criaFatura(nif, num, nifCliente, data, desDesp, valor);
                                   break;
                                }
                                case 2:
                                {
                                    int opcaoC2;
                                    do
                                    {
                                        Menu.subMenuListagemEmpresas(); opcaoC2=Leituras.leInteiro("Escolha uma opção: ");
                                        switch(opcaoC2)
                                        {
                                            case 1:
                                            {
                                                LocalDate dataInicio=Leituras.leData("Introduza a data de inicio (aaaa-mm-dia): ");
                                                LocalDate dataFim=Leituras.leData("Introduza a data de fim (aaaa-mm-dia): ");
                                                
                                                gg.faturasCEIntData(nif, dataInicio, dataFim);
                                                break;
                                            }
                                            case 2:
                                            {
                                                gg.faturasCEValor(nif);
                                                break;
                                            }
                                            case 0:
                                            {
                                                break;
                                            }
                                            default:
                                            {
                                                out.println("A opção selecionada não existe! Introduza uma opção de 1 a 2."); 
                                                break;
                                            }
                                        }
                                    }
                                    while(opcaoC2 != 0);
                                    break;
                                }
                                case 3:
                                {
                                    LocalDate dataInicio=Leituras.leData("Introduza a data de inicio (aaaa-mm-dia): ");
                                    LocalDate dataFim=Leituras.leData("Introduza a data de fim (aaaa-mm-dia): ");
       
                                    gg.totalFaturadoEmpresaDatas(nif, dataInicio, dataFim);
                                    break;
                                }
                                case 0:
                                {
                                    break;
                                }
                                default:
                                {
                                    out.println("A opção selecionada não existe! Introduza uma opção de 1 a 3.");
                                    break;
                                }
                            }
                        }
                        while(opcaoCE != 0);
                        break;
                    }
                    else if (resLogin==3) 
                    {
                        int opcaoAdmin;
                        do
                        {
                            Menu.subMenuAdministrador(); opcaoAdmin=Leituras.leInteiro("Escolha uma opção: ");
                            switch(opcaoAdmin)
                            {
                                case 1:
                                {
                                   gg.contribuintesMaisGastam();
                                   break;
                                }
                                case 2:
                                {
                                    int numEmpresas=Leituras.leInteiro("Quantas Empresas quer consultar:");
                                    
                                    gg.empresasMaisFaturam(numEmpresas);
                                    break;
                                }
                                case 3:
                                {
                                    gg.listaCIRegistados();
                                    break;
                                }
                                case 4:
                                {
                                    gg.listaCERegistados(); 
                                    break;
                                }
                                case 5:
                                {
                                    gg.listaFaturasRegistadas(); 
                                    break;
                                }
                                case 0:
                                {
                                    break;
                                }
                                default:
                                {
                                    out.println("A opção selecionada não existe! Introduza uma opção de 1 a 5.");
                                    break;
                                }
                            }
                        }
                        while(opcaoAdmin != 0);
                        break;
                    }
                    else
                    {
                        out.println("O NIF introduzido não existe");
                        break;
                    }
                }
                case 3:
                {
                    try 
                    {
                        gg.guardaEstado();
                        System.out.println("Base de Dados gravada com sucesso para os ficheiros BDGC, BDGF e BDGAE");
                        break;
                    }
                    catch(IOException e)
                    { 
                        System.out.println("Não foi possivel abrir o ficheiro. Tente novamente!"); 
                    }
                    
                    catch(ClassNotFoundException e)
                    { 
                        System.out.println("Não foi possivel encontrar a Base de Dados. Tente novamente!"); 
                    }
                    break;
                }
                case 4:
                {
                    try 
                    {
                        gg.carregaEstado();
                        System.out.println("As Bases de Dados foram carregadas com sucesso a partir dos ficheiros BDGC, BDGF e BDGAE");
                        break;
                    }
                    catch(IOException e)
                    { 
                       System.out.println("Não foi possivel abrir o ficheiro. Tente novamente!");
                    }
                    catch(ClassNotFoundException e)
                    { 
                       System.out.println("Não foi possivel encontrar a Base de Dados. Tente novamente!");  
                    }
                    break;
                }
                case 0:
                {
                    break;
                }
                default:
                {
                    out.println("A opção selecionada não existe! Introduza uma opção de 1 a 4.");
                    break;
                }
            }
        }
        while(opcao != 0);
    }   
}
