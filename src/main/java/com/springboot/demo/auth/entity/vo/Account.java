package com.springboot.demo.auth.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 账户
 *
 * @author Jim
 */
@Data
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;
    private String password;
    private String salt;

}
