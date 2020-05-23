import static java.lang.System.out;

import java.io.*;
import java.io.ObjectOutputStream;
/**
 * Esta classe contém os menus de opção para o utilizador.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class Menu
{
    /**
     * Menu Principal
     */
    public static void menuPrincipal()
    {
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* Menu Principal *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        out.println("1: Registar um Contribuinte");
        out.println("2: Login");
        out.println("3: Gravar Dados");
        out.println("4: Carregar Dados");
        out.println("0: Sair");
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    } 

    /**
     * Sub-Menu Registar Contribuinte
     */
    public static void subMenuRegistar()
    {
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* Menu Registar *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        out.println("1: Registar Contribuinte Individual");
        out.println("2: Registar Contribuinte Empresarial");
        out.println("0: Sair");
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }  
    
    
    /**
     * Sub-Menu Contribuinte Individual
     */
    public static void subMenuCIndividual()
    {
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-* Menu Contribuinte Individual *-*-*-*-*-*-*-*-*-*-*-*-*");
        out.println("1: Consultar Despesas Emitidas");
        out.println("2: Consultar Montante Dedução Fiscal Acumulado");
        out.println("3: Associar Atividade Económica a um Documento de Despesa");
        out.println("4: Corrigir Atividade Económica de Documento de Despesa"); 
        out.println("5: Listagem de Faturas de uma determinada Empresa");  
        out.println("0: Sair");
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }    
    
    /**
     * Sub-Menu Contribuinte Empresa
     */
    public static void subMenuCEmpresa()
    {
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-* Menu Contribuinte Empresarial *-*-*-*-*-*-*-*-*-*-*-*-*");
        out.println("1: Criar Fatura");
        out.println("2: Listagem de Faturas da Empresa");         
        out.println("3: Valor Total Faturado num Determinado Periodo");          
        out.println("0: Sair");
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }    
    
    /**
     * Sub-Menu Menu Administrador
     */
    public static void subMenuAdministrador()
    {
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* Menu Administrador *-*-*-*-*-*-*-*-*-*-*-*-*-*");
        out.println("1: Lista dos 10 Contribuintes que Mais Gastam");
        out.println("2: Empresas que mais Faturam e Montante de Deduções Fiscais que Representam");
        out.println("3: Lista de Contribuintes Individuais Registados");
        out.println("4: Lista de Contribuintes Empresariais Registados");
        out.println("5: Lista de Faturas Registadas");
        out.println("0: Sair");
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }  

    /**
     * Sub-Menu com opções de listagem de faturas de contribuinte Individual
     */
    public static void subMenuFaturasCIEmpresa()
    {
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-* Operações sobre Faturas *-*-*-*-*-*-*-*-*-*-*-*-*-*");
        out.println("1: Listagem de Faturas de uma Empresa Ordenadas por Data de Emissão");
        out.println("2: Listagem de Faturas de uma Empresa Ordenadas por Valor");
        out.println("0: Sair");
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }  
    
    /**
     * Sub-Menu com opções de listagem de faturas de contribuinte Individual
     */
    public static void subMenuListagemEmpresas()
    {
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-* Operações sobre Faturas *-*-*-*-*-*-*-*-*-*-*-*-*-*");
        out.println("1: Listagem de Faturas num Intervalo de Datas");
        out.println("2: Listagem de Faturas por Valor Decrescente da Despesa");
        out.println("0: Sair");
        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }     
    
    /**
     * Menu Atividades Economicas
     */
    public static void menuAtividadesEconomicas()
    {
         out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-* Atividades Economicas *-*-*-*-*-*-*-*-*-*-*-*-*-*");
         out.println("1: Despesas Gerais e Familiares");
         out.println("2: Saúde");
         out.println("3: Educação");
         out.println("4: Imóveis");
         out.println("5: Lares");
         out.println("6: Manutenção e Reparação de Veículos Automóveis");
         out.println("7: Manutenção e Reparação de Motociclos");
         out.println("8: Alojamento, Restauração e Similares");
         out.println("9: Salões de Cabeleireiro e Institutos de Beleza");
         out.println("10: Atividades Veterinárias");
         out.println("11: Transportes Públicos");        
         out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    } 
}
