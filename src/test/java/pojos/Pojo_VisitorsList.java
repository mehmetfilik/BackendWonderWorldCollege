package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pojo_VisitorsList {

    /*
    {
            "id": "3",
            "staff_id": null,
            "student_session_id": null,
            "source": null,
            "purpose": "Parent Teacher Meeting",
            "name": "Edward ",
            "email": null,
            "contact": "741589632",
            "id_proof": "0112",
            "no_of_people": "3",
            "date": "2021-03-02",
            "in_time": "09:45 AM",
            "out_time": "12:45 PM",
            "note": "",
            "image": "",
            "meeting_with": "",
            "created_at": "2023-01-18 01:07:16",
            "class": null,
            "section": null,
            "staff_name": null,
            "staff_surname": null,
            "staff_employee_id": null,
            "class_id": null,
            "section_id": null,
            "students_id": null,
            "admission_no": null,
            "student_firstname": null,
            "student_middlename": null,
            "student_lastname": null,
            "role_id": null
        }
     */

     private String id;
     private String staff_id;
     private String student_session_id;
     private String source;
     private String purpose;
     private String name;
     private String email;
     private String contact;
     private String id_proof;
     private String no_of_people;
     private String date;
     private String in_time;
     private String out_time;
     private String note;
     private String image;
     private String meeting_with;
     private String created_at;
     private String section;
     private String staff_name;
     private String staff_surname;
     private String staff_employee_id;
     private String class_id;
     private String section_id;
     private String students_id;
     private String admission_no;
     private String student_firstname;
     private String student_middlename;
     private String student_lastname;
     private String role_id;

}
