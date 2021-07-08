package controller;

import com.google.gson.Gson;
import model.JsonResult;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "UploadFileController", value = "/api/v1/upload-file/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 50, maxFileSize = 1024 * 1024 * 50)
public class UploadFileController extends HttpServlet {
    private Gson gson = new Gson();
    private JsonResult jsonResult = new JsonResult();
    public static final String SAVE_DIRECTORY = "file-upload";

    private String getFileName(Part part) {
        String fileNameRs = null;
        String contentDis = part.getHeader("content-disposition");
        String[] items = contentDis.split(";");
        for (String item : items) {
            if (item.trim().startsWith("fileName")) {
                fileNameRs = toString().substring(item.indexOf("=") + 2, item.length() - 1);
                int i = fileNameRs.indexOf("/");
                fileNameRs = fileNameRs.substring(i + 1);


            }
        }

        return fileNameRs;
    }

    private File getFolderUploadFile() {
        String appPath = "/Users/minhthu/Documents/";
        appPath += SAVE_DIRECTORY;
        File folderUpLoad = new File(appPath);
        if (!folderUpLoad.exists()) {
            folderUpLoad.mkdirs();
        }
        return folderUpLoad;

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = null;
        List<String> listFileName = new ArrayList<>();
        try {
            // Collection<Part> partCollection = request.getParts();
            List<Part> partCollection = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());

            for (Part part : partCollection) {
                String fileName = getFileName(part);
                if (fileName != null) {
                    String filePath = getFolderUploadFile().getAbsolutePath() + File.separator + fileName;
                    part.write(filePath);
                    System.out.println(filePath);
                    listFileName.add(SAVE_DIRECTORY + "/" + fileName);
                    rs = gson.toJson(jsonResult.jsonSuccess(listFileName));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            rs = gson.toJson(jsonResult.jsonFail("Fail upload"));
        }
        response.getWriter().println(rs);
    }
}
