package views;

import models.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProdutoView extends JFrame {
    private JTable table; // Tabela para exibir os produtos
    private DefaultTableModel tableModel; // Modelo da tabela

    // Construtor da janela principal
    public ProdutoView() {
        super("Vitrine de Produtos"); // Define o título da janela
        initializeComponents(); // Inicializa os componentes da interface
    }

    private void initializeComponents() {
        String[] columnNames = { // Define os nomes das colunas
            "ID", "Nome", "Telefone", "Preço", "Onde encontrar", "Tipo", "Disponível?"
        };
        tableModel = new DefaultTableModel(columnNames, 0); // Cria o modelo da tabela
        table = new JTable(tableModel); // Cria a tabela com o modelo
        JScrollPane scrollPane = new JScrollPane(table); 

        scrollPane.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Define margens ao redor da tabela
        );

        this.setLayout(new BorderLayout()); // Define o layout da janela
        this.add(scrollPane, BorderLayout.CENTER); // Adiciona o painel de rolagem ao centro

        this.setSize(600, 400); // Define o tamanho da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao fechar a janela
        this.setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    public void atualizarTabela(List<Produto> produtos) { 
        tableModel.setRowCount(0); // Limpa todas as linhas existentes
        for (Produto produto : produtos) { // Adiciona cada produto como uma nova linha
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

    // Obtém o ID do produto selecionado na tabela
    public int getSelectedProdutoId() {
        int selectedRow = table.getSelectedRow(); // Obtém a linha selecionada
        if (selectedRow != -1) { // Verifica se há uma linha selecionada
            return (int) tableModel.getValueAt(selectedRow, 0); // Retorna o ID da linha selecionada
        }
        return -1; // Retorna -1 se nenhuma linha estiver selecionada
    }
}
