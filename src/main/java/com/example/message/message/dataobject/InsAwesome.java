package com.example.message.message.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ins_awesome")
@DynamicInsert
@DynamicUpdate
public class InsAwesome implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    /**
     * 帖子id
     */

    private Integer messageId;

    /**
     * 点赞人id
     */

    private Integer userId;

    /**
     * 被点赞人id
     */

    private Integer awedUserId;

    /**
     * 1是赞 2是取消赞
     */

    private Integer type;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 修改时间
     */

    private LocalDateTime updateTime;

}
