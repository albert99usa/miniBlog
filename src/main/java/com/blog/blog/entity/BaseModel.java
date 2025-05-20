package com.blog.blog.entity;


import com.blog.blog.utils.DateUtils;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
//public abstract class BaseModel<ID extends Serializable> implements Persistable<ID> {
public abstract class BaseModel {
    private static final long serialVersionUID = 1L;

   // @Id
  //  private ID id;.


    //  @Field("create_at")
    @CreatedDate
    private Date createAt;


    //@Field("update_at")
    @LastModifiedDate
    private Date updateAt;


    @Transient
    private String createAtFormatted;

    @Transient
    private String updateAtFormatted;

    @Transient
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

}
