package sample;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class SampleJettyApplication {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        String port = System.getenv("PORT");
        connector.setPort((port != null) ? Integer.parseInt(port) : 8080);
        server.addConnector(connector);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("");
        context.addServlet(HomeServlet.class, "");
        context.addServlet(HelloWorldServlet.class, "/hello");
        server.setHandler(context);
        server.start();
    }

}
