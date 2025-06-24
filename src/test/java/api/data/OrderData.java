package api.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    private Integer id;
    private Integer petId;
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private OffsetDateTime shipDate;

    private String status;
    private boolean complete;

}
