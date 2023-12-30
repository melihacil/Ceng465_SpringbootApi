package com.distributed.MusicAppProject.DataObject.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Update_Request {

    private String username;
    private String newUserName;
}
