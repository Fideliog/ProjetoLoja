package repository;

import models.Produto;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public void postarProduto(Produto produto) {
        String sql = "INSERT INTO Produtos (NomeProd, VendTel, preco, OndEcon, Tipo, Disponivel) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getTel());
        stmt.setString(3, produto.getPreco());
        stmt.setString(4, produto.getOndEnc());
        stmt.setString(5, produto.getTipo());
        stmt.setString(6, produto.getDis());

        int linhasAfetadas = stmt.executeUpdate();
        if (linhasAfetadas > 0) {
            System.out.println("Produto adicionado com sucesso!");
        }

    } catch (SQLException e) {
        System.out.println("Erro ao adicionar produto.");
        e.printStackTrace();
    }
}

    public List<Produto> obterTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produtos";

            
        try (Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("ID"),
                    rs.getString("NomeProd"),
                    rs.getString("VendTel"),
                    rs.getString("preco"),
                    rs.getString("OndEcon"),
                    rs.getString("Tipo"),
                    rs.getString("Disponivel")
                );
            produtos.add(produto);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao obter produtos.");
        e.printStackTrace();
    }
    
    return produtos;
}

    public Produto obterProdutoPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;

        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            produto = new Produto(
                rs.getInt("ID"),
                rs.getString("NomeProd"),
                rs.getString("VendTel"),
                rs.getString("preco"),
                rs.getString("OndEcon"),
                rs.getString("Tipo"),
                rs.getString("Disponivel")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erro ao obter produto por ID.");
        e.printStackTrace();
    }
    
    return produto;
}


    public void atualizarProduto(Produto produto) {
        String sql =
            "UPDATE produtos SET NomeProd = ?, VendTel = ?, preco = ?, OndEcon = ?, Tipo = ?, Disponivel = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getTel());
        stmt.setString(3, produto.getPreco());
        stmt.setString(4, produto.getOndEnc());
        stmt.setString(5, produto.getTipo());
        stmt.setString(6, produto.getDis());
        stmt.setInt(7, produto.getId());

        int linhasAfetadas = stmt.executeUpdate();
        if (linhasAfetadas > 0) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }

    } catch (SQLException e) {
        System.out.println("Erro ao atualizar produto.");
        e.printStackTrace();
    }
}

    public void deletarProduto (int id) {
        String sql = "DELETE FROM Produtos WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto."); 
            e.printStackTrace();
        }
    }
}