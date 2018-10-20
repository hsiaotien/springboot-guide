springboot整合mybatis.(此外为了控制台打印sql,配置了日志级别，属性值配置即可)

回顾ssm框架整合mybatis需要的配置，
mybatis-config.xml 以及applicationContext-mybatis.xml.以及applicationContext.xml

为mybatis的配置有：

其中mybatis-config.xml配置了驼峰命名开关（仅仅剩下这一个配置了）
其中applicationContext-mybatis.xml配置如下，
可以看到，
包含了dataSource数据源，
包含了configLocation配置mybatis-config.xml文件的指定,
包含了mapperLocations配置mapper.xml文件的指定，
包含了typeAliasesPackage配置了别名包扫描，
包含了basePackage配置了mapper接口的包扫描
（此外还有sqlsessionfactory的配置和指定）

    	<!-- 整合,将sqlsessionfactory对象,交给spring管理 -->
    	<bean id="sqlSessionFactory"
    		class="org.mybatis.spring.SqlSessionFactoryBean">
    		<property name="configLocation"
    			value="classpath:mybatis/mybatis-config.xml" />
    		<property name="dataSource" ref="dataSource" />
    		<!-- mapper.xml 和 别名 交给spring -->
    		<property name="mapperLocations"
    			value="classpath:mybatis/mapper/*.xml" />
    		<property name="typeAliasesPackage" value="com.dev.pojo" />
    	</bean>
    
    	<!-- 实例化接口 -->
    	<!-- <bean id="baseMapper" class="org.mybatis.spring.mapper.MapperFactoryBean" 
    				abstract="true" lazy-init="true">
    			<property name="sqlSessionFactory" ref="sqlSessionFactory" />
    		</bean> 
    		<bean id="userMapper" parent="baseMapper"> <property name="mapperInterface" 
    		value="com.dev.dao.UserMapper" /> </bean> -->
    	<!-- 按上述方法每一个接口都需要配置来实例化,非常繁琐. 将接口扫描交给spring,被扫描加载 -->
    	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    		<property name="basePackage"
    			value="com.dev.dao" />
    		<!-- optional unless there are multiple session factories defined -->
    		<property name="sqlSessionFactoryBeanName"
    			value="sqlSessionFactory" />
    	</bean>

实际上，到了springboot整合框架，大部分可以采用autoconfiguration自动配置的方式，
比如数据源（连接池）的默认配置，
比如mybatis-config.xml（就只剩下驼峰命名开关）配置文件的扫描。
都有自动配置，可以不管。

只剩下typeAliasesPackage 和basePackage的包扫描 
以及mapper.xml系列xml文件的位置指定（这个如果在通用mapper下，可以没有xml,可以不配置属性值）

typeAliasesPackage 在springboot中，用application.yml中属性值配置代替；
basePackage 在springboot中，用@Mapper注解代替 或者 @MapperScan注解扫描代替;
*mapper.xml（sql语句配置文件） 系列的配置文件位置指定，用application.yml中属性值配置代替，
当然通用mapper无xml配置sql的情况下，可以不配置,
此外，即使不是通用mapper, mybatis是支持注解配置sql的，
可以做到无需xml配置sql语句，只是复杂sql用注解非常繁琐，这里会有个简单sql的注解配置示例。

日志说明

    因为我们记录的log级别是debug，默认是显示info以上，我们需要进行配置。
    
    SpringBoot通过`logging.level.*=debug`来配置日志级别，*填写包名
    
    设置com.leyou包的日志级别为debug
    logging.level.com.leyou=debug