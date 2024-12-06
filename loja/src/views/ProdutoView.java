package views;

import models.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProdutoView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ProdutoView() {
        super("Vitrine de Produtos");
        initializeComponents();
    }

private void initializeComponents() {
        String[] columnNames = {"ID", "Nome", "Telefone", "Preço","Onde encontrar","Tipo","Disponivél?"};
        tableModel = new DefaultTableModel (columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

public void atualizarTabela (List<Produto> produtos) { 
        tableModel.setRowCount(0); // Limpa a tabela
        for (Produto produto : produtos) {
            Object[] row = {
                produto.getId(),
                produto.getNome(),
                produto.getTel(),
                produto.getPreco(),
                produto.getOndEnc(),
                produto.getTipo(),
                produto.getDis()
            };
            tableModel.addRow(row);
        }
    }

public int getSelectedProdutoId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }
}