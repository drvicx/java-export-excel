package com.example.jxls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import org.springframework.http.HttpStatus;


@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error/error-401";
            }
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            }
            if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                return "error/error-405";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error-500";
            }
        }
        return "error/error";
    }
}
