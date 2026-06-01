package DAO;

import CONEXION.ConexionBD;
import MODEL.DetallePedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {

    public boolean insertar(DetallePedido detallePedido){
        String sql = "INSERT INTO detalles_pedido(id_pedido,descripcion_producto, categoria_producto, cantidad, precio_unitario, importe_linea) values (?,?,?,?,?,?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detallePedido.getId_pedido());
            ps.setString(2, detallePedido.getDescripcion_producto());
            ps.setString(3, detallePedido.getCategoria_producto());
            ps.setInt(4, detallePedido.getCantidad());
            ps.setDouble(5,detallePedido.getPrecio_unitario());
            ps.setDouble(6, detallePedido.getImporte_linea());

            return ps.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean modificar(DetallePedido detallePedido){
        String sql = "UPDATE detalles_pedido set id_pedido = ?,  descripcion_producto = ?, categoria_producto = ?, cantidad = ?, precio_unitario = ?, importe_linea = ? where id_detalle_pedido = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detallePedido.getId_pedido());
            ps.setString(2, detallePedido.getDescripcion_producto());
            ps.setString(3, detallePedido.getCategoria_producto());
            ps.setInt(4, detallePedido.getCantidad());
            ps.setDouble(5,detallePedido.getPrecio_unitario());
            ps.setDouble(6, detallePedido.getImporte_linea());
            ps.setInt(7,detallePedido.getId_detalle_pedido());

            return ps.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPorId(int idDetallePedido){
        String sql = "DELETE from detalles_pedido where id_detalle_pedido = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,idDetallePedido);

            return ps.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    public DetallePedido buscarPorId(int idDetallePedido){
        String sql = "select * from detalles_pedido where id_detalle_pedido = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDetallePedido);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }


        }catch (SQLException e){
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    public List<DetallePedido> listarTodos(){
        String sql = "select * from detalles_pedido";
        List<DetallePedido> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar las configuraciones de red: " + e.getMessage());
        }

        return lista;
    }

    private DetallePedido mapear(ResultSet rs) throws  SQLException{
        DetallePedido detalles = new DetallePedido();
        detalles.setId_detalle_pedido(rs.getInt("id_detalle_pedido"));
        detalles.setId_pedido(rs.getInt("id_pedido"));
        detalles.setDescripcion_producto(rs.getString("descripcion_producto"));
        detalles.setCategoria_producto(rs.getString("categoria_producto"));
        detalles.setCantidad(rs.getInt("cantidad"));
        detalles.setPrecio_unitario(rs.getDouble("precio_unitario"));
        detalles.setImporte_linea(rs.getDouble("importe_linea"));
        return detalles;
    }
}

