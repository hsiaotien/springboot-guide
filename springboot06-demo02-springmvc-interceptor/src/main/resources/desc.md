springboot整合springMvc示例之添加拦截器。

回顾传统的ssm配置，需要在xml配置文件中配置拦截器bean以及拦截匹配路径.

拦截器也是我们经常需要使用的，在SpringBoot中该如何配置呢？

拦截器不是一个普通属性（不在是普通的，再application.yml中配置属性值即可），
而是一个类，所以就要用到java配置方式了。
在SpringBoot官方文档中有这么一段说明：

    If you want to keep Spring Boot MVC features and 
    you want to add additional [MVC configuration]
    (https://docs.spring.io/spring/docs/5.0.5.RELEASE/spring-framework-reference/web.html#mvc)
    (interceptors, formatters, view controllers, and other features), 
    you can add your own `@Configuration` class of type `WebMvcConfigurer` but **without** `@EnableWebMvc`.
     If you wish to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`,
     or `ExceptionHandlerExceptionResolver`, you can declare a `WebMvcRegistrationsAdapter` instance to provide such components.
    
    If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`.
    
总结：通过实现`WebMvcConfigurer`并添加`@Configuration`注解来实现自定义部分SpringMvc配置。
   

补充： 实际上不仅仅是拦截器，其他的处理器都可以通过此方式添加，
WebMvcConfigurer接口提供了各种add处理器的方法，相当于处理器都可以在此注册。

日志说明

    因为我们记录的log级别是debug，默认是显示info以上，我们需要进行配置。
    
    SpringBoot通过`logging.level.*=debug`来配置日志级别，*填写包名
    
    设置com.leyou包的日志级别为debug
    logging.level.com.leyou=debug