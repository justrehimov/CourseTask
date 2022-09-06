package com.example.coursetaskk.dto.response;

import com.example.coursetaskk.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDtoResponse {
    private Long id;
    private String name;

    public StatusDtoResponse(Status status){
        this.id = status.getId();
        this.name = status.getName();
    }
}
