package com.distributed.MusicAppProject.DataObject;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
@Data
@Builder
public class Response<T> {  // Change it to a record
    private final Boolean status;
    @Getter
    private final String _message;
    private final T _payload;

    public Response(Boolean status, String message, T payload) {
        this.status = status;
        this._message = message;
        this._payload = payload;
    }
}