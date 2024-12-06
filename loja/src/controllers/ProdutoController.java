package controllers;

import models.Produto;
import repository.ProdutoRepository;
import views.ProdForm;
import views.ProdutoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdutoController {
    private ProdutoRepository repository; // Repositório para gerenciar dados de produtos
    private ProdutoView tableView; // Interface para exibição de produtos

    public ProdutoController() {
        repository = new ProdutoRepository(); // Inicializa o repositório
        tableView = new ProdutoView(); // Inicializa a interface de exibição
        inicializar();
    }

    private void inicializar() {
        atualizarTabela(); // Atualiza a tabela com os produtos

        // Configura a barra de ferramentas com os botões de ações
        JToolBar toolBar = new JToolBar();
        JButton criarButton = new JButton("Criar Produto");
        JButton editarButton = new JButton("Editar");
        JButton deletarButton = new JButton("Deletar");
        toolBar.add(criarButton);
        toolBar.add(editarButton);
        toolBar.add(deletarButton);

        // Adiciona a barra de ferramentas à parte inferior
        tableView.add(toolBar, java.awt.BorderLayout.SOUTH);

        // Associa ação ao botão de criar produto
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postarProduto(); // Chama método para criar um novo produto
            }
        });

        // Associa ação ao botão de editar produto
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProduto(); // Chama método para editar um produto existente
            }
        });

        // Associa ação ao botão de deletar produto
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarProduto(); // Chama método para deletar um produto
            }
        });

        tableView.setVisible(true); // Torna a interface visível
    }

    private void atualizarTabela() {
        // Obtém a lista de produtos do repositório e atualiza a tabela na visão
        List<Produto> produtos = repository.obterTodosProdutos(); 
        tableView.atualizarTabela(produtos);
    }

    private void postarProduto() {
        // Abre o formulário para criar um novo produto
        ProdForm form = new ProdForm(tableView, "Criar Produto"); 
        form.setVisible(true);
        Produto novoProduto = form.getProduto();
        if (novoProduto != null) { 
            repository.postarProduto(novoProduto); // Salva o produto no repositório
            atualizarTabela(); 
        }
    }

    private void editarProduto() {
        // Obtém o ID do produto selecionado na tabela
        int selectedId = tableView.getSelectedProdutoId();
        if (selectedId != -1) { // Verifica se há um produto selecionado
            Produto produto = repository.obterProdutoPorId(selectedId);
            if (produto != null) { // Verifica se o produto existe
                // Abre o formulário para editar o produto
                ProdForm form = new ProdForm(tableView, "Editar Produto", produto);
                form.setVisible(true); 
                Produto produtoAtualizado = form.getProduto(); // Obtém o produto atualizado do formulário
                if (produtoAtualizado != null) {
                    // Atualiza o produto com os novos dados
                    produtoAtualizado = new Produto(
                        selectedId,
                        produtoAtualizado.getNome(),
                        produtoAtualizado.getTel(),
                        produtoAtualizado.getPreco(),
                        produtoAtualizado.getOndEnc(),
                        produtoAtualizado.getTipo(),
                        produtoAtualizado.getDis()
                    );
                    repository.atualizarProduto(produtoAtualizado); // Salva as alterações no repositório
                    atualizarTabela();
                }
            } else {
                // Exibe erro se o produto não foi encontrado
                JOptionPane.showMessageDialog(tableView,
                    "Produto não encontrado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Exibe aviso se nenhum produto foi selecionado
            JOptionPane.showMessageDialog(tableView,
                "Selecione um Produto para editar.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deletarProduto() {
        // Obtém o ID do produto selecionado na tabela
        int selectedId = tableView.getSelectedProdutoId();
        if (selectedId != -1) {
            // Pede confirmação para deletar o produto
            int confirm = JOptionPane.showConfirmDialog(
                tableView,
                "Tem certeza que deseja deletar este Produto?",
                "Confirmar Deleção",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) { // Se o usuário confirmar
                repository.deletarProduto(selectedId); // Remove o produto do repositório
                atualizarTabela();
            }
        } else {
            // Exibe aviso se nenhum produto foi selecionado
            JOptionPane.showMessageDialog(
                tableView,
                "Selecione um Produto para deletar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public void iniciar() {
    }
}
