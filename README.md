# Meta Model Generator
实体元数据生成器，暂时只有生成对应实体字段的SQL字段（小写下划线）  

不要误会，我不是只为了拿SQL字段写的（

## Getting Started
1. 安装依赖到本地maven仓库
    ```shell script
    mvn clean install
    ```
2. 添加到自己项目依赖  

    这里给出结合lombok的例子，不需要可以把lombok相关的东西删除
    ```
    ...
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>io.github.natsusai</groupId>
            <artifactId>meta-model-generator</artifactId>
            <version>0.0.1-RELEASE</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>io.github.natsusai</groupId>
                            <artifactId>meta-model-generator</artifactId>
                            <version>0.0.1-RELEASE</version>
                        </path>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
    ...
    ````
3. 为实体添加注解
    ```java
    import javax.persistence.Entity;
    import javax.persistence.Id;
    
    @Entity
    public class Student {
       @Id
       private String id;
       private String stuName;
       private String stuId;
       private int    stuAge;
    }
    ```
4. 编译项目
    ```shell script
    mvn clean compile 
    ```
    之后就能够在编译目录中得到带有`_`后缀的实体类
    > 注意
    > 如果您也使用JPA的生成器的话，由于命名规则一样，会出现冲突。
    ```java
    import io.github.natsusai.test.Student;
    
    public abstract class Student_ {
    
     public static final String ID = "id";
     public static final String STU_NAME = "stu_name";
     public static final String STU_AGE = "stu_age";
     public static final String STU_ID = "stu_id";
    
    }
    ```
可直接参考generator-test模块  

## Reference
[Hibernate JPA 2 Metamodel Generator](https://github.com/hibernate/hibernate-orm/tree/master/tooling/metamodel-generator)  