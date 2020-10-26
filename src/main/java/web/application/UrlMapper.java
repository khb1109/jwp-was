package web.application;

import java.util.HashMap;
import java.util.Map;

import web.application.controller.Controller;
import web.application.controller.CreateUserController;
import web.application.controller.ListController;
import web.application.controller.RootController;
import web.application.controller.UserLoginController;

public class UrlMapper {

    private final Map<String, Controller> mapper;

    private UrlMapper() {
        this.mapper = new HashMap<>();
        this.mapper.put("/user/create", CreateUserController.getInstance());
        this.mapper.put("/user/login", UserLoginController.getInstance());
        this.mapper.put("/", RootController.getInstance());
        this.mapper.put("/user/list", ListController.getInstance());
    }

    public static UrlMapper getInstance() {
        return Cache.URL_MAPPER;
    }

    public Controller getController(String url) {
        return this.mapper.get(url);
    }

    public boolean contains(String url) {
        return this.mapper.containsKey(url);
    }

    private static class Cache {

        private static final UrlMapper URL_MAPPER = new UrlMapper();
    }
}
