package model;

public abstract class Produto
{
    private int codigo;
    private String descricao;
    private String tipo;
    private double valor;

    public String getCodigo()
    {
        return String.valueOf(codigo);
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao.toUpperCase();
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getValor()
    {
        return Double.toString(valor);
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }
}
