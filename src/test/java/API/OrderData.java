package API;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

public class OrderData {

    private Integer id;
    private Integer petId;
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private OffsetDateTime shipDate;

    private String status;
    private boolean complete;

    public OrderData() {

    }

    public OrderData(Integer id, Integer petId, Integer quantity, OffsetDateTime shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setShipDate(OffsetDateTime shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPetId() {
        return petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OffsetDateTime getShipDate() {
        return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public boolean isComplete() {
        return complete;
    }
}

