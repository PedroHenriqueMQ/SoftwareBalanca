package model;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

public class UranoIntegra extends Produto implements IBalanca
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
    public void setTipo(String tipo)
    {
        char primeiroDigito = Character.toUpperCase(tipo.charAt(0));

        if (primeiroDigito != '0' && primeiroDigito != '6')
            System.out.println("Tipo não é válido! (Apenas 0 ou 6)");
        else
            super.setTipo(Character.toString(primeiroDigito));
    }

    @Override
    public void setDescricao(String descricao)
    {
        if (descricao.length() > 20)
            descricao = descricao.substring(0, 20);

        while (descricao.length() < 20)
            descricao += " ";

        super.setDescricao(descricao);
    }

    @Override
    public String getValor()
    {
        String valorString = String.valueOf(super.getValor()).substring
                (0, super.getValor().indexOf(".") + 3);

        if (valorString.length() > 9)
        {
            valorString = valorString.substring(valorString.length() - 9);
            super.setValor(Double.parseDouble(valorString));
        }

        while (valorString.length() < 9)
            valorString = "0" + valorString;

        return valorString;
    }


    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws IOException {
        File arquivo = new File(pastaArquivoTxt);
        FileWriter writer = new FileWriter(arquivo,true);
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));

        String produtoIdentificador;

        for (Produto produto:produtos)
        {
            produtoIdentificador = produto.getCodigo() + "*" + produto.getTipo() + produto.getDescricao()
            + produto.getValor() + "00000" + "D";

            if (reader.readLine() != null) writer.write("\n" + produtoIdentificador);
            else writer.write(produtoIdentificador);
        }

        writer.close();
        reader.close();
    }
}
