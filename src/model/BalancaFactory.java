package model;

import java.io.IOException;
import java.util.List;

public class BalancaFactory
{
       //filizolaSmart.exportar(filizolaSmartList, "src/main/resources/CADTXT.TXT");
       //toledoMGV6.exportar(toledoMGV6List, "src/main/resources/ITENSMGV.TXT");
       //uranoIntegra.exportar(uranoIntegraList, "src/main/resources/PRODUTOS.TXT");


    public enum TipoBalanca {FINIZOLA_SMART, TOLEDO_MGV6, URANO_INTEGRA}

    public IBalanca getBalanca (TipoBalanca tipo)
    {
        return new IBalanca()
        {
            @Override
            public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws IOException
            {
                switch (tipo.name())
                {
                    case "FINIZOLA_SMART":
                        FilizolaSmart filizolaSmart = new FilizolaSmart();
                        filizolaSmart.exportar(produtos, pastaArquivoTxt);
                        break;
                    case "TOLEDO_MGV6":
                        ToledoMGV6 toledoMGV6 = new ToledoMGV6();
                        toledoMGV6.exportar(produtos, pastaArquivoTxt);
                        break;
                    case "URANO_INTEGRA":
                        UranoIntegra uranoIntegra = new UranoIntegra();
                        uranoIntegra.exportar(produtos, pastaArquivoTxt);
                }
            }
        };
    };
}
