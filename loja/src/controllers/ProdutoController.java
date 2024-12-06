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
    private ProdutoRepository repository;
    private ProdutoView tableView;

    public ProdutoController() {
        repository = new ProdutoRepository();
        tableView = new ProdutoView();
        inicializar();
    }

private void inicializar() {

    atualizarTabela();

    JToolBar toolBar = new JToolBar();
    JButton criarButton = new JButton("Criar Produto");
    JButton editarButton = new JButton("Editar");
    JButton deletarButton = new JButton("Deletar");
    toolBar.add(criarButton);
    toolBar.add(editarButton);
    toolBar.add(deletarButton);

    tableView.add(toolBar, java.awt.BorderLayout.SOUTH);

    criarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            postarProduto();
        }
    });

    editarButton.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) { 
            editarProduto();
        }
    });

    deletarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deletarProduto();
        }
    });

    tableView.setVisible(true);
}

    private void atualizarTabela() {
            List<Produto> produtos = repository.obterTodosProdutos(); 
            tableView.atualizarTabela(produtos);
        }

    private void postarProduto() {
        ProdForm form = new ProdForm(tableView, "Criar Produto"); 
        form.setVisible(true);
        Produto novoProduto = form.getProduto();
        if (novoProduto != null) {
            repository.postarProduto(novoProduto);
            atualizarTabela();
        }
    }

    private void editarProduto() {
        int selectedId = tableView.getSelectedProdutoId();
        if (selectedId != -1) {
            Produto produto = repository.obterProdutoPorId(selectedId);
            if (produto != null) {
                ProdForm form = new ProdForm (tableView, 
                    "Editar Produto", produto);
                form.setVisible(true);
                Produto produtoAtualizado = form.getProduto();
                if (produtoAtualizado != null) {
                    produtoAtualizado = new Produto( 
                        selectedId,
                        produtoAtualizado.getNome(),
                        produtoAtualizado.getTel(),
                        produtoAtualizado.getPreco(),
                        produtoAtualizado.getOndEnc(),
                        produtoAtualizado.getTipo(),
                        produtoAtualizado.getDis()
                    );
                    repository.atualizarProduto(produtoAtualizado);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(tableView,
            "Produto não encontrado.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        
        } else {
                JOptionPane.showMessageDialog(tableView,
            "Selecione um Produto para editar.",
            "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deletarProduto() {
            int selectedId = tableView.getSelectedProdutoId();
            if (selectedId != -1) {
                int confirm = JOptionPane.showConfirmDialog(
                    tableView,
            "Tem certeza que deseja deletar este Produto?",
            "Confirmar Deleção",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                repository.deletarProduto(selectedId);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(
            tableView,
            "Selecione um Produto para deletar.",
            "Aviso",
            JOptionPane.WARNING_MESSAGE);
        }
    }
    public void iniciar() {
    // Ações já são inicializadas no construtor
    }
}   