package main;


import model.Produto;

import javax.naming.SizeLimitExceededException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.Menu.menuEscolha;

public class Main
{
    public static void main(String[] args) throws IOException, SizeLimitExceededException
    {
        List<Produto> produtos = new ArrayList<>();

        menuEscolha(produtos);

        main(args);
    }
}