import java.util.Scanner;
import static java.lang.System.out;
import java.lang.String;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.Iterator;
import java.io.*;
/**
 * A classe GestaoGeral implementa os métodos que permitem criar instancias dos várias classes
 * consideradas bem como os métodos que as interligam.
 * 
 * @author (Luís Fernandes (A76712), Raquel Dias (A32954), João Coutinho (A86272))
 * @version (2018-05-13)
 */
public class GestaoGeral implements Serializable
{
   private GestaoContribuintes gc;
   private GestaoFaturas gf;
   private GestaoAtivEconomicas ga;
   
   Scanner input = new Scanner(System.in);
   
   /**
    * Construtor para objetos da classe GestaoGeral
    */
   public GestaoGeral()
   {
       this.gc = new GestaoContribuintes();
       this.gf = new GestaoFaturas();
       this.ga = new GestaoAtivEconomicas();
   }
    
   /**
    * Regista um Contribuinte Individual.
    */
   public void registaCI(String nif, String email, String nome, String morada, String password, int numDependentes, ArrayList <String> nifAF)
   {       
       ArrayList<String> naeI = new ArrayList<>();
       int codNat;
       try
       {
           Set<AtivEconomica> lista=ga.getAtividades().values().stream().collect(Collectors.toSet());
           for(AtivEconomica ae: lista)
           {
               String nat=ae.getNatureza();
               naeI.add(nat);
           }
       }
       catch (NullPointerException e)
       {
           out.println("Não existem Atividades Económicas inseridas.");
       }
           
       CIndividual c  = new CIndividual(numDependentes, nifAF, naeI, 0.0, nif, email, nome, morada, password);
                      
       gc.addContribuinte(c); 
   }
   
   /**
    * Regista um Contribuinte Empresarial.
    */
   public void registaCE(String nif, String email, String nome, String morada, String password, int zonaGeografica)
   {
       int opcaoAEE;
       Menu.menuAtividadesEconomicas();
       ArrayList<String> naeE = new ArrayList<>();
       int codNat;
       do
       {
           opcaoAEE=Leituras.leInteiro("Introduza opção para Atividade Económica (1 a 11) ou 0 para terminar: ");
           if (opcaoAEE==0)
                break;
           else if (opcaoAEE>0 && opcaoAEE<12)
           {
               String nat=ga.getAtividades().get(opcaoAEE).getNatureza();
               naeE.add(nat);
           }
           else
            out.println("A opção introduzida não existe, introduza uma opção válida (1 a 11) ou 0 para terminar: ");
       }
       while(opcaoAEE!=0);
                                
       CEmpresa c  = new CEmpresa(naeE, zonaGeografica, 0.0, nif, email, nome, morada, password);
       
       gc.addContribuinte(c);
   }
    
   /**
    * Valida o acesso à aplicação utilizando as credenciais (nif e password).
    */
   public int login(String nif, String password)
   {    
       if (gc.getContribuintes().containsKey(nif))
       {
           if (gc.getContribuintes().get(nif).getPassword().equals(password))
           {
                if (gc.getContribuintes().get(nif) instanceof CIndividual)
                    return 1;
                else if (gc.getContribuintes().get(nif) instanceof CEmpresa)
                    return 2;
                else
                    return 3;
           }
           else
                return 0;
       }
       else
            return 5;
   }
    
   /**
    * Devolve as despesas emitidas em nome de um determinado Contribuinte Individual.
    */
   public void despesasEmitidas(String nifCI)
   {
       TreeSet<Fatura> de = new TreeSet<>();
       de=gf.getFaturasCI().get(nifCI);
       try
       {
           out.println("\n");
           out.println("-*-*-*-*- Despesas Emitidas para o NIF "+nifCI+" -*-*-*-*-");
           out.println(de.toString());
       }
       catch (NullPointerException e)
       {
           out.println("Não existem faturas para o NIF "+nifCI+".\n");
       }
   }
   
   /**
    * Devolve as despesas emitidas em nome dos elementos do agregado familiar de um determinado Contribuinte Individual.
    */
   public void despesasEmitidasAF(String nifCI)
   {
       ArrayList <String> listNIfAF = new ArrayList<String>();
       CIndividual ci = (CIndividual) gc.getContribuintes().get(nifCI);
       listNIfAF = ci.getNifAF();
       out.println("-*-*-*-*- Despesas Emitidas para o Agregado Familiar -*-*-*-*-\n");
       for(String nif: listNIfAF)
       {
           try
           {
               out.println("-> NIfAF: "+nif);
               out.println(gf.getFaturasCI().get(nif).toString());
           }
           catch (NullPointerException e)
           {
               out.println("Não existem faturas para o NIF "+nif+".\n");
           }
       } 
   }
   
   /**
    * Devolve o montande de dedução fiscal acumulado de um determinado Contribuinte Individual.
    */
   public void deducaoFiscalAcum(String nifCI)
   {
       try
       {
           out.println("\nValor Fiscal Acumulado do NIF "+nifCI+":");
           out.println(gf.getFaturasCI().get(nifCI).stream().mapToDouble(Fatura::getVDeducao).sum()); 
       }
       catch (NullPointerException e)
       {
           out.println("Não existem deduções para o NIF "+nifCI+".\n");
       }
   }
   
   /**
    * Devolve o montande de dedução fiscal acumulado dos elementos do agregado familiar de um determinado Contribuinte 
    * Individual.
    */
   public void deducaoFiscalAcumAF(String nifCI)
   {
       ArrayList <String> listNIfAF = new ArrayList<String>();
       CIndividual ci = (CIndividual) gc.getContribuintes().get(nifCI);
       listNIfAF = ci.getNifAF();
       out.println("\n--- Valor Fiscal Acumulado pelo Agregado Familiar ---");
       for(String nif: listNIfAF)
       {
           try
           {
               out.println("\n-> NIfAF: "+nif);
               out.println(gf.getFaturasCI().get(nif).stream().mapToDouble(Fatura::getVDeducao).sum());
            }
           catch (NullPointerException e)
           {
               out.println("Não existem faturas para o NIF "+nif+".\n");
           }
       } 
   } 
   
   /**
    * Associa a classificação de atividade económica a documento de despesa.
    */
   public void associarAE(String nifCI)
   {
       int opcaoAEE;
       ArrayList<Fatura> listFat = new ArrayList<>();
       out.print("\n");
       try
       {
           listFat=gf.getFaturasCI().get(nifCI).stream().filter(f -> f.getEstado().equals("pendente")).collect(Collectors.toCollection(ArrayList::new));
           for(Fatura f: listFat)
           {
               out.print("Índice ");
               out.printf("%d: ",(listFat.indexOf(f)+1));
               out.println("\n"+f.toString()+"\n");
           }  
           
           int nrIndice=0;
           nrIndice=Leituras.leInteiro("Introduza o Índice da Fatura a alterar ou 0 para sair: ");
           if(nrIndice > 0 && nrIndice <= listFat.size())
           {
               String nifEmitente = listFat.get(nrIndice-1).getNifEmitente();
               String nrFatura = listFat.get(nrIndice-1).getNumero();               
               
               Menu.menuAtividadesEconomicas();
               int controlo=1;
               do
               {
                   int codNat=Leituras.leInteiro("Introduza uma Atividade Económica: ");
                   if (codNat==0)
                        break;
                   else if (codNat>0 && codNat<12)
                   {
                       String natureza=ga.getAtividades().get(codNat).getNatureza();
                       double vd=0.0;
               
                       CEmpresa ce = (CEmpresa) gc.getContribuintes().get(nifEmitente);
                       CIndividual ci = (CIndividual) gc.getContribuintes().get(nifCI);
                       if (ci.getNaturezaAtivEcon().contains(natureza))
                       {
                           double coefDeducaoAtiv = ga.getAtividades().get(codNat).getCoefDeducao();
                           vd = (listFat.get(nrIndice-1).getValor()) * ce.getFatorDedFiscal() * ci.getCoefFiscal()* coefDeducaoAtiv; 
                        }
       
                       gf.setFatura(nifCI, nifEmitente, nrFatura, natureza, vd, "inserido");
                       controlo=0;
                   }
                   else
                        out.println("A opção introduzida não existe, introduza uma opção válida (1 a 11):");
               }
               while (controlo!=0);
            }
            else if (nrIndice!=0)
                out.println("Essa fatura não existe. Escolha outra ou 0 para sair");
       }
       catch (NullPointerException e)
       {
           out.println("Não existem faturas para o NIF "+nifCI+".\n");
       }
   }     
    
   /**
    * Corrige a classificação de atividade económica de um documento de despesa.
    */
   public void corrigirAE(String nifCI)
   {
      ArrayList<Fatura> listFat = new ArrayList<>();
      out.print("\n");
      try
      {
          listFat=gf.getFaturasCI().get(nifCI).stream().filter(f -> (f.getEstado().equals("inserido") || f.getEstado().equals("corrigido"))).collect(Collectors.toCollection(ArrayList::new)); 
          for(Fatura f: listFat)
          {
              out.print("Índice ");
              out.printf("%d: ",(listFat.indexOf(f)+1));
              out.println("\n"+f.toString()+"\n");
          }
           
          int nrIndice=0;
          nrIndice=Leituras.leInteiro("Introduza o Índice da Fatura a alterar ou 0 para sair: ");
          if(nrIndice > 0 && nrIndice <= listFat.size())
          {
              String nifEmitente = listFat.get(nrIndice-1).getNifEmitente();
              String nrFatura = listFat.get(nrIndice-1).getNumero();
       
              Menu.menuAtividadesEconomicas();
              int controlo=1;
              do
              {
                  int codNat=Leituras.leInteiro("Introduza uma Atividade Económica: ");
                  
                  if (codNat==0)
                        break;
                  else if (codNat>0 && codNat<12)
                  {
                      String natureza=ga.getAtividades().get(codNat).getNatureza();
                      double vd=0.0;
              
                      CEmpresa ce = (CEmpresa) gc.getContribuintes().get(nifEmitente);
                      CIndividual ci = (CIndividual) gc.getContribuintes().get(nifCI);
                      if (ci.getNaturezaAtivEcon().contains(natureza))
                      {
                          double coefDeducaoAtiv = ga.getAtividades().get(codNat).getCoefDeducao();
                          vd = (listFat.get(nrIndice-1).getValor()) * ce.getFatorDedFiscal() * ci.getCoefFiscal()* coefDeducaoAtiv; 
                      }
       
                      gf.setFatura(nifCI, nifEmitente, nrFatura, natureza, vd, "corrigido");
                      controlo=0;
                   }
                   else
                        out.println("A opção introduzida não existe, introduza uma opção válida (1 a 11):");
               }
               while (controlo!=0);
            }
            else if (nrIndice!=0)
                out.println("Essa fatura não existe. Escolha outra ou 0 para sair.");
      }
      catch (NullPointerException e)
      {
          out.println("Não existem faturas para o NIF "+nifCI+".\n");
      }
   }  
   
   /**
    * Devolve a listagem das faturas de uma determinada empresa (associadas a um determinado Contribuinte Individual), ordenada
    * por data de emissão.
    */
   public void faturasCIEmpresaData(String nifCI, String nifEmpresa)
   {
      TreeSet<Fatura> de;
      if(gc.getContribuintes().containsKey(nifEmpresa))
      {
          try
          {
              de=gf.getFaturasCI().get(nifCI).stream().filter(f -> f.getNifEmitente().equals(nifEmpresa)).collect(Collectors.toCollection(TreeSet::new));
              out.println("Listagem de Faturas Ordenadas por Data:");
              out.println(de.toString());
          }
          catch (NullPointerException e)
          {
              out.println("Não existem faturas para o NIF "+nifCI+".");
          }
      }
      else
        out.println("O NIF "+nifEmpresa+" introduzido, não existe.");  
   } 
   
   /**
    * Devolve a listagem das faturas de uma determinada empresa (associadas a um determinado Contribuinte Individual), ordenada
    * por valor.
    */
   public void faturasCIEmpresaValor(String nifCI, String nifEmpresa)
   {
       TreeSet de=new TreeSet<>(new FaturaValorComparator());
       if(gc.getContribuintes().containsKey(nifEmpresa))
       {
           try
           {
               Set<Fatura> lista=gf.getFaturasCI().get(nifCI).stream().filter(f -> f.getNifEmitente().equals(nifEmpresa)).collect(Collectors.toSet());
               for(Fatura f: lista)
               de.add(f);
               
               out.println("Listagem de faturas ordenadas por Valor:");
               out.println(de.toString());
           }
           catch (NullPointerException e)
           {
               out.println("Não existem faturas para o NIF "+nifCI+".");
           }
       }
       else
            out.println("O NIF "+nifEmpresa+" introduzido, não existe.");
   } 
   
   /**
    * Cria fatura associada a despesa feita por um Contribuinte Individual.
    */
   public void criaFatura(String nifCE, String num, String nifCliente, LocalDate data, String desDesp, double valor)
   {
       double vDeducao=0.0;
       String natureza = "", estado="";
       ArrayList <String> regEst = new ArrayList <String>();
       
       CEmpresa ce = (CEmpresa) gc.getContribuintes().get(nifCE);
       CIndividual ci = (CIndividual) gc.getContribuintes().get(nifCliente);
                                        
       String desigEmitente = ce.getNome();

       ArrayList <String> ativs = ce.getAtivEconom();
        
       if(ativs.size()==1 && ci!=null)
       {
           natureza = ativs.get(0);
           if (ci.getNaturezaAtivEcon().contains(natureza))
           {
               double coefDeducaoAtiv = ga.getDeducaoNat(natureza);
               estado = "inserido";
               vDeducao = valor * ce.getFatorDedFiscal() * ci.getCoefFiscal()* coefDeducaoAtiv; 
               regEst.add(natureza);
           }
       }
       else 
       {
           estado = "pendente";
           vDeducao = 0.0;
           regEst.add("ND");
       }
                                    
       Fatura a = new Fatura(num, nifCE, desigEmitente, data, nifCliente, desDesp, natureza, valor, vDeducao, estado, regEst);
        
       gf.addFatura(a);
       gc.atualizaTotalFaturado(nifCE, valor);
       
       try
       {
           gc.atualizaTotalGasto(nifCliente, valor);
       }
       catch (NullPointerException e)
       {
           out.println("O NIF Emitente introduzido não existe, logo esta despesa não será contabilizada para efeitos de NIF's que mais gastaram.");
       } 
   } 
   
   /**
    * Devolve a listagem das faturas por contribuinte num determinado intervalo de datas (relativas a uma determinada empresa).
    */
   public void faturasCEIntData(String nifCE, LocalDate dataInicio, LocalDate dataFim)
   {
      TreeSet <Fatura> fatsED = new TreeSet<>();
      HashMap<String, TreeSet <Fatura>> fatsEDCI = new HashMap<>();
      try
      {
          fatsED=gf.getFaturasCE().get(nifCE).stream().filter(f -> f.getData().isAfter(dataInicio.minusDays(1)) && f.getData().isBefore(dataFim.plusDays(1))).collect(Collectors.toCollection(TreeSet::new));
          
          for(Fatura fat: fatsED)
          {
              if(fatsEDCI.containsKey(fat.getNifCliente()))
              {
                  fatsEDCI.get(fat.getNifCliente()).add(fat);
              }
              else
              {
                  TreeSet<Fatura> newArvI = new TreeSet<Fatura>();
                  fatsEDCI.put(fat.getNifCliente(),newArvI);
                  fatsEDCI.get(fat.getNifCliente()).add(fat);
              }
          }
          
          out.println("Faturas entre " + dataInicio.toString() + " e " + dataFim.toString() + ":\n");         
          for(TreeSet<Fatura> arv: fatsEDCI.values())
          {
              String nifCl = arv.first().getNifCliente();;
              out.println("-> NIF Cliente: "+nifCl);
              for (Fatura fat: arv)
                    out.println(fat.toString());
          }
      }
      catch (NullPointerException e)
      {
          out.println("Não existem faturas para o NIF "+nifCE+".");
      }
   }  
   
   /**
    * Devolve a listagem das faturas por contribuinte ordenadas por valor decrescente de despesa (relativas a uma determinada 
    * empresa).
    */
   public void faturasCEValor(String nifCE)
   {
      HashMap<String, TreeSet <Fatura>> fatsEDCI = new HashMap<>();
      try
      {
          Set<Fatura> lista=gf.getFaturasCE().get(nifCE).stream().collect(Collectors.toSet());
          
           for(Fatura fat: lista)
          {
              if(fatsEDCI.containsKey(fat.getNifCliente()))
              {
                  fatsEDCI.get(fat.getNifCliente()).add(fat);
              }
              else
              {
                  TreeSet<Fatura> newArvI = new TreeSet<Fatura>(new FaturaValorComparator());
                  fatsEDCI.put(fat.getNifCliente(),newArvI);
                  fatsEDCI.get(fat.getNifCliente()).add(fat);
              }
          }
          
          out.println("Listagem de faturas ordenadas por Valor\n");         
          for(TreeSet<Fatura> arv: fatsEDCI.values())
          {
              String nifCl = arv.first().getNifCliente();;
              out.println("-> NIF Cliente: "+nifCl);
              Iterator iterator;
              iterator = arv.descendingIterator();
              while (iterator.hasNext())
                  System.out.println(iterator.next());
          }
      }
      catch (NullPointerException e)
      {
          out.println("Não existem faturas para o NIF "+nifCE+".");
      }
   }     
   
   /**
    * Devolve o total faturado por uma empresa num determinado período.
    */
   public void totalFaturadoEmpresaDatas(String nifCE, LocalDate dataInicio, LocalDate dataFim)
   {
       try
       {
           out.println("Valor Total Acumulado:");
           out.println(gf.getFaturasCE().get(nifCE).stream().filter(f -> f.getData().isAfter(dataInicio.minusDays(1)) && f.getData().isBefore(dataFim.plusDays(1))).mapToDouble(Fatura::getValor).sum());
       }
       catch (NullPointerException e)
       {
          out.println("Não existem faturas para o NIF "+nifCE+", logo o valor faturado até ao momento é 0.0.");
       }
   }   
   
   /**
    * Devolve a relação dos 10 contribuintes que mais gastam em todo o sistema.
    */
   public void contribuintesMaisGastam()
   { 
      TreeSet<CIndividual> de = new TreeSet<>(new TotalGastoComparator());
      
      Set<Contribuinte> lista=gc.getContribuintes().values().stream().filter(c -> c instanceof CIndividual).collect(Collectors.toSet());
       
      for(Contribuinte c: lista)
          de.add((CIndividual) c.clone());
       
      Iterator<CIndividual> it = de.descendingIterator();
      int i=0;
      System.out.println("Listagem dos 10 Contribuintes que mais gastam:");
      while(it.hasNext() && i<10) 
      {
          i++;
          System.out.println(it.next().toString());
      }
   }

   /**
    * Devolve a relação das X empresas que mais faturam em todo o sitema e o respetivo montante de deduções fiscais que as 
    * despesas registadas (dessas empresas) representam.
    */
   public void empresasMaisFaturam(int numEmpresas)
   { 
      TreeSet <CEmpresa> de = new TreeSet<>(new TotalFaturadoComparator());
      
      Set<Contribuinte> lista=gc.getContribuintes().values().stream().filter(c -> c instanceof CEmpresa).collect(Collectors.toSet());
       
      for(Contribuinte c: lista)
          de.add((CEmpresa)c.clone());
      
      Iterator<CEmpresa> it = de.descendingIterator();
      int i=0;
      System.out.println("Listagem dos " + numEmpresas + " que mais faturam:");
      while(it.hasNext() && i<numEmpresas) 
      {
          i++;
          CEmpresa ce = it.next();
          out.println(ce.toString());
          double totalFaturado = ce.getTotalFaturado();  
          out.println("Valor Total Faturado: "+totalFaturado+"\n"); 
          double totalDeducao = gf.getFaturasCE().get(ce.getNif()).stream().mapToDouble(Fatura::getVDeducao).sum(); 
          out.println("Fator de dedução acumulado: "+totalDeducao+"\n");   
      }  
   }  
   
   /**
    * Devolve a listagem dos Contribuintes Individuais registados em todo o sistema.
    */
   public void listaCIRegistados()
   {
       HashSet<CIndividual> ci = new HashSet<>();
       Set<Contribuinte> lista=gc.getContribuintes().values().stream().filter(c -> c instanceof CIndividual).collect(Collectors.toSet());
       for(Contribuinte c: lista)
          ci.add((CIndividual)c.clone());
       out.println(ci.toString());
   }
   
   /**
    * Devolve a listagem dos Contribuintes Empresariais registados em todo o sistema.
    */
   public void listaCERegistados()
   {
       HashSet<CEmpresa> ce = new HashSet<>();
       Set<Contribuinte> lista=gc.getContribuintes().values().stream().filter(c -> c instanceof CEmpresa).collect(Collectors.toSet());
       for(Contribuinte c: lista)
          ce.add((CEmpresa)c.clone());
       out.println(ce.toString());
   }
   
   /**
    * Devolve a listagem das Faturas registadas em todo o sistema.
    */
   public void listaFaturasRegistadas()
   {
       HashSet<Fatura> fats = new HashSet<>();
       try
       {
           Set<Set<Fatura>> lista=gf.getFaturasCE().values().stream().collect(Collectors.toSet());

           for(Set<Fatura> arv: lista)
             for(Fatura f: arv)
                 fats.add(f);
               
           System.out.println("Listagem de faturas no sistema:");
           out.println(fats.toString());
       }
       catch (NullPointerException e)
       {
           out.println("Não existem faturas no sistema.");
       }
   }
   
   public void guardaEstado() throws IOException, ClassNotFoundException
   {
           FileOutputStream fos1 = new FileOutputStream("BDGC");
           FileOutputStream fos2 = new FileOutputStream("BDGF");
           FileOutputStream fos3 = new FileOutputStream("BDGAE");
           ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
           ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
           ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
           oos1.writeObject(this.gc);
           oos2.writeObject(this.gf);
           oos3.writeObject(this.ga);
           oos1.flush(); oos2.flush(); oos3.flush();
           oos1.close(); oos2.close(); oos3.close();
   }
   
   public void carregaEstado() throws IOException, ClassNotFoundException
   {
           FileInputStream fis1 = new FileInputStream("BDGC");
           FileInputStream fis2 = new FileInputStream("BDGF");
           FileInputStream fis3 = new FileInputStream("BDGAE");
           ObjectInputStream ois1 = new ObjectInputStream(fis1);
           ObjectInputStream ois2 = new ObjectInputStream(fis2);
           ObjectInputStream ois3 = new ObjectInputStream(fis3);
           gc = (GestaoContribuintes) ois1.readObject();
           gf = (GestaoFaturas) ois2.readObject();
           ga = (GestaoAtivEconomicas) ois3.readObject();
           ois1.close(); ois2.close(); ois3.close();
   }  
   
   /**
    * Carrega lista de Atividades Económicas disponível.
    */
   public void carregaListaAE()
   {
       AtivEconomica a1 = new AtivEconomica (1, "Despesas Gerais e Familiares", 0.15);
       AtivEconomica a2 = new AtivEconomica (2, "Saúde", 0.30);
       AtivEconomica a3 = new AtivEconomica (3, "Educação", 0.2);
       AtivEconomica a4 = new AtivEconomica (4, "Imóveis", 0.22);
       AtivEconomica a5 = new AtivEconomica (5, "Lares", 0.15);
       AtivEconomica a6 = new AtivEconomica (6, "Manutenção e Reparação de Veículos Automóveis", 0.15);
       AtivEconomica a7 = new AtivEconomica (7, "Manutenção e Reparação de Motociclos", 0.15);
       AtivEconomica a8 = new AtivEconomica (8, "Alojamento, Restauração e Similares", 0.12);
       AtivEconomica a9 = new AtivEconomica (9, "Salões de Cabeleireiro e Institutos de Beleza", 0.10);
       AtivEconomica a10 = new AtivEconomica (10, "Atividades Veterinárias", 0.2);
       AtivEconomica a11 = new AtivEconomica (11, "Transportes Públicos", 0.30);
        
       ga.addAtividade(a1);
       ga.addAtividade(a2);
       ga.addAtividade(a3);
       ga.addAtividade(a4);
       ga.addAtividade(a5);
       ga.addAtividade(a6);
       ga.addAtividade(a7);
       ga.addAtividade(a8);
       ga.addAtividade(a9);
       ga.addAtividade(a10);
       ga.addAtividade(a11);
   }  
   
   /**
    * Carrega uma lista de Contribuintes Individuais.
    */
   public void carregaListaCI()
   {
       ArrayList<String> aes = new ArrayList<>();
       aes.add("Despesas Gerais e Familiares");
       aes.add("Saúde");
       aes.add("Educação");
       aes.add("Imóveis");
       aes.add("Lares");
       aes.add("Manutenção e Reparação de Veículos Automóveis");
       aes.add("Manutenção e Reparação de Motociclos");
       aes.add("Alojamento, Restauração e Similares");
       aes.add("Salões de Cabeleireiro e Institutos de Beleza");
       aes.add("Atividades Veterinárias");
       aes.add("Transportes Públicos");
       
       
       ArrayList<String> dep1 = new ArrayList<>();
       dep1.add("2105555");
       CIndividual ci1 = new CIndividual (1, dep1, aes, 335.0, "2101111", "2101111@jfpoo.com", "Ines Monteiro", "Lisboa", "1111");
        
       ArrayList<String> dep2 = new ArrayList<>();
       dep2.add("2106666");
       dep2.add("2107777");
       CIndividual ci2 = new CIndividual (2, dep2, aes, 385.0, "2102222", "2102222@jfpoo.com", "João Fonseca", "Porto", "2222");
        
       ArrayList<String> dep3 = new ArrayList<>();
       CIndividual ci3 = new CIndividual (0, dep3, aes, 15.0, "2103333", "2103333@jfpoo.com", "Miguel Teixeira", "Braga", "3333");
        
       ArrayList<String> dep4 = new ArrayList<>();
       dep4.add("2108888");
       dep4.add("2109999");
       dep4.add("2101010");
       dep4.add("2101212");
       dep4.add("2101313");
       CIndividual ci4 = new CIndividual (5, dep4, aes, 338.5, "2104444", "2104444@jfpoo.com", "Maria Gonçalves", "Bragança", "4444");
        
       ArrayList<String> dep5 = new ArrayList<>();
       CIndividual ci5 = new CIndividual (0, dep5, aes, 60.0, "2105555", "2105555@jfpoo.com.com", "Sara Abreu", "Lisboa", "5555");
       
       ArrayList<String> dep6 = new ArrayList<>();
       CIndividual ci6 = new CIndividual (0, dep5, aes, 35.0, "2106666", "2106666@jfpoo.com.com", "Pedro Dias", "Porto", "6666");
       
       ArrayList<String> dep7 = new ArrayList<>();
       CIndividual ci7 = new CIndividual (0, dep5, aes, 210.0, "2107777", "2107777@jfpoo.com.com", "Ana Silva", "Porto", "7777");
       
       ArrayList<String> dep8 = new ArrayList<>();
       CIndividual ci8 = new CIndividual (0, dep5, aes, 34.0, "2108888", "2108888@jfpoo.com.com", "Simone Costa", "Bragança", "8888");
       
       ArrayList<String> dep9 = new ArrayList<>();
       CIndividual ci9 = new CIndividual (0, dep5, aes, 0.0, "2109999", "2109999@jfpoo.com.com", "Henrique Ferreira", "Bragança", "9999");
       
       ArrayList<String> dep10 = new ArrayList<>();
       CIndividual ci10 = new CIndividual (0, dep5, aes, 110.0, "2101010", "2101010@jfpoo.com.com", "Isabel Martins", "Bragança", "1010");
       
       ArrayList<String> dep12 = new ArrayList<>();
       CIndividual ci12 = new CIndividual (0, dep5, aes, 60.0, "2101212", "2101212@jfpoo.com.com", "Carolina Guimarães", "Bragança", "1212");
       
       ArrayList<String> dep13 = new ArrayList<>();
       CIndividual ci13 = new CIndividual (0, dep5, aes, 0.0, "2101313", "2101313@jfpoo.com.com", "Marco Matias", "Bragança", "1313");
       
       gc.addContribuinte(ci1);
       gc.addContribuinte(ci2);
       gc.addContribuinte(ci3);
       gc.addContribuinte(ci4);
       gc.addContribuinte(ci5);
       gc.addContribuinte(ci6);
       gc.addContribuinte(ci7);
       gc.addContribuinte(ci8);
       gc.addContribuinte(ci9);
       gc.addContribuinte(ci10);
       gc.addContribuinte(ci12);
       gc.addContribuinte(ci13);
   }
    
   /**
    * Carrega umalLista de Contribuintes Empresariais.
    */
   public void carregaListaCE()
   {
       ArrayList<String> cod1 = new ArrayList<>();
       cod1.add("Saúde");
       CEmpresa ce1 = new CEmpresa (cod1, 2, 170.0, "5011111", "5011111@jfpoo.com", "Clinica Freitas", "Braga", "1111");
        
       ArrayList<String> cod2 = new ArrayList<>();
       cod2.add("Despesas Gerais e Familiares");
       CEmpresa ce2 = new CEmpresa (cod2, 0, 82.5, "5012222", "5012222@jfpoo.com", "Loja Tem Tudo", "Lisboa", "2222");
        
       ArrayList<String> cod3 = new ArrayList<>();
       cod3.add("Transportes Públicos");
       cod3.add("Educação");
       CEmpresa ce3 = new CEmpresa (cod3, 1, 935.0,"5013333", "5013333@jfpoo.com", "Colégio dos Pequeninos", "Leiria", "3333");
        
       ArrayList<String> cod4 = new ArrayList<>();
       cod4.add("Despesas Gerais e Familiares");
       cod4.add("Saúde");
       cod4.add("Educação");
       CEmpresa ce4 = new CEmpresa (cod4, 3, 80.0, "5014444", "5014444@jfpoo.com", "Ginasio em Forma", "Beja", "4444");
        
       ArrayList<String> cod5 = new ArrayList<>();
       cod5.add("Salões de Cabeleireiro e Institutos de Beleza");
       CEmpresa ce5 = new CEmpresa (cod5, 2, 90.0, "5015555", "5015555@jfpoo.com", "JF Cabeleireiro", "Porto", "5555");
       
       ArrayList<String> cod6 = new ArrayList<>();
       cod6.add("Manutenção e Reparação de Veículos Automóveis");
       CEmpresa ce6 = new CEmpresa (cod6, 5, 225.0, "5016666", "5016666@jfpoo.com", "Oficina do Norte", "Bragança", "6666");
       
       gc.addContribuinte(ce1);
       gc.addContribuinte(ce2);
       gc.addContribuinte(ce3);
       gc.addContribuinte(ce4);
       gc.addContribuinte(ce5);
       gc.addContribuinte(ce6);
   }  
   
   /**
    * Carrega uma lista de Administradores.
    */
   public void carregaListaAdmin()
   {
       CAdmin ca1 = new CAdmin ("0000", "0000@admin.com", "Ricardo Afonso", "Admin-Braga", "0000");
       gc.addContribuinte(ca1);
   }
   
   /**
    * Carrega uma lista de Faturas.
    */
   public void carregaFaturas()
   {
       ArrayList<String> est1 = new ArrayList<>();
       est1.add("Saúde");
       Fatura f1 = new Fatura ("1.1", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2101111", "Dermatologia", "Saúde", 60.0, 6.0, "inserido", est1);
       
       ArrayList<String> est2 = new ArrayList<>();
       est2.add("Saúde");
       Fatura f2 = new Fatura ("1.2", "5011111", "Clinica Freitas", LocalDate.now().plusDays(1), "2102222", "Oftalmologia", "Saúde", 40.0, 4.0, "inserido", est2);
       
       ArrayList<String> est3 = new ArrayList<>();
       est3.add("Saúde");
       Fatura f3 = new Fatura ("1.3", "501111", "Clinica Freitas", LocalDate.now().plusDays(2), "2105555", "Clinica Geral", "Saúde", 35.0, 3.5, "inserido", est3);
       
       ArrayList<String> est4 = new ArrayList<>();
       est4.add("Saúde");
       Fatura f4 = new Fatura ("1.4", "501111", "Clinica Freitas", LocalDate.now().plusDays(3), "2106666", "Dentista", "Saúde", 35.0, 3.5, "inserido", est4);
       
       ArrayList<String> est5 = new ArrayList<>();
       est5.add("Despesas Gerais e Familiares");
       Fatura f5 = new Fatura ("2.1", "5012222", "Loja tem Tudo", LocalDate.now().plusDays(2), "2103333", "Detergentes", "Despesas Gerais e Familiares", 15.0, 1.5, "inserido", est5);
       
       ArrayList<String> est6 = new ArrayList<>();
       est6.add("Despesas Gerais e Familiares");
       Fatura f6 = new Fatura ("2.2", "5012222", "Loja tem Tudo", LocalDate.now().plusDays(-4), "2104444", "Mercearia", "Despesas Gerais e Familiares", 23.5, 2.3, "inserido", est6);
       
       ArrayList<String> est7 = new ArrayList<>();
       est7.add("Despesas Gerais e Familiares");
       Fatura f7 = new Fatura ("2.3", "5012222", "Loja tem Tudo", LocalDate.now().plusDays(4), "2107777", "Detergentes", "Despesas Gerais e Familiares", 10.0, 1.0, "inserido", est7);
       
       ArrayList<String> est8 = new ArrayList<>();
       est8.add("Despesas Gerais e Familiares");
       Fatura f8 = new Fatura ("2.4", "5012222", "Loja tem Tudo", LocalDate.now().plusDays(1), "2108888", "Mercearia", "Despesas Gerais e Familiares", 34.0, 3.4, "inserido", est8);
       
       ArrayList<String> est9 = new ArrayList<>();
       est9.add("ND");
       Fatura f9 = new Fatura ("3.1", "5013333", "Colégio dos Pequeninos", LocalDate.now().plusDays(1), "2104444", "Inscrição 2º Ano", "ND", 240.0, 24.0, "pendente", est9);
       
       ArrayList<String> est10 = new ArrayList<>();
       est10.add("ND");
       Fatura f10 = new Fatura ("3.2", "5013333", "Colégio dos Pequeninos", LocalDate.now().plusDays(2), "2104444", "Transporte Colégio", "ND", 75.0, 7.5, "pendente", est10);
       
       ArrayList<String> est11 = new ArrayList<>();
       est11.add("ND");
       Fatura f11 = new Fatura ("3.3", "5013333", "Colégio dos Pequeninos", LocalDate.now().plusDays(3), "2101111", "Inscrição 1º Ano", "ND", 200.0, 20.0, "pendente", est11);
       
       ArrayList<String> est12 = new ArrayList<>();
       est12.add("ND");
       Fatura f12 = new Fatura ("3.4", "5013333", "Colégio dos Pequeninos", LocalDate.now().plusDays(4), "2101111", "Transporte Colégio", "ND", 75.0, 7.5, "pendente", est12);
       
       ArrayList<String> est13 = new ArrayList<>();
       est13.add("ND");
       Fatura f13 = new Fatura ("3.5", "5013333", "Colégio dos Pequeninos", LocalDate.now().plusDays(4), "2102222", "Inscrição 3º Ano", "ND", 270.0, 27.0, "pendente", est13);
       
       ArrayList<String> est14 = new ArrayList<>();
       est14.add("ND");
       Fatura f14 = new Fatura ("3.6", "5013333", "Colégio dos Pequeninos", LocalDate.now().plusDays(3), "2102222", "Transporte Colégio", "ND", 75.0, 7.5, "pendente", est14);
       
       ArrayList<String> est15 = new ArrayList<>();
       est15.add("ND");
       Fatura f15 = new Fatura ("4.1", "5014444", "Ginásio em Forma", LocalDate.now().plusDays(4), "2101010", "Mensalidade Horário Completo", "ND", 50.0, 5.5, "pendente", est15);
       
       ArrayList<String> est16 = new ArrayList<>();
       est16.add("ND");
       Fatura f16 = new Fatura ("4.2", "5014444", "Ginásio em Forma", LocalDate.now().plusDays(-3), "2101212", "Mensalidade Horário FimSemana", "ND", 30.0, 5.5, "pendente", est16);
       
       ArrayList<String> est17 = new ArrayList<>();
       est17.add("Salões de Cabeleireiro e Institutos de Beleza");
       Fatura f17 = new Fatura ("5.1", "5015555", "JF Cabeleireiro", LocalDate.now().plusDays(-10), "2101212", "Corte", "Salões de Cabeleireiro e Institutos de Beleza", 30.0, 3.0, "inserido", est17);
       
       ArrayList<String> est18 = new ArrayList<>();
       est18.add("Salões de Cabeleireiro e Institutos de Beleza");
       Fatura f18 = new Fatura ("5.2", "5015555", "JF Cabeleireiro", LocalDate.now().plusDays(-3), "2101010", "Coloração", "Salões de Cabeleireiro e Institutos de Beleza", 60.0, 6.0, "inserido", est18);
       
       ArrayList<String> est19 = new ArrayList<>();
       est19.add("Manutenção e Reparação de Veículos Automóveis");
       Fatura f19 = new Fatura ("6.1", "5016666", "Oficina do Norte", LocalDate.now().plusDays(0), "2105555", "Oleo", "Manutenção e Reparação de Veículos Automóveis", 25.0, 2.5, "inserido", est19);
       
       ArrayList<String> est20 = new ArrayList<>();
       est20.add("Manutenção e Reparação de Veículos Automóveis");
       Fatura f20 = new Fatura ("6.2", "5016666", "Oficina do Norte", LocalDate.now().plusDays(3), "2107777", "Pneus", "Manutenção e Reparação de Veículos Automóveis", 200.0, 20.0, "inserido", est20);
       
       ArrayList<String> est21 = new ArrayList<>();
       est21.add("Saúde");
       Fatura f21 = new Fatura ("1.21", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2109999", "Oftalmologia", "Saúde", 75.0, 7.0, "inserido", est21);
       
       ArrayList<String> est22 = new ArrayList<>();
       est22.add("Saúde");
       Fatura f22 = new Fatura ("1.22", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2101010", "Ressonancia Magnetica", "Saúde", 350.0, 10.0, "inserido", est22);
       
       ArrayList<String> est23 = new ArrayList<>();
       est23.add("Saúde");
       Fatura f23 = new Fatura ("1.23", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2101212", "Rx", "Saúde", 40.0, 4.0, "inserido", est23);
       
       ArrayList<String> est24 = new ArrayList<>();
       est24.add("Saúde");
       Fatura f24 = new Fatura ("1.24", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2101313", "Ecografia", "Saúde", 55.0, 5.5, "inserido", est24);
       
       ArrayList<String> est25 = new ArrayList<>();
       est25.add("Saúde");
       Fatura f25 = new Fatura ("1.25", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2108888", "Psicologia", "Saúde", 40.0, 4.0, "inserido", est25);
       
       ArrayList<String> est26 = new ArrayList<>();
       est26.add("Saúde");
       Fatura f26 = new Fatura ("1.26", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2107777", "Analises Clinicas", "Saúde", 27.0, 2.7, "inserido", est26);
       
       ArrayList<String> est27 = new ArrayList<>();
       est27.add("Saúde");
       Fatura f27 = new Fatura ("1.27", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2106666", "Neurologia", "Saúde", 100.0, 12.7, "inserido", est27);
       
       ArrayList<String> est28 = new ArrayList<>();
       est28.add("Saúde");
       Fatura f28 = new Fatura ("1.28", "5011111", "Clinica Freitas", LocalDate.now().plusDays(0), "2105555", "Ortopedia", "Saúde", 80.0, 8.7, "inserido", est28);
       
       ArrayList<String> est29 = new ArrayList<>();
       est29.add("ND");
       Fatura f29 = new Fatura ("4.3", "5014444", "Ginásio em Forma", LocalDate.now().plusDays(4), "2101111", "Mensalidade Horário Completo", "ND", 50.0, 5.5, "pendente", est29);
       
       ArrayList<String> est30 = new ArrayList<>();
       est30.add("ND");
       Fatura f30 = new Fatura ("4.4", "5014444", "Ginásio em Forma", LocalDate.now().plusDays(-3), "2102222", "Mensalidade Horário FimSemana", "ND", 30.0, 5.5, "pendente", est30);
       
       ArrayList<String> est31 = new ArrayList<>();
       est31.add("ND");
       Fatura f31 = new Fatura ("4.5", "5014444", "Ginásio em Forma", LocalDate.now().plusDays(4), "2103333", "Mensalidade Horário Completo", "ND", 50.0, 5.5, "pendente", est31);
       
       ArrayList<String> est32 = new ArrayList<>();
       est32.add("ND");
       Fatura f32 = new Fatura ("4.6", "5014444", "Ginásio em Forma", LocalDate.now().plusDays(-3), "2104444", "Mensalidade Horário FimSemana", "ND", 30.0, 5.5, "pendente", est32);
       
       ArrayList<String> est33 = new ArrayList<>();
       est33.add("Salões de Cabeleireiro e Institutos de Beleza");
       Fatura f33 = new Fatura ("5.1", "5015555", "JF Cabeleireiro", LocalDate.now().plusDays(-10), "2105555", "Corte", "Salões de Cabeleireiro e Institutos de Beleza", 30.0, 3.0, "inserido", est33);
       
       ArrayList<String> est34 = new ArrayList<>();
       est34.add("Salões de Cabeleireiro e Institutos de Beleza");
       Fatura f34 = new Fatura ("5.2", "5015555", "JF Cabeleireiro", LocalDate.now().plusDays(-3), "2106666", "Coloração", "Salões de Cabeleireiro e Institutos de Beleza", 60.0, 6.0, "inserido", est34);
       
       ArrayList<String> est35 = new ArrayList<>();
       est35.add("Saúde");
       Fatura f35 = new Fatura ("1.35", "5011111", "Clinica Freitas", LocalDate.now().plusDays(-1), "2101010", "Rx", "Saúde", 32.0, 3.0, "inserido", est35);
       
       ArrayList<String> est36 = new ArrayList<>();
       est36.add("Saúde");
       Fatura f36 = new Fatura ("1.36", "5011111", "Clinica Freitas", LocalDate.now().plusDays(-2), "2101010", "Ecografia", "Saúde", 51.0, 3.0, "inserido", est36);
       
       ArrayList<String> est37 = new ArrayList<>();
       est37.add("Saúde");
       Fatura f37 = new Fatura ("1.37", "5011111", "Clinica Freitas", LocalDate.now().plusDays(2), "2101010", "Analises Clinicas", "Saúde", 33.0, 3.0, "inserido", est37);
       
       ArrayList<String> est38 = new ArrayList<>();
       est38.add("Saúde");
       Fatura f38 = new Fatura ("1.38", "5011111", "Clinica Freitas", LocalDate.now().plusDays(3), "2101010", "Reumatologia", "Saúde", 75.0, 7.0, "inserido", est38);
       
       ArrayList<String> est39 = new ArrayList<>();
       est39.add("Saúde");
       Fatura f39 = new Fatura ("1.39", "5011111", "Clinica Freitas", LocalDate.now().plusDays(2), "2101010", "Rx", "Saúde", 34.0, 3.0, "inserido", est39);
       
       ArrayList<String> est40 = new ArrayList<>();
       est40.add("Saúde");
       Fatura f40 = new Fatura ("1.40", "5011111", "Clinica Freitas", LocalDate.now().plusDays(1), "2101010", "Ecografia", "Saúde", 54.0, 5.6, "inserido", est40);
       
       gf.addFatura(f1);
       gf.addFatura(f2);
       gf.addFatura(f3);
       gf.addFatura(f4);
       gf.addFatura(f5);
       gf.addFatura(f6);
       gf.addFatura(f7);
       gf.addFatura(f8);
       gf.addFatura(f9);
       gf.addFatura(f10);
       gf.addFatura(f11);
       gf.addFatura(f12);
       gf.addFatura(f13);
       gf.addFatura(f14);
       gf.addFatura(f15);
       gf.addFatura(f16);
       gf.addFatura(f17);
       gf.addFatura(f18);
       gf.addFatura(f19);
       gf.addFatura(f20);
       gf.addFatura(f21);
       gf.addFatura(f22);
       gf.addFatura(f23);
       gf.addFatura(f24);
       gf.addFatura(f25);
       gf.addFatura(f26);
       gf.addFatura(f27);
       gf.addFatura(f28);
       gf.addFatura(f29);
       gf.addFatura(f30);
       gf.addFatura(f31);
       gf.addFatura(f32);
       gf.addFatura(f33);
       gf.addFatura(f34);
       gf.addFatura(f35);
       gf.addFatura(f36);
       gf.addFatura(f37);
       gf.addFatura(f38);
       gf.addFatura(f39);
       gf.addFatura(f40);
   }  
} 
        