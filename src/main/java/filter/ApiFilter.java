package filter;

import model.MyConnection;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "ApiFilter", urlPatterns = {"/api/*"})
public class ApiFilter implements Filter {

    private MyConnection myConnection = new MyConnection();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            myConnection.connectDB();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        chain.doFilter(request, response);
    }
}
