package org.example;

import org.example.domain.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        User user = new User("Carlos Magna", "carlos@gmail.com", 20, 176.5F);

        //SaveUser.save(user);

        User carlos = SaveUser.getUser(1);
        System.out.println(carlos);
    }
}