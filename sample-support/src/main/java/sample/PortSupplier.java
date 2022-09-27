package sample;

final class PortSupplier {

    private PortSupplier() {
    }

    static int get() {
        String port = System.getenv("PORT");
        return (port != null) ? Integer.parseInt(port) : 8080;
    }

}
