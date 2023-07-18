package com.tdl.tdl.entity;


import com.tdl.tdl.dto.AdminUserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profilename")
    private String profilename;

    @Column(name = "userImage")
    private String userImage;


    @Column
    @Enumerated(value = EnumType.STRING) // enum 타입을 데이터베이스에 저장할때 사용하는 애너테이션
    private UserGenderEnum gender;

    @Column
    @Enumerated(value = EnumType.STRING) // enum 타입을 데이터베이스에 저장할때 사용하는 애너테이션
    private UserRoleEnum role;

    public User(String username, String password, String profilename, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.profilename = profilename;
        this.role = role;
    }

    public void AdminUpdate(AdminUserRequestDto requestDto) {
        this.username =  requestDto.getUsername();
        this.profilename = requestDto.getProfilename();
        this.userImage = requestDto.getUserImage();
    }

}
