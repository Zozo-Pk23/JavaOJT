package scm.bulletinboard.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getRequestURI().equals(request.getContextPath() + "/login") ||
                request.getRequestURI().equals(request.getContextPath() + "/authenticate") ||
                request.getRequestURI().equals(request.getContextPath() + "/forgotPassword") ||
                request.getRequestURI().equals(request.getContextPath() + "/reset") ||
                request.getRequestURI().equals(request.getContextPath() + "/resetPasswordForm") ||
                request.getRequestURI().equals(request.getContextPath() + "/reset/password") ||
                request.getRequestURI().equals(request.getContextPath() + "/register") ||
                request.getRequestURI().equals(request.getContextPath() + "/registered")) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                response.sendRedirect(request.getContextPath() + "/posts/index");
                return false;
            }
            return true;
        }
        if (request.getRequestURI().equals(request.getContextPath() + "/authenticate")) {
            return true;
        }
        if (request.getRequestURI().equals(request.getContextPath() + "/forgotPassword")) {
            return true;
        }
        if (request.getRequestURI().equals(request.getContextPath() + "/reset")) {
            return true;
        }
        if (request.getRequestURI().equals(request.getContextPath() + "/resetPasswordForm")) {
            return true;
        }
        if (request.getRequestURI().equals(request.getContextPath() + "/reset/password")) {
            return true;
        }
        if (request.getRequestURI().equals(request.getContextPath() + "/register")) {
            return true;
        }
        if (request.getRequestURI().equals(request.getContextPath() + "/registered")) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        } else {
            return true;
        }
    }

}
