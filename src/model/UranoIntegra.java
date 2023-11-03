package model;

import javax.naming.SizeLimitExceededException;
import java.io.*;
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
    public void setCodigo(int codigo) throws SizeLimitExceededException
    {
        if (String.valueOf(codigo).length() > 6)
            throw new SizeLimitExceededException("Código passou do tamanho limite em Urano Integra! (Máx. 6).");

        super.setCodigo(codigo);
    }

    @Override
    public void setTipo(String tipo)
    {
        char primeiroDigito = Character.toUpperCase(tipo.charAt(0));

        if (primeiroDigito != '0' && primeiroDigito != '6')
        {
            throw new IllegalArgumentException("Não corresponde ao tipo em Urano Integra! (Apenas 0 ou 6).");
        }
        else
            super.setTipo(Character.toString(primeiroDigito));
    }

    @Override
    public void setDescricao(String descricao) throws SizeLimitExceededException {
        if (descricao.length() > 20)
            throw new SizeLimitExceededException("Descrição passou do tamanho limite em Urano Integra! (Máx. 20).");

        while (descricao.length() < 20)
            descricao += " ";

        super.setDescricao(descricao);
    }

    @Override
    public String getValor()
    {
        StringBuilder valorString = new StringBuilder(super.getValor());

        if (valorString.substring(valorString.indexOf(".")+1).length() < 2) valorString.append("0");

        while (valorString.length()-1 < 9) valorString.insert(0, "0");

        return valorString.toString().replace(".",",");
    }

    @Override
    public void setValor(double valor) throws SizeLimitExceededException
    {
        if (Double.toString(valor).substring(0, Double.toString(valor).indexOf(".")).length() > 7)
            throw new SizeLimitExceededException("Valor passou do tamanho limite em Urano Integra! (Máx. 6).");
        else if (Double.toString(valor).substring(Double.toString(valor).indexOf(".")+1).length() > 2)
            throw new NumberFormatException
                    ("Valor passou do tamanho limite de casas decimais em Urano Integra! (Máx. 2).");

        super.setValor(valor);
    }

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws IOException, SizeLimitExceededException {
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

            produtoIdentificador = getCodigo() + "*" + getTipo()
            + getDescricao() + getValor() + "00000" + "D";

            writer.write(produtoIdentificador + "\n");
        }

        writer.close();
        reader.close();
    }
}
