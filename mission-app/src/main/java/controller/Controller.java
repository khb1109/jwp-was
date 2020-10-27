package controller;

import web.server.domain.request.HttpRequest;
import web.server.domain.response.HttpResponse;

@FunctionalInterface
public interface Controller {

    void service(HttpRequest httpRequest, HttpResponse httpResponse);
}
