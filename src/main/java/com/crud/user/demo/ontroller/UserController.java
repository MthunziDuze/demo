package com.crud.user.demo.ontroller;

import com.crud.user.demo.model.User;
import com.crud.user.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public User getUser(@PathParam(value = "userId") long userId) {

        return new User();
    }

    @PostMapping
    public User updateUser(@RequestBody User user) throws Exception{
        try {

            return userService.updateUser(user);

        } catch (FileNotFoundException fne) {
            throw new Exception("File not found! please check if the file exists first and try again", fne.getCause());
        } catch (IOException e) {
            throw new Exception("Error in inserting data to file please try again later",e.getCause());
        }
    }

    @PutMapping
    public String createUser(@RequestBody User user) throws Exception {
        try {
            userService.addUserToFile(user);
            return "User " + user.getFirstName() + " created";

        } catch (FileNotFoundException fne) {
            throw new Exception("File not found! please check if the file exists first and try again", fne.getCause());
        } catch (IOException e) {
            throw new Exception("Error in inserting data to file please try again later",e.getCause());
        }
    }

    @DeleteMapping
    public String deleteFile() {
        if (userService.deleteFile()) {
            return "File Deleted";
        } else {
            return "Was not able to delete file, please try again later";
        }
    }
}
