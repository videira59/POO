import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;
/**
 * Write a description of class Imovel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Imovel implements Serializable{
    /** Area total do imovel a venda*/
    private double areaT;

    /** Rua correspondente ao imovel */
    private String rua;

    /** Preço pedido pelo imovel */
    private int precoP;

    /** Preço minimo aceitado pelo vendedor */
    protected int precoM;

    /** Lista de todas as consultas*/
    private List<Consulta> consultas;

    /** Estado do imóvel (venda ou vendido) */
    private String estado;

    /** Id do imovel */
    private String id;


    public Imovel () {
        areaT = precoP = precoP =  0;
        rua = "n/a";
        consultas = new ArrayList<Consulta> ();
        estado = "n/a";
        id = "n/a";
    }

    public Imovel (Imovel i){
        this.areaT = i.getAreaT();
        this.rua = i.getRua ();
        this.precoP = i.getPrecoP ();
        this.precoM = i.getPrecoM ();
        this.consultas = i.getConsultas().stream().map(a-> {return a.clone();}).collect(Collectors.toList());
        this.estado = i.getEstado();
        this.id = i.getId();
    }

    public Imovel (double areaT,String rua,int precoP,int precoM,List<Consulta> consultas,String estado,String id){
        this.areaT = areaT;
        this.rua = rua;
        this.precoP = precoP;
        this.precoM = precoM;
        this.consultas = consultas.stream().map(i-> {return i.clone();}).collect(Collectors.toList());
        this.estado = estado;
        this.id = id;
    }

    public double getAreaT () {
        return areaT;
    }

    public void setAreaT (double areaT){
        this.areaT = areaT;
    }

    public String getRua (){
        return rua;
    }

    public void setRua (String rua){
        this.rua = rua;
    }

    public int getPrecoP (){
        return precoP;
    }

    public void setPrecoP (){
        this.precoP = precoP;
    }

    public int getPrecoM (){
        return precoM;
    }

    public void setPrecoM (){
        this.precoM = precoM;
    }

    public List<Consulta> getConsultas (){
      return consultas.stream().map(i->{return i.clone();}).collect(Collectors.toList());
    }

    public void setConsultas (List<Consulta> consultas){
      this.consultas = consultas.stream().map(i->{return i.clone();}).collect(Collectors.toList());
    }

    public String getEstado (){
      return estado;
    }

    public void setEstado(String estado){
      this.estado = estado;
    }

    public String getId(){
      return id;
    }

    public void setId(String id){
      this.id = id;
    }

    public abstract Imovel clone ();

    public boolean equals (Object obj){
      if (obj == this){
        return true;
      }
      if (obj == null || obj.getClass() != this.getClass()){
        return false;
      }
      Imovel i = (Imovel) obj;
      return i.getAreaT() == areaT
      && i.getRua().equals(rua)
      && i.getPrecoP() == precoP
      && i.getPrecoM() == precoM
      && i.getConsultas().equals(consultas)
      && i.getId().equals(id);
    }

    public String toString (){
      StringBuilder sb = new StringBuilder();
      sb.append("Rua:(").append(rua).append(")\n");
      sb.append("A area total do imóvel é:(").append(areaT).append(")\n");
      sb.append("O preço pedido pelo imóvel é:(").append(precoP).append(")\n");
      sb.append("O id do Imovel é:").append(id).append(")\n");
      //verificar se temos de add a preçoM
      return sb.toString();
    }
}
