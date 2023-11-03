package model;

import javax.naming.SizeLimitExceededException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu
{
    private static Produto povoaItem() throws SizeLimitExceededException
    {
        var produto = new Produto();

        System.out.print("Código: ");
        try
        {
            produto.setCodigo(Integer.parseInt(new Scanner(System.in).nextLine()));
        }
        catch (NumberFormatException e)
        {
            throw new NumberFormatException("Entrada informada não é um número inteiro.");
        }

        System.out.print("Descrição: ");
        produto.setDescricao(new Scanner(System.in).nextLine());

        System.out.print("Tipo: ");
        produto.setTipo(new Scanner(System.in).nextLine());

        System.out.print("Valor: ");
        try
        {
            produto.setValor(Double.parseDouble(new Scanner(System.in).nextLine()));
        }
        catch (NumberFormatException e)
        {
            throw new NumberFormatException("Entrada informada não é um número.");
        }

        return produto;
    }

    private static void insereItem(BalancaFactory.TipoBalanca tipoBalanca, List<Produto> produtos)
            throws SizeLimitExceededException
    {
        if (tipoBalanca.name().equals(BalancaFactory.TipoBalanca.FINIZOLA_SMART.name()))
        {
            System.out.print("FILIZOLA SMART:\n" +
                    "Código: 6 caracteres numéricos\n" +
                    "Descrição: Até 22 caracteres\n" +
                    "Tipo: 1 caracter (apenas 'U' ou 'P')\n" +
                    "Valor: 7 caracteres numéricos, com duas casas decimais\n" +
                    "\nSegundo a descrição acima, digite:\n");
        }
        else if (tipoBalanca.name().equals(BalancaFactory.TipoBalanca.TOLEDO_MGV6.name()))
        {
            System.out.print("TOLEDO MGV6:\n" +
                    "Código: 6 caracteres numéricos\n" +
                    "Descrição: 50 caracteres\n" +
                    "Tipo: 1 caracter (apenas números entre 0 e 5)\n" +
                    "Valor: 6 caracteres numéricos, com duas casas decimais\n" +
                    "\nSegundo a descrição acima, digite:\n");
        }
        else
        {
            System.out.print("URANO INTEGRA:\n" +
                    "Código: 6 caracteres numéricos\n" +
                    "Descrição: Até 20 caracteres\n" +
                    "Tipo: 1 caracter (apenas '0' ou '6')\n" +
                    "Valor: 9 caracteres numéricos, com duas casas decimais\n" +
                    "\nSegundo a descrição acima, digite:\n");
        }

        produtos.add(povoaItem());
    }

    public static void menuEscolha(List<Produto> produtos) throws SizeLimitExceededException, IOException
    {
        System.out.println("ESCOLHA UMA DAS OPÇÕES PARA ADICIONAR ITENS:\n" +
                "1 - Filizola Smart\n" +
                "2 - Toledo MGV6\n" +
                "3 - Urano Integra\n" +
                "4 - Sair");

        System.out.print("Digite um número: ");

        int escolha;

        try
        {
            escolha = new Scanner(System.in).nextInt();
        }
        catch (InputMismatchException e)
        {
            throw new InputMismatchException("Entrada informada não é um número inteiro.");
        }

        switch (escolha)
        {
            case 1:
                IBalanca balanca = new BalancaFactory().getBalanca(BalancaFactory.TipoBalanca.FINIZOLA_SMART);
                insereItem(BalancaFactory.TipoBalanca.FINIZOLA_SMART, produtos);
                balanca.exportar(produtos, "src/main/resources/CADTXT.TXT");
                break;
            case 2:
                balanca = new BalancaFactory().getBalanca(BalancaFactory.TipoBalanca.TOLEDO_MGV6);
                insereItem(BalancaFactory.TipoBalanca.TOLEDO_MGV6, produtos);
                balanca.exportar(produtos, "src/main/resources/ITENSMGV.TXT");
                break;
            case 3:
                balanca = new BalancaFactory().getBalanca(BalancaFactory.TipoBalanca.URANO_INTEGRA);
                insereItem(BalancaFactory.TipoBalanca.URANO_INTEGRA, produtos);
                balanca.exportar(produtos, "src/main/resources/PRODUTOS.TXT");
                break;
            case 4:
                System.exit(0);
            default:
                menuEscolha(produtos);
        }
    }
}
