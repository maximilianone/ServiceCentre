package application.controller.requestMapper;

import javax.servlet.http.HttpServletRequest;

public interface RequestMapper<T> {
    T map(HttpServletRequest request);
}
