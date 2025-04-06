package com.dailyDeals.dailyDeals_v6.controllers.interfaces;

import com.dailyDeals.dailyDeals_v6.models.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserControllerInterface {

    public ResponseEntity<Object> getUsers(@PathVariable int userId);
    public ResponseEntity<Object> getUsers();
    public ResponseEntity<Object> deleteUser(@PathVariable int userIdToDelete);
    public ResponseEntity<Object> updateUser(@RequestBody UserEntity user);
}
