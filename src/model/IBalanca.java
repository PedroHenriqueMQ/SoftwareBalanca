package model;

import java.io.IOException;
import java.util.List;

public interface IBalanca
{
    void exportar(List<Produto> produtos, String pastaArquivoTxt) throws IOException;
}
