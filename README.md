# Meta Model Generator
实体元数据生成器，暂时只有生成对应实体字段的SQL字段（小写下划线）  

不要误会，我不是只为了拿SQL字段写的（

## Getting Started
1. 安装依赖到本地maven仓库
    ```shell script
    mvn clean install
    ```
2. 添加到项目依赖  
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
   