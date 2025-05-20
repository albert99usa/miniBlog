package com.blog.blog.entity;

import com.blog.blog.entity.embed.SocialInfo;
import com.blog.blog.utils.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   /* @Column(nullable = false)
    private String name;*/
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    // ALL means whatever operation we apply on User entity, will be applied on ROLE Entity.
    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();

    //-------------------------
    @CreatedDate
    private Date createAt;


    //@Field("update_at")
    @LastModifiedDate
    private Date updateAt;


    @org.springframework.data.annotation.Transient
    private String createAtFormatted;

    @org.springframework.data.annotation.Transient
    private String updateAtFormatted;

    @org.springframework.data.annotation.Transient
    private String friendlyTime;




    //---------------------


        @Column
        private String username;


        @Column(nullable = true)
        private String avatarURL;
        @Column(nullable = true)
        private boolean avatarURLByUploaded;
        @Column(nullable = true)
        private String website;
        @Column(nullable = true)
        private String location;
        @Column(nullable = true)
        private String slogan;
        @Column(nullable = true)
        private String selfDesc;

       @OneToOne
       @JoinColumn(name = "socialInfo",nullable = true)
        private SocialInfo socialInfo;

    public String getCreateAtFormatted() {
        return null==getCreateAt()?null: DateFormatUtils.format(getCreateAt(),"yyyy-MM-dd HH:mm:ss");
    }

    public String getUpdateAtFormatted() {
        return null==getUpdateAt()?null:DateFormatUtils.format(getUpdateAt(),"yyyy-MM-dd HH:mm:ss");
    }

    public String getFriendlyTime() {
        return DateUtils.getFriendlyTime(getCreateAt());
    }

}
