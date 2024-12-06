package views;

import models.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdForm extends JDialog {

    // Componentes da interface
    private JTextField nomeP, numVend, ondeEnc, precoV;
    private JCheckBox comb1, comb2;
    private JComboBox<String> selTipo;
    private JPanel StatSel;
    private JButton postarButton;
    private JButton voltarButton;
    private Produto produto; 
    private boolean isEditMode; 

    // Construtor para criar um novo produto
    public ProdForm(Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false; // Não está no modo de edição
        initializeComponents();
    }

    // Construtor para editar um produto existente
    public ProdForm(Frame parent, String title, Produto produto) {
        super(parent, title, true);
        this.produto = produto; // Produto a ser editado
        this.isEditMode = true; // Está no modo de edição
        initializeComponents();
        preencherCampos(); // Preenche os campos com os dados do produto
    }

    private void initializeComponents() {
        // Configuração dos checkboxes para disponibilidade
        StatSel = new JPanel();
        comb1 = new JCheckBox("Sim");
        comb2 = new JCheckBox("Não");

        // Agrupa os checkboxes para garantir que apenas um seja selecionado
        ButtonGroup disponibilidadeGroup = new ButtonGroup();
        disponibilidadeGroup.add(comb1);
        disponibilidadeGroup.add(comb2);

        StatSel.add(comb1);
        StatSel.add(comb2);

        // Opções para o campo de tipo
        String[] tipos = {"Doce", "Salgado", "Bebida", "Artesanato", "Aparelho"};
        selTipo = new JComboBox<>(tipos);

        // Campos de texto
        nomeP = new JTextField(20);
        numVend = new JTextField(20);
        ondeEnc = new JTextField(20);
        precoV = new JTextField(20);

        // Botões
        postarButton = new JButton("Postar Produto");
        voltarButton = new JButton("Voltar");

        // Painel para organizar os campos e rótulos
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeP);
        panel.add(new JLabel("Telefone:"));
        panel.add(numVend);
        panel.add(new JLabel("Preço:"));
        panel.add(precoV);
        panel.add(new JLabel("Onde encontrar:"));
        panel.add(ondeEnc);
        panel.add(new JLabel("Tipo:"));
        panel.add(selTipo);
        panel.add(new JLabel("Disponível?:"));
        panel.add(StatSel);
        panel.add(postarButton);
        panel.add(voltarButton);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem do painel

        // Ação do botão "Postar Produto"
        postarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) { // Valida os campos antes de continuar
                    if (isEditMode) { 
                        atualizarProduto(); // Atualiza o produto no modo de edição
                    } else {
                        adicionarProduto(); // Adiciona um novo produto
                    }
                    dispose(); // Fecha a janela
                }
            }
        });

        // Ação do botão "Voltar"
        voltarButton.addActionListener(e -> dispose());

        this.add(panel); // Adiciona o painel principal
        this.pack(); // Ajusta o tamanho da janela
        this.setLocationRelativeTo(getParent()); // Centraliza a janela
    }

    // Preenche os campos com os dados do produto no modo de edição
    private void preencherCampos() {
        if (produto != null) {
            nomeP.setText(produto.getNome());
            numVend.setText(produto.getTel());
            precoV.setText(produto.getPreco());
            ondeEnc.setText(produto.getOndEnc());
            selTipo.setSelectedItem(produto.getTipo());

            // Define o checkbox de disponibilidade
            if ("Sim".equals(produto.getDis())) {
                comb1.setSelected(true);
            } else if ("Não".equals(produto.getDis())) {
                comb2.setSelected(true);
            }
        }
    }

    // Valida se os campos obrigatórios estão preenchidos
    private boolean validarCampos() {
        if (nomeP.getText().trim().isEmpty() || numVend.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Preencha os campos obrigatórios.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }

    // Cria um novo objeto Produto com os dados preenchidos
    private void adicionarProduto() {
        produto = new Produto(
            nomeP.getText().trim(),
            numVend.getText().trim(),
            precoV.getText().trim(),
            ondeEnc.getText().trim(),
            selTipo.getSelectedItem().toString(),
            comb1.isSelected() ? "Sim" : "Não"
        );
    }

    // Atualiza os dados do produto no modo de edição
    private void atualizarProduto() {
        if (produto != null) {
            produto.setNome(nomeP.getText().trim());
            produto.setTel(numVend.getText().trim());
            produto.setPreco(precoV.getText().trim());
            produto.setOndEnc(ondeEnc.getText().trim());
            produto.setTipo(selTipo.getSelectedItem().toString());
            produto.setDis(comb1.isSelected() ? "Sim" : "Não");
        }
    }

    // Retorna o produto criado ou atualizado
    public Produto getProduto() {
        return produto;
    }
}
