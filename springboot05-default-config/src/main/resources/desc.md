在quickstart部分，
分析了springboot自动配置的基本原理。
也就是三个元注解。

但还有几个细节原理：

- 这些默认配置是在哪里定义的呢？
- 为何依赖引入就会触发配置呢？

第一个问题：
查看启动器引入的jar包，其中spring-boot-autoconfigure.jar,
这个jar包包含了大量的默认配置。
它是springboot给知名常用的框架写的默认配置类
（@Configuration 以及 @ConfigurationProperties大量的注解），
比如WebMvcAutoConfiguration类中，有@Configuration来表明
该类是一个配置类，类中有大量的@Bean来配置bean. 
通过@EnableConfigurationProperties来引入WebMvcProperties类，进行属性注入。
（回顾springboot的属性注入）
这是springboot给常用的知名框架写的自动配置类，
属性值只需要按源码的说明进行配置即可，如spring.mvc.view.prefix=xxx。
有些属性值也会有默认的值指定，如server.port=8080

第二个问题：
查看springboot提供的配置类，
比如WebMvcAutoConfiguration类中，
类上或者方法上有大量的@onditionalOn...类似的注解，
比如@ConditionalOnMissingBean这个注解，
在容器中没有某个类或者class时，那么springboot提供的配置类和Bean就生效了。
比如 @ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
这是WebMvcAutoConfiguration类上的注解，
其中servlet.class是tomcat的类，
DispatcherServlet.class, WebMvcConfigurer.class是springmvc的类。
也就是引入启动器后，就引入一系列的依赖，
starter-web,引入了tomcat和springmvc的依赖，这些jar包中有这些类，那么这个配置类就生效了。

方法上也有@ConditionalOnMissingBean

    @Bean
    @ConditionalOnMissingBean
    public InternalResourceViewResolver defaultViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(this.mvcProperties.getView().getPrefix());
        resolver.setSuffix(this.mvcProperties.getView().getSuffix());
        return resolver;
    }

这个注解说明，没有InternalResourceViewResolver这个bean,
那么配置的@Bean就生效，
会去读mvcProperties的属性。
这些属性就是在application.yml(全局唯一)中定义，如spring.mvc.view.prefix,
也说明了，通过这种定义属性值的方式，进行默认配置。

补充：理论上根据上述的原理，就可以自己写默认配置，给自己的框架写启动器了。
比如一些小众的框架，或者springboot不想写的框架，都会有自己编写启动器。
例如mybatis.  玩springboot一般就是找启动器，
先看有没有直接支持，没有看框架官方是否提供，没有提供那么看github大神有没有编写，最后实在不行，自己编写…………

以上就是springboot的默认配置原理。
结合quickstart部分的自动配置原理分析。

小结：

SpringBoot为我们提供了默认配置，而默认配置生效的条件一般有两个：

- 你引入了相关依赖
- 你自己没有配置Bean

1）启动器

所以，我们如果不想配置，只需要引入依赖即可，而依赖版本我们也不用操心，因为只要引入了SpringBoot提供的stater（启动器），就会自动管理依赖及版本了。

因此，玩SpringBoot的第一件事情，就是找启动器，SpringBoot提供了大量的默认启动器，参考课前资料中提供的《SpringBoot启动器.txt》

2）全局配置

另外，SpringBoot的默认配置，都会读取默认属性，而这些属性可以通过自定义`application.properties`文件来进行覆盖。这样虽然使用的还是默认配置，但是配置中的值改成了我们自定义的。

因此，玩SpringBoot的第二件事情，就是通过`application.properties`来覆盖默认属性值，形成自定义配置。我们需要知道SpringBoot的默认属性key，非常多，参考课前资料提供的：《SpringBoot全局属性.md》