package com.blog.blog.entity;


import com.blog.blog.utils.DateUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author tangzhiqiang
 */
//@Document(collection = "topics")
@Getter
@Setter
@ToString
@Entity
@Table(name="topic")
//public class Topic extends BaseModel<String> {
public class Topic extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String thumbURL;
//    @Column
//    private List<String> tags;
    @Column
    private String contentMD;
    @Column
    private String contentHTML;
    @Column
    private Long authorId;

    @Column
    private String authorName;

    /**
     * 置頂帖
     */
    @Column
    private boolean top=false;

    /**
     * 精华帖
     */
    @Column
    private boolean good=false;

    /**
     * 被锁定主题
     */
    @Column
    private boolean islocked=false;
    @Column
    private int replyCount=0;
    @Column
    private int visitCount=0;
    @Column
    private int collectCount=0;
    @Column
    private Long lastReplyId;
    @Column
    private String lastReplyAt;
    @Column
    private boolean contentIsHTML;
    @Column
    private boolean deleted=false;
    @Column
    private Long catId;
    @Column
    private String catName;
    @Column
    private String catDir;
    @Column
    private Set<Long> collectedUsers;
    @Column
    private Set<Long> likedUsers;
//***************************
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


    public String getCreateAtFormatted() {
        return null==getCreateAt()?null: DateFormatUtils.format(getCreateAt(),"yyyy-MM-dd HH:mm:ss");
    }

    public String getUpdateAtFormatted() {
        return null==getUpdateAt()?null:DateFormatUtils.format(getUpdateAt(),"yyyy-MM-dd HH:mm:ss");
    }

    public String getFriendlyTime() {
        return DateUtils.getFriendlyTime(getCreateAt());
    }

    //***********************

  /*  @Override
    public boolean isNew() {
        return getId()==null;
    }
*/

}
