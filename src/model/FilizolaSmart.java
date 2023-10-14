package model;

import java.io.*;
import java.util.List;

public class FilizolaSmart extends Produto implements IBalanca
{
    @Override
    public String getCodigo()
    {
        String codigoString = super.getCodigo();

        while (codigoString.length() < 6)
            codigoString = "0" + codigoString;

        return codigoString;
    }

    @Override
    public void setCodigo(int codigo)
    {
        if (String.valueOf(codigo).length() > 6)
            codigo = Integer.parseInt(String.valueOf(codigo).substring(0, 6));

        super.setCodigo(codigo);
    }

    @Override
    public void setDescricao(String descricao)
    {
        if (descricao.length() > 22)
            descricao = descricao.substring(0, 22);

        while (descricao.length() < 22)
            descricao += " ";

        super.setDescricao(descricao);
    }

    @Override
    public void setTipo(String tipo)
    {
        char primeiroDigito = Character.toUpperCase(tipo.charAt(0));

        if (primeiroDigito != 'U' && primeiroDigito != 'P')
            System.out.println("Tipo não é válido! (Apenas U ou P");
        else
            super.setTipo(Character.toString(primeiroDigito));
    }

    @Override
    public String getValor()
    {
        String valorString = String.valueOf(super.getValor()).substring
                (0, super.getValor().indexOf(".") + 3).replace(".", "");

        if (valorString.length() > 7)
        {
            valorString = valorString.substring(valorString.length() - 7);
            super.setValor(Double.parseDouble(valorString.substring(0, valorString.length() -2)
            + "." + valorString.substring(valorString.length() -2)));
        }

        while (valorString.length() < 7)
            valorString = "0" + valorString;

        return valorString;
    }

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws IOException
    {
        File arquivo = new File(pastaArquivoTxt);
        FileWriter writer = new FileWriter(arquivo,true);
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));

        String produtoIdentificador;

        for (Produto produto:produtos)
        {
            produtoIdentificador =
                    produto.getCodigo() + produto.getTipo() +
                            produto.getDescricao() + produto.getValor() + "000";

            if (reader.readLine() != null) writer.write("\n" + produtoIdentificador);
            else writer.write(produtoIdentificador);
        }

        writer.close();
        reader.close();
    }
}
