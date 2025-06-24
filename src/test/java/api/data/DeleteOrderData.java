package api.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteOrderData {

    private Integer code;
    private String type;
    private String message;

}
