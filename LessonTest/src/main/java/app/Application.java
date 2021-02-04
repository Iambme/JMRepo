package app;

import app.config.DataBaseConfig;
import app.config.WebConfig;
import app.model.MyUser;
import app.service.MyUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Application implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        AnnotationConfigApplicationContext dataContext = new AnnotationConfigApplicationContext(DataBaseConfig.class);

        MyUserService userService = dataContext.getBean(MyUserService.class);
        userService.saveMyUser(new MyUser("Ivan", "IvanLogin", "Ivan123"));
        userService.saveMyUser(new MyUser("Maria", "MariaLogin", "Maria123"));


        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
    }
}
