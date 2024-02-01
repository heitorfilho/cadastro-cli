package org.example;

import org.example.domain.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        File file = new File("src\\main\\java\\org\\example\\formulario.txt");
        try (FileReader fileReader = new FileReader(file);
             BufferedReader br = new BufferedReader(fileReader))
        {
            String linha;
            while ((linha = br.readLine()) != null){
                System.out.println(linha);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //User user = new User("Carlos Magna", "carlos@gmail.com", 20, 176.5F);

        //SaveUser.save(user);
        //User carlos = SaveUser.getUser(1);
        //System.out.println(carlos);

        List<User> users = SaveUser.getAllUsers();

        users.forEach(u -> System.out.println(u.getName()));

        users.forEach(System.out::println);

        // criar menu

        printMenu();
    }

    private static void printMenu(){
        System.out.println(
                "--------------------------" + "\n"
                + "Menu Principal" + "\n"
                + "1 - Cadastrar o usuário" + "\n"
                + "2 - Listar todos usuários cadastrados" + "\n"
                + "3 - Cadastrar nova pergunta no formulário" + "\n"
                + "4 - Deletar pergunta do formulário" + "\n"
                + "5 - Pesquisar usuário por nome ou idade ou email" + "\n"
                + "--------------------------" + "\n"
        );
    }

//     System.out.println("--------------------------");
//     System.out.println("Menu Principal");
//     System.out.println("1 - Cadastrar o usuário");
//     System.out.println("2 - Listar todos usuários cadastrados");
//     System.out.println("3 - Cadastrar nova pergunta no formulário");
//     System.out.println("4 - Deletar pergunta do formulário");
//     System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
}