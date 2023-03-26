package no.accelerate.springwebpreswagger.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import no.accelerate.springwebpreswagger.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("No user is currently logged in.");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            return false;
        }
        return true;
    }
}
