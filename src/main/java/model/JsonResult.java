package model;

public class JsonResult {
    private String status;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JsonResult jsonSuccess(Object data){
        return new JsonResult("Success", data);
    }

    public JsonResult jsonFail(Object data){
        return new JsonResult("Fail", data);
    }
}
