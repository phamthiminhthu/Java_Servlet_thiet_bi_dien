package controller;

import com.google.gson.Gson;
import model.Category;
import model.JsonResult;
import service.CategoryService;
import service_impl.CategoryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/api/v1/category/*")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService = new CategoryServiceImpl();
    private JsonResult jsonResult = new JsonResult();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String rs = null;
        if (pathInfo.equals("/find-all")) {
            try {
                List<Category> list = categoryService.findAll();
                rs = gson.toJson(jsonResult.jsonSuccess(list));
            } catch (Exception ex) {
                ex.printStackTrace();
                rs = gson.toJson(jsonResult.jsonFail("category fail"));
            }
        } else if (pathInfo.equals("/find-by-id")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try{
                Category category = categoryService.findById(id);
                rs = gson.toJson(jsonResult.jsonSuccess(category == null ? "" : category));
            }catch (Exception ex){
                ex.printStackTrace();
                rs = gson.toJson(jsonResult.jsonFail("fail"));
            }

        } else {
            response.sendError(404);
        }
        response.getWriter().println(rs);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = null;
        try{
            Category category = gson.fromJson(request.getReader(), Category.class);
            rs = gson.toJson(jsonResult.jsonSuccess(categoryService.insert(category)));
        }catch (Exception ex){
            ex.printStackTrace();
            rs = gson.toJson(jsonResult.jsonFail("Fail"));
        }
        response.getWriter().println(rs);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rs = null;
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            rs = gson.toJson(jsonResult.jsonSuccess(categoryService.delete(id)));
        }catch(Exception ex){
            ex.printStackTrace();
            rs = gson.toJson(jsonResult.jsonFail("Fail"));
        }
        resp.getWriter().println(rs);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rs = null;
        try{
            Category category = gson.fromJson(req.getReader(),Category.class);
            rs = gson.toJson(jsonResult.jsonSuccess(categoryService.update(category)));
        }catch (Exception ex){
            ex.printStackTrace();
            rs = gson.toJson(jsonResult.jsonFail("fail"));
        }
        resp.getWriter().println(rs);
    }
}
