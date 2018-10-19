springboot整合SpringMvc的示例说明

我们的项目是一个jar工程，那么就没有webapp，我们的静态资源该放哪里呢？

回顾我们上面看的源码，有一个叫做ResourceProperties的类，
里面就定义了静态资源的默认查找路径。

    @Configuration
    @Import(EnableWebMvcConfiguration.class)
    @EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
    public static class WebMvcAutoConfigurationAdapter
            implements WebMvcConfigurer, ResourceLoaderAware {

        public WebMvcAutoConfigurationAdapter(ResourceProperties resourceProperties,
        				WebMvcProperties mvcProperties, ListableBeanFactory beanFactory,
        				@Lazy HttpMessageConverters messageConverters,
        				ObjectProvider<ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider) {
            this.resourceProperties = resourceProperties;
            this.mvcProperties = mvcProperties;
            this.beanFactory = beanFactory;
            this.messageConverters = messageConverters;
            this.resourceHandlerRegistrationCustomizer = resourceHandlerRegistrationCustomizerProvider
                    .getIfAvailable();
        }
        ...
        @Bean
        ...

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
                return;
            }
            Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
            CacheControl cacheControl = this.resourceProperties.getCache()
                    .getCachecontrol().toHttpCacheControl();
            if (!registry.hasMappingForPattern("/webjars/**")) {
                customizeResourceHandlerRegistration(registry
                        .addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/")
                        .setCachePeriod(getSeconds(cachePeriod))
                        .setCacheControl(cacheControl));
            }
            String staticPathPattern = this.mvcProperties.getStaticPathPattern();
            if (!registry.hasMappingForPattern(staticPathPattern)) {
                customizeResourceHandlerRegistration(
                        registry.addResourceHandler(staticPathPattern)
                                .addResourceLocations(getResourceLocations(
                                        this.resourceProperties.getStaticLocations()))
                                .setCachePeriod(getSeconds(cachePeriod))
                                .setCacheControl(cacheControl));
            }
        }
    }
    
可以看到ResourceProperties.class的作用了

    @ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
    public class ResourceProperties {
    
    	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
    			"classpath:/META-INF/resources/", "classpath:/resources/",
    			"classpath:/static/", "classpath:/public/" };
    
    	/**
    	 * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
    	 * /resources/, /static/, /public/].
    	 */
    	private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
    	
    	...
    }
  
这个属性注入已经表明了静态资源的路径。

对比传统的ssm配置，需要配置拦截路径"/",即一切路径都被dispactherServlet处理，
还需要配置default servlet 这是将静态资源交给tomcat的默认servlet处理。

而这里springboot已经是一个jar项目，而非war,并没有webapp的war项目结构。
就是通过ResourceProperties.class这个属性注入，静态资源目录位置，
在WebMvcAutoConfiguration中，配置静态资源处理器。

这就是springboot整合springMvc摒弃war, 用jar的形式,同时具备web项目的处理能力

小结：springboot整合springMVC之访问静态资源。

只要静态资源放在这些目录中任何一个，SpringMVC都会帮我们处理。

我们习惯会把静态资源放在`classpath:/static/`目录下。我们创建目录，并且添加一些静态资源