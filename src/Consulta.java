import java.util.GregorianCalender;

public class Consulta{

  /** Email do utilizador que fez a consulta */
  private String email;

  /** Data da consulta*/
  private GregorianCalender data;

  public Consulta (){
    email = "n/a";
    data = GregorianCalender();
  }

  public Consulta (Consulta c){
    this.email = c.getEmail();
    this.data = c.getData();
  }

  public Consulta (String email,GregorianCalender data){
    this.email = email;
    this.data = data;
  }

  public String getEmail(){
    return email;
  }

  public void setEmail (String email){
    this.email = email;
  }

  public GregorianCalender getData(){
    return data;
  }

  public void getData (GregorianCalender data){
    this.data = data;
  }

  public Consulta clone (){
    return new Consulta (this);
  }

  public boolean equals(Object obj){
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    Consulta c = (Consulta) obj;
    return c.getEmail().equals(email)
    && c.getData().equals(data);
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("O email da consulta é:").append(email).append("\n");
    sb.append("A data da consulta é:").append(data).append("\n");
  }
}
