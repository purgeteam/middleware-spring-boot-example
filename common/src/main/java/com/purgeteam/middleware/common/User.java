package com.purgeteam.middleware.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * user 实体类
 *
 * @author purgeyao
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 5231134212346077681L;

    private Integer id;

    private String name;

    private Integer age;
}
