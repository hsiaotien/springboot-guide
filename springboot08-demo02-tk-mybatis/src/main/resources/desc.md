springboot整合通用mapper

脚下留心： @MapperScan 要使用通用mapper的，需要对应。
如果使用mybatis官方，那么用mybatis的对应注解！

通用mapper，对于pojo类的映射，pojo类名，属性名不一致都有对应的注解进行匹配！
需要指定主键，效率更高！