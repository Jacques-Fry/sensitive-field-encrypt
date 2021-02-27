package com.jacques.sensitive.pojo;

import com.jacques.sensitive.core.annotation.SensitiveEntity;
import com.jacques.sensitive.core.annotation.SensitiveField;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户类
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 9:16
 */
@SensitiveEntity
@Table(name = "user")
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    @SensitiveField
    private String password;

    @ApiModelProperty("余额")
    @SensitiveField
    private String balance;

    public void verify() {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }

        if (StringUtils.isBlank(balance)) {
            if (!balance.matches("-?[0-9]+.？[0-9]*")) {
                throw new IllegalArgumentException("余额必须是一个数字");
            }
        }
    }

    @Override
    public String toString() {
        return "User{"
                + "id="
                + id
                + ", username='"
                + username
                + '\''
                + ", password='"
                + password
                + '\''
                + ", balance='"
                + balance
                + '\''
                + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
