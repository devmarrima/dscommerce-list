package com.devmarrima.dscommerce_list.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomErrorDTO {
    public List<FieldMessageDTO> erros = new ArrayList<>();

    public ValidationError(Instant timeStamp, Integer status, String error, String path) {
        super(timeStamp, status, error, path);
    }

    public void addError(String fieldName, String massege){
        erros.add(new FieldMessageDTO(fieldName, massege));
    }
    

}
