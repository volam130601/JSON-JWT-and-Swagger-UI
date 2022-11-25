package com.metabook.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseObject {
    private Object data;
    private String message;
    private String status;
    private Integer total;
    private Integer page;

    public ResponseObject(Object data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public ResponseObject(Object data, String message, String status, Integer total) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.total = total;
    }

    public ResponseObject(Object data, String message, String status, Integer total, Integer page) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.total = total;
        this.page = page;
    }
}
