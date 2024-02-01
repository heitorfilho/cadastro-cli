package org.example;

import org.example.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SaveUser {

    private static final File amount = new File("src\\main\\java\\org\\example\\users\\amount.txt");

    // recebe um usuario e o serializa no formato 1-NOME.ser
    public static void save(User user){
        int am = getAmount();
        am++;
        String nameFile = user.getName().replace(" ", "").toUpperCase();
        File userFile = new File("src\\main\\java\\org\\example\\users\\" + am + "-" + nameFile + ".txt");

        String pathName = "src\\main\\java\\org\\example\\users\\" + am + "-" + nameFile + ".ser";

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(pathName)))
        {
            outputStream.writeObject(user);
            System.out.println("Objeto serializado com sucesso.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        setAmount(am);
    }

    // cadastra um usuario e ja serializa
    public static void register(){
        Scanner sc = new Scanner(System.in);

        String name;
        String email;
        int age;
        float height;

        System.out.print("\nQual seu nome completo? ");
        name = sc.nextLine();

        System.out.print("Qual seu email de contato?  ");
        email = sc.nextLine();

        System.out.print("Qual sua idade? ");
        age = sc.nextInt();

        System.out.print("Qual sua altura? ");
        height = sc.nextFloat();

        User user = new User(name, email, age, height);

        if (user != null){
            System.out.println("Usuario cadastrado com sucesso");
            SaveUser.save(user);
        }
        else{
            System.out.println("Erro no cadastro");
        }

        sc.close();
    }

    // recebe uma posicao e retorna o usuario correspondente
    // 1 - User1; 2 - User2; 3 - User3
    public static User getUser(int pos){
        File file = new File("src\\main\\java\\org\\example\\users\\");
        File[] arq = file.listFiles();
        int posFile = pos-1;

        User user;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arq[posFile]))) {
            user = (User) inputStream.readObject();
            System.out.println("Objeto deserializado com sucesso.");
            return user;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // retorna todos os usuarios
    public static List<User> getAllUsers(){
        int am = getAmount();
        List<User> users = new ArrayList<>();

        for (int i = 1; i <= am; i++) {
            users.add(getUser(i));
        }
        return users;
    }

    // usando IntStream
    public static List<User> getAllUsers2(){
        int am = getAmount() + 1;
        List<User> users = new ArrayList<>();

        return IntStream.range(1, am)
                .mapToObj(SaveUser::getUser)
                .collect(Collectors.toList());
    }

    // retorna a quantidade de users salvos
    private static int getAmount(){
        try (FileReader fr = new FileReader(amount);
             BufferedReader br = new BufferedReader(fr))
        {
            String line = br.readLine();
            return Integer.parseInt(line);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    // altera a quantidade de users salvos
    private static void setAmount(int am){
        try(FileWriter fw = new FileWriter(amount);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(Integer.toString(am));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
