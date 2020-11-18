package fx;

public class XmlConstructorBeanDefinition {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-bean-constructor-config.xml");
        classPathXmlApplicationContext.refresh();

        User user = classPathXmlApplicationContext.getBean(User.class);
        classPathXmlApplicationContext.close();
    }
}
