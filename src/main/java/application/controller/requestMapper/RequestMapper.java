package application.controller.requestMapper;

import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;

public interface RequestMapper<T> extends RequestParameters {
    T map(HttpServletRequest request);
}
