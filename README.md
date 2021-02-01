# 字段加密功能示例说明

切面切入点定向指定数据库操作层例如: **Mybatis**的Mapper、**JPA**的repository。

本功能将默认使用可逆加密算法AES进行加解密，当然各位需要不同的算法可执行修改AOP切入处的加密和解密执行逻辑。

> 主要的业务逻辑代码在 com.jacques.sensitive.annotation.encrypt 包下
>
> @SensitiveEntity 添加在实体类上标注此实体类会进行字段的加密扫描。
>
> @SensitiveField 添加到字段上标注此字段需要进行加密解密操作。
>
> 如果实体类上没有添加 @SensitiveEntity 注解将不会对此实体类进行加密扫描，也就是说没有添加 @SensitiveEntity 注解的话，那么 @SensitiveField 是无效的。

实体类添加注解的示例: 

```java
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
}
   
```

另外需要注意的是，针对不同的项目，切入点的配置需要修改为对应的需求地址。

```java
@Component
@Aspect
@Slf4j
public class SensitiveFieldAspect {

    请在这里将切入点修改为你的配置↓↓↓↓↓↓↓↓↓
    @Around(value ="execution(* com.*.*.dao.*..*(..))")
    public Object doProcess(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    }
}
```

需了解@Around注解的细节请访问 https://blog.csdn.net/sifanlook/article/details/88905217

其他细节请下载此示例去了解