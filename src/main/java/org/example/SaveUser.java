package org.example;

import org.example.domain.User;

import java.io.*;

public class SaveUser {

    private static final File amount = new File("src\\main\\java\\org\\example\\users\\amount.txt");
    public static void save(User user){
        // criar um novo arquivo com o nome do user

        // salvar as informações dele: duas formas: linha linha ou serializacao

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
