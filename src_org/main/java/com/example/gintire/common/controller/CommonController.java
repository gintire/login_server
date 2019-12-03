package com.example.gintire.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CommonController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        log.error("===== Error =====");

        if ( status != null ) {
            HttpStatus statCode = HttpStatus.valueOf(Integer.valueOf(status.toString()));
            log.error("statCode ; {}",statCode);
            switch (statCode) {
                case NOT_FOUND :
                    return "404.html";
                case INTERNAL_SERVER_ERROR:
                    return "500.html";
                case FORBIDDEN:
                    return "403.html";
                case METHOD_NOT_ALLOWED:
                    return "405.html";
                default:
                    return "error.html";
            }
        }
        return "error.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
