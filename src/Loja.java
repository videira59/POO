import java.util.List;
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

   public Loja (){
       super ();
       wc = false;
       tipoNegocio = "n/a";
       porta = 0;
   }

   public Loja (Loja l){
       super (l);
       this.wc = l.existeWC();
       this.tipoNegocio = l.getTipoNegocio();
       this.porta = l.getNumPorta ();
   }

   public Loja (boolean wc,String tipoNegocio,int porta,double areaT,String rua,int precoP,int precoM,List<Consulta> consultas,String estado,String id){
         super (areaT,rua,precoP,precoM,consultas,estado,id);
         this.wc = wc;
         this.tipoNegocio = tipoNegocio;
         this.porta = porta;
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
     && l.getNumPorta() == porta;
     }

   public String toString (){
     StringBuilder sb = new StringBuilder();
     if (existeWC()) sb.append("Existe casa de banho?: Sim\n");
     else sb.append("Existe casa de banho?: Não\n");
     sb.append("O tipo de Negócio é:(").append(tipoNegocio).append(")\n");
     sb.append("O número da porta é:(").append(porta).append(")\n");
     return sb.toString();
   }
}
