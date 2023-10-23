package com.vvecon.present.Controller;

import androidx.appcompat.app.AppCompatActivity;

import com.vvecon.present.Core.Controller;
import com.vvecon.present.Model.UserModel;

public class UserController extends Controller {
    private final UserModel model;
    public UserController(AppCompatActivity this_)
    {
        parent = this_;
        model = new UserModel(parent);
    }

    public void login(String email, String password, Handler listener)
    {
        model.login(email, password, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void signup(String name, String email, String password, Handler listener)
    {
        model.signup(name, email, password, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void createRoom(String room, Integer userId, Handler listener) {
        model.createRoom(room, userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void rooms(Integer userId, Handler listener) {
        model.rooms(userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void addMember(String email, Integer room, Handler listener) {
        model.addMember(email, room, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void notifications(Integer userId, Handler listener) {
        model.notifications(userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void todos(Integer room, Integer userId, Handler listener) {
        model.todos(room, userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void history(Integer room, Integer userId, Handler listener) {
        model.history(room, userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void categories(Handler listener) {
        model.categories(api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void priorities(Handler listener) {
        model.priorities(api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void createTask(String task, Integer category, Integer priority, String dueDate, String description, String cover, Integer room, Integer userId, Handler listener) {
        model.createTask(task, category, priority, dueDate, description, cover, room, userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void finishTask(Integer userId, Integer room, Integer task, Handler listener) {
        model.finishTask(userId, room, task, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void updateProfile(String profile, String name, String dob, Integer gender, String mobile, Integer userId, Handler listener) {
        model.updateProfile(profile, name, dob, gender, mobile, userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void services(Integer userId, Handler listener) {
        model.services(userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }

    public void createService(String service, String image, Integer category, String location, String discount, Integer userId, Handler listener) {
        model.createService(service, image, category, location, discount, userId, api_report -> {
            if (listener != null) {
                listener.on(api_report);
            }
        });
    }
}
