package pojos.VisitorsPurpose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pojo_VisitorsPurpose {


    /*
        {
            "id": "1",
            "visitors_purpose": "Marketing ",
            "description": "",
            "created_at": "2023-01-18 01:07:12"
        }
         */

    private String id;
    private String visitors_purpose;
    private String description;
    private String created_at;

}
