package MODEL;

import java.sql.Date;
import java.time.LocalDate;

public class PedidoCompra {

    //atributos
    private int id_pedido;
    private String codigoPedido;
    private String cod_proveedor;
    private String nombre_proveedor;
    private Date fecha_pedido;
    private Date fecha_entrega_prevista;
    private String estado;

    //constante
    private static final String[] estadoValido = {"SOLICITADO", "RECIBIDO", "CANCELADO"};
    private static final String codigoPedidoValido = ("^PC_[0-9]{5}/[0-9]{2}$");

    public PedidoCompra(){this.estado = "SOLICITADO";} //valor por defecto igual que la BD

    public PedidoCompra(int id_pedido, String cod_proveedor, String codigoPedido,
                        String nombre_proveedor, Date fecha_pedido,
                        String estado, Date fecha_entrega_prevista) {
        this.id_pedido = id_pedido;
        setCodigoPedido(codigoPedido);
        setCod_proveedor(cod_proveedor);
        setNombre_proveedor(nombre_proveedor);
        setFecha_pedido(fecha_pedido);
        setEstado(estado);
        setFecha_entrega_prevista(fecha_entrega_prevista);
    }


    public PedidoCompra(String codigoPedido, String cod_proveedor,
                        String nombre_proveedor, Date fecha_pedido,
                        Date fecha_entrega_prevista, String estado) {
        this(0, cod_proveedor,codigoPedido,nombre_proveedor,fecha_pedido,estado,fecha_entrega_prevista);
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        if (codigoPedido == null){
            throw new IllegalArgumentException("El codigo de pedido no puede estar vacio.");
        } else if (!codigoPedido.matches(codigoPedidoValido)) {
            throw new IllegalArgumentException("El codigo de pedido no es valido.");
        }
        this.codigoPedido = codigoPedido;
    }

    public String getCod_proveedor() {
        return cod_proveedor;
    }

    public void setCod_proveedor(String cod_proveedor) {
        if (cod_proveedor == null){
            throw new IllegalArgumentException("El codigo de proveedor no puede estar vacio.");
        }
        this.cod_proveedor = cod_proveedor;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        if (fecha_pedido == null){
            throw new IllegalArgumentException("La fecha de pedido no puede estar vacio.");
        }
        this.fecha_pedido = fecha_pedido;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        if (nombre_proveedor == null){
            throw new IllegalArgumentException("El nombre de proveedor no puede estar vacio.");
        }
        this.nombre_proveedor = nombre_proveedor;
    }

    public Date getFecha_entrega_prevista() {
        return fecha_entrega_prevista;
    }

    public void setFecha_entrega_prevista(Date fecha_entrega_prevista) {
        if (fecha_entrega_prevista == null){
            throw new IllegalArgumentException("La fecha de pedido no puede ser null.");
        } else if (fecha_pedido.after(fecha_entrega_prevista)) {
            throw new IllegalArgumentException("La fecha de pedido no puede ser posterior a la fecha de engrega.");
        }
        this.fecha_entrega_prevista = fecha_entrega_prevista;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || (!estado.equalsIgnoreCase("SOLICITADO")
                && !estado.equalsIgnoreCase("RECIBIDO")
                && !estado.equalsIgnoreCase("CANCELADO")
        )) throw new IllegalArgumentException("El estado no es valido.");
        this.estado = estado.toUpperCase();
    }

    @Override
    public String toString() {
        return "PedidoCompra{" +
                "id_pedido=" + id_pedido +
                ", codigoPedido='" + codigoPedido + '\'' +
                ", cod_proveedor='" + cod_proveedor + '\'' +
                ", nombre_proveedor='" + nombre_proveedor + '\'' +
                ", fecha_pedido=" + fecha_pedido +
                ", fecha_entrega_prevista=" + fecha_entrega_prevista +
                ", estado='" + estado + '\'' +
                '}';
    }
}
