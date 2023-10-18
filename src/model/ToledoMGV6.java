package model;

import java.io.*;
import java.util.List;

public class ToledoMGV6 extends Produto implements IBalanca
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
        if (descricao.length() > 50)
            descricao = descricao.substring(0, 50);

        while (descricao.length() < 50)
            descricao += " ";

        super.setDescricao(descricao);
    }

    @Override
    public void setTipo(String tipo)
    {
        char primeiroDigito = Character.toUpperCase(tipo.charAt(0));

        if (Character.isDigit(primeiroDigito) &&
           (Character.getNumericValue(primeiroDigito) >= 0 &&
            Character.getNumericValue(primeiroDigito) <= 5))
            super.setTipo(Character.toString(primeiroDigito));
        else
        {
            System.out.println("Não corresponde ao tipo em Toledo MGV6! (Apenas números entre 0 e 5).");
            super.setTipo(null);
        }
    }

    @Override
    public String getValor()
    {
        String valorString = String.valueOf(super.getValor()).substring
                (0, super.getValor().indexOf(".") + 3).replace(".", "");

        if (valorString.length() > 6)
        {
            valorString = valorString.substring(valorString.length() - 6);
            super.setValor(Double.parseDouble(valorString.substring(0, valorString.length() -2)
                    + "." + valorString.substring(valorString.length() -2)));
        }

        while (valorString.length() < 6)
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
            setCodigo(Integer.parseInt(produto.getCodigo()));
            setDescricao(produto.getDescricao());
            setTipo(produto.getTipo());
            setValor(Double.parseDouble(produto.getValor()));

            if(getTipo() == null) continue;

            produtoIdentificador = "01" + getTipo() + getCodigo() + getValor() + "000" + getDescricao()
            + "\n000000" + "0000" + "000000" + "0" + "0" + "0000" + "000000000000" + "00000000000"
            + "0" + "0000" + "0000" + "0000" + "0000" + "0000" + "\n0000" + "000000000000" + "000000"
            + "|01|" + "                                   " + "                                   "
            +"\n000000" + "000000" + "000000" + "0000000|0000|0||";

            writer.write(produtoIdentificador + "\n");
        }

        writer.close();
        reader.close();
    }
}
