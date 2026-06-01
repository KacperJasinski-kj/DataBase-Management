package MODEL;

public class DetallePedido {
    private int id_detalle_pedido;
    private int id_pedido;
    private String descripcion_producto;
    private String categoria_producto;
    private int cantidad;
    private double precio_unitario;
    private double importe_linea;

    public DetallePedido(){this.categoria_producto = "HARDWARE";}


    public DetallePedido(int id_detalle_pedido, int id_pedido,
                         String descripcion_producto, String categoria_producto, int cantidad,
                         double precio_unitario, double importe_linea) {
        this.id_detalle_pedido = id_detalle_pedido;
        setId_pedido(id_pedido);
        setDescripcion_producto(descripcion_producto);
        setCategoria_producto(categoria_producto);
        setCantidad(cantidad);
        setPrecio_unitario(precio_unitario);
        setImporte_linea(importe_linea);
    }

    public DetallePedido(int id_pedido, String categoria_producto, String descripcion_producto, int cantidad, double precio_unitario, double importe_linea) {

        this(0,id_pedido,descripcion_producto,categoria_producto,cantidad,precio_unitario,importe_linea);

    }

    public int getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(int id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        if (descripcion_producto == null){
            throw new IllegalArgumentException("La descripcion del producto no puede estar vacia.");
        }
        this.descripcion_producto = descripcion_producto;
    }

    public String getCategoria_producto() {
        return categoria_producto;
    }

    public void setCategoria_producto(String categoria_producto) {
        if (categoria_producto == null){
            throw new IllegalArgumentException("La categoria del producto no puede estar vacia.");
        }else if (!categoria_producto.equals("HARDWARE") &&
                  !categoria_producto.equals("SOFTWARE") &&
                  !categoria_producto.equals("PERIFERICO") &&
                  !categoria_producto.equals("REDES") &&
                  !categoria_producto.equals("CONSUMIBLE")){
            throw new IllegalArgumentException("La categoria no es válida");
        }

        this.categoria_producto = categoria_producto.toUpperCase();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad<=0){
            throw new IllegalArgumentException("La cantidad no puede ser menor a 0.");
        }
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        if (precio_unitario<=0){
            throw new IllegalArgumentException("El precio unitario no puede ser menor a 0.");

        }
        this.precio_unitario = precio_unitario;
    }

    public double getImporte_linea() {
        return importe_linea;
    }

    public void setImporte_linea(double importe_linea) {
        if (importe_linea<=0){
            throw new IllegalArgumentException("El importe linea no puede ser menor que cero.");

        } else if (importe_linea != precio_unitario * cantidad) {
            throw new IllegalArgumentException("El importe linea debe ser precio unitario por la cantidad de objetos.");
        }
        this.importe_linea = importe_linea;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "id_detalle_pedido=" + id_detalle_pedido +
                ", id_pedido=" + id_pedido +
                ", descripcion_producto='" + descripcion_producto + '\'' +
                ", categoria_producto='" + categoria_producto + '\'' +
                ", cantidad=" + cantidad +
                ", precio_unitario=" + precio_unitario +
                ", importe_linea=" + importe_linea +
                '}';
    }
}
