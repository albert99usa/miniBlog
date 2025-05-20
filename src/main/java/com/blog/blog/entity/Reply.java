package com.blog.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author tangzhiqiang
 */
//@Document(collection = "replys")
@Getter
@Setter
@ToString
@Entity
@Table(name="reply")
public class Reply extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contentMD;
    @Column
    private String contentHTML;
    @Column
    private Long topicId;
   // @Column
  //  private ReplyAuthorInfo authorInfo;
    @Column
    private String replyId;
    @Column
    private boolean contentIsHTML;
    @Column
    private int thumbsUPCount;
    @Column
    private boolean deleted;


}
