/**
 * Write a description of class Loja here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Loja extends Imovel
{
   /** Indica se a loja contem casa de banho */
   private boolean wc;

   /** Indica o tipo de negocio praticavel na loja */
   private String tipoNegocio;

   /** Número da porta da loja */
   private int porta;

   /** Indica se a loja em questao contem espaço para habitação */
   private boolean habitacao;

   /** Caso a loja tenha habitacao, a informacao é aqui guardada */
   private Apartamento apartamento;


   public Loja (){
       super ();
       wc = false;
       tipoNegocio = "n/a";
       porta = 0;
       habitacao = false;
       apartamento = null;
   }

   public Loja (Loja l){
       super (l);
       this.wc = l.existeWC();
       this.tipoNegocio = l.getTipoNegocio();
       this.porta = l.getNumPorta ();
       this.habitacao = l.existeHabitacao ();
       this.apartamento = l.getApartamento ();
   }

   public Loja (double areaT,boolean wc,String tipoNegocio,int porta,boolean habitacao,Apartamento apartamento,String rua,double precoP,double precoM){
         super (areaT,rua,precoP,precoM);
         this.wc = wc;
         this.tipoNegocio = tipoNegocio;
         this.porta = porta;
         this.habitacao = habitacao;
         this.apartamento = apartamento;
   }

   public boolean existeWC (){
       return wc;
   }

   public void setWC (boolean wc){
         this.wc = wc;
   }

   public String getTipoNegocio (){
       return tipoNegocio;
   }

   public void setTipoNegocio (String tipoNegocio){
         this.tipoNegocio = tipoNegocio;
   }

   public int getNumPorta (){
       return porta;
   }

   public void setNumPorta (int porta){
         this.porta = porta;
   }

   public boolean existeHabitacao (){
       return habitacao;
   }

   public void setExisteHabitacao (boolean habitacao){
         this.habitacao = habitacao;
   }

   public Apartamento getApartamento (){
         return apartamento;
   }

   public void setApartamento (Apartamento apartamento){
      this.apartamento = apartamento;
   }

   public Loja clone (){
       return new Loja(this);
   }

   public boolean equals (Object obj){
     if (obj == this){
       return true;
     }
     if (obj == null || obj.getClass () != this.getClass ()){
       return false;
     }
     Loja l = (Loja) obj;
     return l.existeWC() == wc
     && l.getTipoNegocio ().equals(tipoNegocio)
     && l.getNumPorta() == porta
     && l.existeHabitacao() == habitacao
     && l.getApartamento().equals(apartamento);
   }

   public String toString (){
     StringBuilder sb = new StringBuilder();
     if (existeWC()) sb.append("Existe casa de banho?: Sim\n");
     else sb.append("Existe casa de banho?: Não\n");
     sb.append("O tipo de Negócio é:(").append(tipoNegocio).append(")\n");
     sb.append("O número da porta é:(").append(porta).append(")\n");
     if (existeHabitacao()) sb.append("Existe habitação na loja?: Sim\n");
     else sb.append("Existe habitação na loja?: Não \n");
     sb.append("Habitação: (").append(apartamento.toString()).append(")\n");
     return sb.toString();
   }
}
