package fx;

public class XmlPropertyBeanDefinition {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-bean-config.xml");
        classPathXmlApplicationContext.refresh();

        User user = classPathXmlApplicationContext.getBean(User.class);
        classPathXmlApplicationContext.close();
    }
}
