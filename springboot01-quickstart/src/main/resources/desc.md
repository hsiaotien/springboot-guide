使用springboot快速搭建项目：

    一分钟快速搭建企业级，生产级项目，spring boot 开箱即用，几乎零配置，快速搭建项目


分析springboot的自动配置原理：

在项目的启动类中，点击注解@SpringBootApplication,
可以看到该注解有三个核心的元注解：

    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan

其中第一个@SpringBootConfiguration，
继续戳，它的元注解是@Configuration。
（注解的注解称为元注解，
注解上并有@Inherited元注解，
那么元注解相当于作用在注解类的子注解类上）
由此得知启动类本质是一个配置类（所以这个类可以加@Bean声明对象），
并且也表面了一个类一般只有一个@SpringBootConfiguration，
这是与@Configuration的不同之处，后者可以配置多个。

其中第二个注解@EnableAutoConfiguration。
根据它的注释说明，这是一个自动配置的开关，
它自动配置的依据是根据引入的依赖来自动配置，
引入某个依赖，那么该jar包对应的配置就会自动配置生效。
比如引入spring-boot-starter-web启动器，
该启动器会引入一系列的jar包，包括tomcat和springmvc的，
那么tomcat和springmvc的自动配置就会生效，
所以可以看到，引入启动器，就可以开箱即用，快速搭建项目。
具体可以参考官方文档关于此注解的说明。

其中第三个注解@ComponentScan，
根据它的注释说明，它相当于一个注解扫描配置，
即 xml配置中的此node节点，<context:component-scan>，
是一个注解扫描的配置。
这个注解有个属性basePackages，
但此注解可以看到并没有指定basePackages，
那么如何确定扫描的范围呢？
可以看到文档的说明，如果没有配置这个属性，
那么就有一个默认值，即此子注解类（启动类）所在的包。
可以看到一般启动类在一个较浅层次的包中，这样设计包结构是有原因的！

以上就是springboot自动配置的基本原理。还有更细微的自动配置原理，后续补充。