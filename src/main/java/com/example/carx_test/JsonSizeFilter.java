package com.example.carx_test;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JsonSizeFilter implements Filter {

    private static final int MAX_JSON_SIZE = 10 * 1024;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            if (httpRequest.getContentType() != null && httpRequest.getContentType().startsWith("application/json")) {
                int contentLength = httpRequest.getContentLength();
                if (contentLength > MAX_JSON_SIZE) {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE,
                            "JSON size exceeds the limit");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }
}
