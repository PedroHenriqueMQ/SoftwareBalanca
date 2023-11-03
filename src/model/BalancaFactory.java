package model;

import javax.naming.SizeLimitExceededException;
import java.io.IOException;
import java.util.List;

public class BalancaFactory
{
    public enum TipoBalanca {FINIZOLA_SMART, TOLEDO_MGV6, URANO_INTEGRA}

    public IBalanca getBalanca (TipoBalanca tipo)
    {
        return new IBalanca()
        {
            @Override
            public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws IOException, SizeLimitExceededException
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
