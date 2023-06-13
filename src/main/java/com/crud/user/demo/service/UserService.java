package com.crud.user.demo.service;

import com.crud.user.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


@Service
public class UserService {
    private File file;
    private static final Logger log = LogManager.getLogger(UserService.class);
    public boolean createUserFile() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("userdata.txt", true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        if (!file.exists()) {
            return file.createNewFile();
        }else {
            return false;
        }
    }

    public void addUserToFile(User user) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("userdata.txt", false);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(user);

        fileOutputStream.close();
        objectOutputStream.close();
    }

    public User updateUser(User user) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("userdata.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


        User user1 = (User) objectInputStream.readObject();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setContactNumber(user.getContactNumber());
        fileInputStream.close();
        objectInputStream.close();

        addUserToFile(user1);
        return user1;

    }

    public boolean deleteFile() {
        file = new File("userdata.txt");
        return file.delete();
    }
}
