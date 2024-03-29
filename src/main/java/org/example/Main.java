package org.example;

import org.example.domain.User;
import org.example.exceptions.InvalidUserException;
import org.example.service.UserService;

import java.awt.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opt = 0;
        String ft = "";

        while (opt != 4){
            printMenu();
            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 -> {
                    registerUser();
                }
                case 2 -> {
                    listUsers();
                }
                case 3 -> {
                    filterUser();
                }
            }
        }

        sc.close();
    }

    private static void printMenu(){
        System.out.println(
                "--------------------------" + "\n"
                + "Menu Principal" + "\n"
                + "1 - Cadastrar o usuário" + "\n"
                + "2 - Listar todos usuários cadastrados" + "\n"
                + "3 - Pesquisar usuário por nome ou idade ou email" + "\n"
                + "4 - Sair"
                //+ "--------------------------" + "\n"
        );
    }

    private static void registerUser(){
        try {
            UserService.register();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch(InvalidUserException e){
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }


    private static void listUsers(){
        List<User> users = UserService.getAllUsers();

        IntStream.range(0, users.size())
                .forEach(pos -> {
                    User user = users.get(pos);
                    System.out.println((pos+1) + " - " + user.getName());
                });
    }

    private static void filterUser() {

        System.out.println("Digite o nome que deseja buscar: ");
        Scanner sc = new Scanner(System.in);
        String searchTerm = sc.nextLine();

        List<User> users = UserService.getAllUsers();

        List<User> filteredUsers = users.stream()
                .filter(user -> user.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                .sorted((user1, user2) -> user1.getName().compareToIgnoreCase(user2.getName()))
                .toList();

        if (filteredUsers.isEmpty()) {
            System.out.println("Nenhum usuário encontrado com o termo: " + searchTerm);
        } else {
            System.out.println("Usuários encontrados com o termo \"" + searchTerm + "\":");
            for (User user : filteredUsers) {
                System.out.println(user);
            }
        }
    }

}

//     System.out.println("--------------------------");
//     System.out.println("Menu Principal");
//     System.out.println("1 - Cadastrar o usuário");
//     System.out.println("2 - Listar todos usuários cadastrados");
//     System.out.println("3 - Cadastrar nova pergunta no formulário");
//     System.out.println("4 - Deletar pergunta do formulário");
//     System.out.println("5 - Pesquisar usuário por nome ou idade ou email");

//        File file = new File("src\\main\\java\\org\\example\\formulario.txt");
//        try (FileReader fileReader = new FileReader(file);
//             BufferedReader br = new BufferedReader(fileReader))
//        {
//            String linha;
//            while ((linha = br.readLine()) != null){
//                System.out.println(linha);
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }