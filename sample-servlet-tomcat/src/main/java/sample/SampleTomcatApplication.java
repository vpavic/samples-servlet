package sample;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SampleTomcatApplication {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        String port = System.getenv("PORT");
        if (port != null) {
            tomcat.setPort(Integer.parseInt(port));
        }
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

    static class HomeServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.sendRedirect(resp.encodeRedirectURL("/hello"));
        }

    }

    static class HelloWorldServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.setContentType("text/plain");
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("Hello World!");
                writer.flush();
            }
        }

    }

}
