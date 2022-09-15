package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jakarta.servlet.Servlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SampleTomcatApplication {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        int port = PortSupplier.get();
        tomcat.setBaseDir(createTempDir(port));
        tomcat.setPort(port);
        Context context = tomcat.addContext("", new File(".").getAbsolutePath());
        registerServlet(context, HomeServlet.class, "");
        registerServlet(context, HelloWorldServlet.class, "/hello");
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static String createTempDir(int port) {
        try {
            File tempDir = Files.createTempDirectory("tomcat." + port + ".").toFile();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void registerServlet(Context context, Class<? extends Servlet> servletClass, String servletMapping) {
        String servletClassName = servletClass.getName();
        Tomcat.addServlet(context, servletClassName, servletClassName);
        context.addServletMappingDecoded(servletMapping, servletClassName);
    }

}
