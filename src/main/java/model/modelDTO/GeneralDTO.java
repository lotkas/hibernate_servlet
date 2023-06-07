package model.modelDTO;

import lombok.Data;

import java.util.List;

@Data
public class GeneralDTO<T> {
    private T entity;
    private String message;
    private List<T> entityList;

    public GeneralDTO(T entity, String message) {
        this.entity = entity;
        this.message = message;
    }

    public GeneralDTO(List<T> entityList) {
        this.entityList = entityList;
    }
}
