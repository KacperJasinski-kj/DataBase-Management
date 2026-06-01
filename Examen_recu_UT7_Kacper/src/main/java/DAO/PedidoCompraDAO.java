package DAO;

import CONEXION.ConexionBD;
import MODEL.PedidoCompra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoCompraDAO {

    public boolean insertar(PedidoCompra pedidoCompra){
        String sql = "INSERT INTO pedidos_compra (codigo_pedido, cod_proveedor, nombre_proveedor, fecha_pedido ,fecha_entrega_prevista ,estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, pedidoCompra.getCodigoPedido());
            ps.setString(2, pedidoCompra.getCod_proveedor());
            ps.setString(3, pedidoCompra.getNombre_proveedor());
            ps.setDate(4, pedidoCompra.getFecha_pedido());
            ps.setDate(5, pedidoCompra.getFecha_entrega_prevista());
            ps.setString(6, pedidoCompra.getEstado());


            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean modificar(PedidoCompra pedidoCompra){
        String sql = "UPDATE pedidos_compra SET codigo_pedido = ?, cod_proveedor = ?, nombre_proveedor = ?, fecha_pedido = ?,fecha_entrega_prevista = ?, estado = ? WHERE id_pedido = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, pedidoCompra.getCodigoPedido());
            ps.setString(2, pedidoCompra.getCod_proveedor());
            ps.setString(3, pedidoCompra.getNombre_proveedor());
            ps.setDate(4,  pedidoCompra.getFecha_pedido());
            ps.setDate(5, pedidoCompra.getFecha_entrega_prevista());
            ps.setString(6, pedidoCompra.getEstado());
            ps.setInt(7, pedidoCompra.getId_pedido());


            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPorId(int idPedido){
        String sql = "DELETE FROM pedidos_compra where id_pedido = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, idPedido);

            return ps.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public PedidoCompra buscarPorId (int idPedido){
        String sql = "Select * from pedidos_compra where id_pedido = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, idPedido);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return mapear(rs);
            }

        }catch (SQLException e){
            System.out.println("Error al buscar pedido: " + e.getMessage());
        }
        return null;
    }

    public List<PedidoCompra> listarTodos(){
        String sql = "Select * from pedidos_compra";
        List<PedidoCompra> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()){
                lista.add(mapear(rs));
            }
        }catch (SQLException e){
            System.out.println("Error al listar los pedidos: " + e.getMessage());
        }
            return lista;
    }
    private PedidoCompra mapear(ResultSet rs) throws SQLException{
        PedidoCompra pedido = new PedidoCompra();
        pedido.setId_pedido(rs.getInt("id_pedido"));
        pedido.setCodigoPedido(rs.getString("codigo_pedido"));
        pedido.setCod_proveedor(rs.getString("cod_proveedor"));
        pedido.setNombre_proveedor(rs.getString("nombre_proveedor"));
        pedido.setFecha_pedido(rs.getDate("fecha_pedido"));
        pedido.setFecha_entrega_prevista(rs.getDate("fecha_entrega_prevista"));
        pedido.setEstado(rs.getString("estado"));
        return pedido;
    }
}
