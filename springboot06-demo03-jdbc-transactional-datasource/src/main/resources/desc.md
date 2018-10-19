springboot整合jdbc， 事物管理， 以及连接池.

引入starter-jdbc启动器，引入mysql驱动包。

springboot在这一块的整合上非常简洁，引入启动器和驱动包即可完成大部分工作。
事物管理采用声明式事物管理@Transactonal;
连接池有默认的追光者连接池(也可以引入如druid连接池，覆盖默认的连接池),
连接池参数在application.yml中配置即可，
springboot的自动配置会见属性注入，并配置Bean.

其他的一切类似于传统的配置，如事物的配置，连接池的配置都由springboot的自动配置完成。
