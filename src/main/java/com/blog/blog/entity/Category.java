package com.blog.blog.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author tangzhiqiang
 */
//@Document(collection = "category")
@Getter
@Setter
@ToString
@Entity
@Table(name="category")
//public class Category extends BaseModel<String> {
public class Category extends BaseModel {
    @Column
    private String catName;
    @Column
    private String catDir;
    @Column
    private String catDesc;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


   /* @Override
    public boolean isNew() {
        return getId()==null;
    }
*/


}
