package API;

public class DeleteOrderData {
    private Integer code;
    private String type;
    private String message;

    public DeleteOrderData() {

    }


    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
