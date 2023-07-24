package in.reqres.models;

import lombok.Data;

@Data
public class UserData {
    private String email;
    private String password;
    private String name;
    private String job;
}