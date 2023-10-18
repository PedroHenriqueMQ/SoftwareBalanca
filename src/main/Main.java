package main;


import model.BalancaFactory;
import model.IBalanca;
import model.Produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException {
        Produto produto1 = new Produto();
        produto1.setCodigo(12345);
        produto1.setDescricao("Setando");
        produto1.setTipo("p");
        produto1.setValor(8812345.6788);

        Produto produto2 = new Produto();
        produto2.setCodigo(77);
        produto2.setDescricao("Setando um título maior que 50 dígitos.");
        produto2.setTipo("5");
        produto2.setValor(881234.5688);

        Produto produto3 = new Produto();
        produto3.setCodigo(777);
        produto3.setDescricao("Setando um título");
        produto3.setTipo("6");
        produto3.setValor(12345.67);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto3);

        IBalanca balanca = new BalancaFactory().getBalanca(BalancaFactory.TipoBalanca.FINIZOLA_SMART);
        balanca.exportar(produtos, "src/main/resources/CADTXT.TXT");

        balanca = new BalancaFactory().getBalanca(BalancaFactory.TipoBalanca.TOLEDO_MGV6);
        balanca.exportar(produtos, "src/main/resources/ITENSMGV.TXT");

        balanca = new BalancaFactory().getBalanca(BalancaFactory.TipoBalanca.URANO_INTEGRA);
        balanca.exportar(produtos, "src/main/resources/PRODUTOS.TXT");
    }
}