package model;

import javax.naming.SizeLimitExceededException;
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
    public void setCodigo(int codigo) throws SizeLimitExceededException
    {
        if (String.valueOf(codigo).length() > 6)
            throw new SizeLimitExceededException("Código passou do tamanho limite em Toledo MGV6! (Máx. 6).");

        super.setCodigo(codigo);
    }

    @Override
    public void setDescricao(String descricao) throws SizeLimitExceededException {
        if (descricao.length() > 50)
            throw new SizeLimitExceededException("Descrição passou do tamanho limite em Toledo MGV6! (Máx. 50).");

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
            throw new IllegalArgumentException("Não corresponde ao tipo em Toledo MGV6! (Apenas números entre 0 e 5).");
        }
    }

    @Override
    public String getValor()
    {
        StringBuilder valorString = new StringBuilder(super.getValor());

        if (valorString.substring(valorString.indexOf(".")+1).length() < 2) valorString.append("0");

        while (valorString.length()-1 < 6) valorString.insert(0, "0");

        return valorString.toString().replace(".","");
    }

    @Override
    public void setValor(double valor) throws SizeLimitExceededException
    {
        if (Double.toString(valor).substring(0, Double.toString(valor).indexOf(".")).length() > 4)
            throw new SizeLimitExceededException("Valor passou do tamanho limite em Toledo MGV6! (Máx. 6).");
        else if (Double.toString(valor).substring(Double.toString(valor).indexOf(".")+1).length() > 2)
            throw new NumberFormatException
                    ("Valor passou do tamanho limite de casas decimais em Toledo MGV6! (Máx. 2).");

        super.setValor(valor);
    }

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws IOException, SizeLimitExceededException
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
