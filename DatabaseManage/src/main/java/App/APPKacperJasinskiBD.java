package App;

import CONEXION.ConexionBD;
import DAO.DetallePedidoDAO;
import DAO.PedidoCompraDAO;
import MODEL.DetallePedido;
import MODEL.PedidoCompra;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class APPKacperJasinskiBD {
    static Scanner sc = new Scanner(System.in);
    static PedidoCompraDAO pedidoDAO = new PedidoCompraDAO();
    static DetallePedidoDAO detalleDAO = new DetallePedidoDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--MENU--");
            System.out.println("------------------------------------------");
            System.out.println("1.  Insertar pedido de compra");
            System.out.println("2.  Modificar pedido de compra");
            System.out.println("3.  Eliminar pedido de compra por su ID");
            System.out.println("4.  Consultar pedido de compra por ID");
            System.out.println("5.  Listar todos los pedidos de compra");
            System.out.println("------------------------------------------");
            System.out.println("6.  Insertar detalle de pedido");
            System.out.println("7.  Modificar detalle de pedido");
            System.out.println("8.  Eliminar detalle de pedido por ID");
            System.out.println("9.  Consultar detalle de pedido por ID");
            System.out.println("10. Listar todos los detalled de pedido");
            System.out.println("------------------------------------------");
            System.out.println("11. Consultas de comprobacion");
            System.out.println("12. Operaciones en bloque detalle");
            System.out.println("13. Operaciones en bloque pedidos");
            System.out.println("------------------------------------------");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            try {
                switch (opcion) {
                    case 1 -> insertarPedido();
                    case 2 -> modificarPedido();
                    case 3 -> eliminarPedidoPorId();
                    case 4 -> consultarPedidoPorId();
                    case 5 -> listarTodosPedidos();
                    case 6 -> insertarDetallePedido();
                    case 7 -> modificarDetalle();
                    case 8 -> eliminarDetallePorId();
                    case 9 -> consultarDetallePorId();
                    case 10 -> listarTodosDetalles();
                    case 11 -> consultasComprobacion();
                    case 12 -> operacionesBloqueDetalle();
                    case 13 -> operacionesBloquePedido();
                    case 0 -> System.out.println("Adios");
                    default -> System.out.println("Opcion no válida");
                }
            } catch (Exception e) {
                System.out.println("No se puede realizar la operacion");
            }
        } while (opcion != 0);

    }

    static void insertarPedido() {
        try {
            PedidoCompra pedido = new PedidoCompra();
            System.out.println("Codigo pedido:");
            pedido.setCodigoPedido(sc.nextLine());
            System.out.println("Codigo proveedor:");
            pedido.setCod_proveedor(sc.nextLine());
            System.out.println("Nombre proveedor:");
            pedido.setNombre_proveedor(sc.nextLine());
            System.out.println("Fecha pedido");
            pedido.setFecha_pedido(Date.valueOf(sc.nextLine()));
            System.out.println("Fecha de entrega:");
            pedido.setFecha_entrega_prevista(Date.valueOf(sc.nextLine()));
            System.out.println("Estado: ");
            pedido.setEstado(sc.nextLine());

            boolean ok = pedidoDAO.insertar(pedido);
            System.out.println(ok ? "Se ha insertado" : "No se ha podido insertar");
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }

    }

    static void modificarPedido() {
        System.out.println("Id del pedido a modificar");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            PedidoCompra pedido = pedidoDAO.buscarPorId(id);

            if (pedido == null) {
                System.out.println("No se encontro el id a modificar");
                return;
            }
            System.out.println("Nuevo codigo de pedido:");
            pedido.setCodigoPedido(sc.nextLine());
            System.out.println("Nuevo codigo de proveedor: ");
            pedido.setCod_proveedor(sc.nextLine());
            System.out.println("Nuevo nombre d proveedor");
            pedido.setNombre_proveedor(sc.nextLine());
            System.out.println("Nueva fecha pedido");
            pedido.setFecha_pedido(Date.valueOf(LocalDate.parse(sc.nextLine())));
            System.out.println("Nueva fecha de entrega:");
            pedido.setFecha_entrega_prevista(Date.valueOf(LocalDate.parse(sc.nextLine())));
            System.out.println("Nuevo estado");
            pedido.setEstado(sc.nextLine());

            boolean ok = pedidoDAO.modificar(pedido);
            System.out.println(ok ? "Pedido modificado " : "No se ha podido modificar el pedido");
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }

    }

    static void eliminarPedidoPorId() {
        try {
            System.out.print("ID del pedido a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();
            boolean ok = pedidoDAO.eliminarPorId(id);
            System.out.println(ok ? "Se ha eliminado correctamente" : "No se ha encontrado el id");
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }

    }

    static void consultarPedidoPorId() {
        try {
            System.out.print("ID del pedido: ");
            int id = sc.nextInt();
            sc.nextLine();
            PedidoCompra pedido = pedidoDAO.buscarPorId(id);
            if (pedido != null) {
                System.out.println(pedido);
            } else {
                System.out.println("No se ha encontrado ningun pedido con ese id.");
            }
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }

    }

    static void listarTodosPedidos() {
        try {
            List<PedidoCompra> lista = pedidoDAO.listarTodos();
            if (lista.isEmpty()) {
                System.out.println("No hay pedidos registrados");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }
    }

    static void insertarDetallePedido() {
        DetallePedido detalle = new DetallePedido();
        codigoDuplicadoInsertarModificar(detalle);

        boolean ok = detalleDAO.insertar(detalle);
        System.out.println(ok ? "Se ha insertado" : "No se ha podido insertar");
    }

    static void modificarDetalle() {
        System.out.println("Id del detalle a modificar");
        int id = sc.nextInt();
        sc.nextLine();

        DetallePedido detalle = detalleDAO.buscarPorId(id);

        if (detalle == null) {
            System.out.println("No se encontro el id a modificar");
            return;
        }

        codigoDuplicadoInsertarModificar(detalle);

        boolean ok = detalleDAO.modificar(detalle);
        System.out.println(ok ? "Detalle modificado " : "No se ha podido modificar el detalle");

    }

    private static void codigoDuplicadoInsertarModificar(DetallePedido detalle) {
        try {
            System.out.println("Codigo pedido:");
            detalle.setId_pedido(sc.nextInt());
            sc.nextLine();
            System.out.println("Descripcion:");
            detalle.setDescripcion_producto(sc.nextLine());
            System.out.println("Categoria:");
            detalle.setCategoria_producto(sc.nextLine());
            System.out.println("Cantidad");
            detalle.setCantidad(sc.nextInt());
            sc.nextLine();
            System.out.println("Precio unitario:");
            detalle.setPrecio_unitario(sc.nextDouble());
            System.out.println("Importe linea: ");
            detalle.setImporte_linea(sc.nextDouble());
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }
    }

    static void eliminarDetallePorId() {
        try {
            System.out.println("Que id de detalle quiere eliminar");
            int id = sc.nextInt();
            sc.nextLine();
            boolean ok = detalleDAO.eliminarPorId(id);
            System.out.println(ok ? "Detalle eliminado correctamente." : "No se encontró ningun detalle con ese ID.");
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }
    }

    static void consultarDetallePorId() {
        try {
            System.out.println("Que id de detalle quiere consultar");
            int id = sc.nextInt();
            sc.nextLine();
            DetallePedido detalle = detalleDAO.buscarPorId(id);

            if (detalle != null) {
                System.out.println(detalle);
            } else {
                System.out.println("No se encontraron detalles con ese ID");
            }
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }
    }

    static void listarTodosDetalles() {
        try {
            List<DetallePedido> lista = detalleDAO.listarTodos();
            if (lista.isEmpty()) {
                System.out.println("No hay detalles registrados");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error en el dato: " + e.getMessage());
        }
    }

    static void consultasComprobacion() {
        try {
            System.out.println("--------------------------");
            System.out.println("Que id de detalle quiere consultar");
            int id = sc.nextInt();
            sc.nextLine();
            DetallePedido detalle = detalleDAO.buscarPorId(id);
            if (detalle != null) {
                System.out.println(detalle);
            } else {
                System.out.println("No se encontraron detalles con ese ID");
            }
        } catch (Exception e) {
            System.out.println("Error en comprobacion 1. " + e.getMessage());
        }

        try {
            System.out.println("--------------------------");
            System.out.println("Precio");
            String sql = "SELECT sum(importe_linea) as importe_linea from detalles_pedido";
            try (Connection conexion = ConexionBD.getConexion(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    System.out.println("Precio total: " + rs.getDouble("importe_linea"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error en comprobacion 2. " + e.getMessage());
        }

        try {
            System.out.println("--------------------------");
            System.out.println("Mes de mayo , recibido.");
            String sql2 = "Select * from pedidos_compra where estado like 'RECIBIDO' and MONTH(fecha_pedido) like 5";
            try (Connection conexion = ConexionBD.getConexion(); PreparedStatement ps = conexion.prepareStatement(sql2); ResultSet rs = ps.executeQuery();) {

                while (rs.next()) {
                    System.out.println("Codigo pedido: º" + rs.getString("codigo_pedido") + "Nombre: " + rs.getString("nombre_proveedor") + "Fecha de entrega prevista: " + rs.getDate("fecha_entrega_prevista"));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error en comprobacion 3. " + e.getMessage());
        }


        try {
            System.out.println("--------------------------");
            System.out.println("Consulta con group by.");
            String sql3 = "SELECT categoria_producto, COUNT(*) AS num_productos, SUM(importe_linea) AS total " + "FROM detalles_pedido GROUP BY categoria_producto";

            try (Connection conexion = ConexionBD.getConexion(); PreparedStatement ps = conexion.prepareStatement(sql3); ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    System.out.println("Categoria: " + rs.getString("categoria_producto") + " | Num productos: " + rs.getInt("num_productos") + " | Total: " + rs.getDouble("total") + " EUR");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error en comprobacion 4. " + e.getMessage());
        }


        try {
            System.out.println("--------------------------");
            System.out.println("Importe total por pedido:");
            String sql4 = "SELECT p.codigo_pedido, p.nombre_proveedor, SUM(d.importe_linea) AS total " + "FROM pedidos_compra p JOIN detalles_pedido d ON p.id_pedido = d.id_pedido " + "GROUP BY p.codigo_pedido, p.nombre_proveedor";
            try (Connection conexion = ConexionBD.getConexion(); PreparedStatement ps = conexion.prepareStatement(sql4); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("codigo_pedido") + " | " + rs.getString("nombre_proveedor") + " | Total: " + rs.getDouble("total") + " EUR");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error en comprobacion 5. " + e.getMessage());
        }


    }

    static void operacionesBloqueDetalle() {
        System.out.println("Operaciones en bloque.");
        insertarDetallePedido();

        consultarDetallePorId();

        listarTodosDetalles();
    }

    static void operacionesBloquePedido() {
        System.out.println("Operaciones en bloque.");
        insertarPedido();

        eliminarPedidoPorId();

        listarTodosPedidos();
    }
}
