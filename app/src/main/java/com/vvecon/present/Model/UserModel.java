package com.vvecon.present.Model;

import androidx.appcompat.app.AppCompatActivity;

import com.vvecon.present.Core.Model;

import java.util.HashMap;

public class UserModel extends Model {
    public UserModel(AppCompatActivity this_) {
        super(this_);
        API_URL = "https://famcartapi.elit-x.co/";
        HOST = "user@famcartapi.elit-x.co";
        API_KEY = "mNkgCO77A3Rajm0PsXy7DyJzDrnF9fwGPQKB+PHdLdL9dyvw4V1KIwgBdvq8ylZ2nnA64ZbDrASuH5ZF6Febc+0lpZYyMV";
    }

    public void login(String email, String password, Model.Receiver listener)
    {
        HashMap<String, Object> data = new HashMap<>();
        data.put("email", email);
        data.put("password", password);
        callAPI("login", data, listener, "Login failed", "Failed to log in.", true);
    }

    public void signup(String name, String email, String password, Model.Receiver listener)
    {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);
        data.put("password", password);
        callAPI("signup", data, listener, "Signup failed", "Failed to sign up.", true);
    }

    public void createRoom(String room, Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("room", room);
        data.put("user_id", userId);
        callAPI("create room", data, listener, "Request failed", "failed to create a new room", true);
    }

    public void rooms(Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", userId);
        callAPI("rooms", data, listener, "Request failed", "failed to get rooms", false);
    }

    public void addMember(String email, Integer room, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("email", email);
        data.put("room", room);
        callAPI("add member", data, listener, "Request failed", "failed to add a new member", true);
    }

    public void notifications(Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", userId);
        callAPI("notifications", data, listener, "Request failed", "failed to get notifications", false);
    }

    public void todos(Integer room, Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("room", room);
        data.put("user_id", userId);
        callAPI("todos", data, listener, "Request failed", "failed to get todos", false);
    }

    public void history(Integer room, Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("room", room);
        data.put("user_id", userId);
        callAPI("history", data, listener, "Request failed", "failed to get history", false);
    }

    public void categories(Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        callAPI("categories", data, listener, "Request failed", "failed to get categories", false);
    }

    public void priorities(Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        callAPI("priorities", data, listener, "Request failed", "failed to get priorities", false);
    }

    public void createTask(String task, Integer category, Integer priority, String dueDate, String description, String cover, Integer room, Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("task", task);
        data.put("category", category);
        data.put("priority", priority);
        data.put("due_date", dueDate);
        data.put("description", description);
        data.put("cover_image", cover);
        data.put("room", room);
        data.put("user_id", userId);
        callAPI("create task", data, listener, "Request Failed", "failed to create a new task", true);
    }

    public void finishTask(Integer userId, Integer room, Integer task, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", userId);
        data.put("room", room);
        data.put("task", task);
        callAPI("close task", data, listener, "Request Failed", "failed to finish the task", true);
    }

    public void updateProfile(String profile, String name, String dob, Integer gender, String mobile, Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("profile", profile);
        data.put("name", name);
        data.put("dob", dob);
        data.put("gender", gender);
        data.put("mobile", mobile);
        data.put("user_id", userId);
        callAPI("update profile", data, listener, "Save Failed", "Failed to save profile information", true);
    }

    public void services(Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", userId);
        callAPI("services", data, listener, "Request Failed", "failed to get services", false);
    }

    public void createService(String service, String image, Integer category, String location, String discount, Integer userId, Model.Receiver listener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("service", service);
        data.put("image", image);
        data.put("category", category);
        data.put("location", location);
        data.put("discount", discount);
        data.put("user_id", userId);
        callAPI("create service", data, listener, "Request Failed", "Failed to create a new service", true);
    }
}
