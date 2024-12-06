package models;

public class Produto {
    private int id;
    private String NomeProd;
    private String Tipo;
    private String preco;
    private String VendTel;
    private String OndEcon;
    private String Disponivel;


    //Construtores

    public Produto() {
    }

    public Produto(String NomeProd, String VendTel, String preco, String OndEcon, String Tipo, String Disponivel ) {
        this.NomeProd = NomeProd;
        this.VendTel = VendTel;
        this.preco = preco;
        this.OndEcon = OndEcon;
        this.Tipo = Tipo;
        this.Disponivel = Disponivel;
    }

    public Produto(int id, String NomeProd, String VendTel, String preco, String OndEcon, String Tipo, String Disponivel ) {
        this.id = id;
        this.NomeProd = NomeProd;
        this.VendTel = VendTel;
        this.preco = preco;
        this.OndEcon = OndEcon;
        this.Tipo = Tipo;
        this.Disponivel = Disponivel;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public String getDis() {
        return Disponivel;
    }

    public void setDis(String Disponivel) {
        this.Disponivel = Disponivel;
    }

    public String getNome() {
        return NomeProd;
    }

    public void setNome(String NomeProd) {
        this.NomeProd = NomeProd;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getTel() {
        return VendTel;
    }

    public void setTel(String VendTel) {
        this.VendTel = VendTel;
    }

    public String getOndEnc() {
        return OndEcon;
    }

    public void setOndEnc(String OndEcon) {
        this.OndEcon = OndEcon;
    }

    //toString

    @Override
    public String toString() {
        return "Produto [id=" + id +", NomeProd=" + NomeProd + ", VendTel=" + VendTel +", preco=" + preco + ",  OndEcon=" + OndEcon +", Tipo=" + Tipo + ", Disponivel=" + Disponivel +"]";
    }
}