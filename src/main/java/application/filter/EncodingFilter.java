package application.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter{
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        encoding = filterConfig.getInitParameter("encoding");

        if(encoding == null){
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws ServletException, IOException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String requestEncoding = httpServletRequest.getCharacterEncoding();

        if(!encoding.equalsIgnoreCase(requestEncoding)){
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy(){
        encoding = null;
    }

}
