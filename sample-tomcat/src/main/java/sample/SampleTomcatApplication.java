package sample;

import java.io.File;

import jakarta.servlet.Servlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SampleTomcatApplication {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.setPort(PortSupplier.get());
        Context context = tomcat.addContext("", new File(".").getAbsolutePath());
        registerServlet(context, "home", new HomeServlet(), "");
        registerServlet(context, "helloWorld", new HelloWorldServlet(), "/hello");
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static void registerServlet(Context context, String servletName, Servlet servlet, String servletMapping) {
        Tomcat.addServlet(context, servletName, servlet);
        context.addServletMappingDecoded(servletMapping, servletName);
    }

}
