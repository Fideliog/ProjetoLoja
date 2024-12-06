package views;

import models.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdForm extends JDialog {

    private JTextField nomeP, numVend, ondeEnc, precoV;
    private JCheckBox comb1, comb2;
    private JComboBox<String> selTipo;
    private JPanel StatSel;
    
    private JButton postarButton;
    private JButton voltarButton;

    private Produto produto;
    private boolean isEditMode;

    public ProdForm(Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false;
        initializeComponents();
    }

    public ProdForm (Frame parent, String title, Produto produto) {
        super (parent, title, true);
        this.produto = produto;
        this.isEditMode = true;
        initializeComponents();
        preencherCampos();
    }

    

private void initializeComponents() {
    
        StatSel = new JPanel();
        comb1 = new JCheckBox("Sim");
        comb2 = new JCheckBox("Não");

        ButtonGroup disponibilidadeGroup = new ButtonGroup();
        disponibilidadeGroup.add(comb1);
        disponibilidadeGroup.add(comb2);

        StatSel.add(comb1);
        StatSel.add(comb2);

        String[] tipos = {"Doce", "Salgado", "Bebida", "Artesanato", "Aparelho"};
        selTipo = new JComboBox<>(tipos);

        nomeP = new JTextField(20);
        numVend = new JTextField(20);
        ondeEnc = new JTextField(20);
        precoV = new JTextField(20);

        postarButton = new JButton("Postar Produto");
        voltarButton = new JButton("Voltar");

        JPanel panel = new JPanel(new GridLayout (7, 2, 10, 10));
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
        panel.add(new JLabel("Disponivél?:"));
        panel.add(StatSel);



        panel.add(postarButton);
        panel.add(voltarButton);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        postarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    if (isEditMode) {
                        atualizarProduto();
                    } else {
                        adicionarProduto();
                    }
                    dispose();
                }
            }
        });

        voltarButton.addActionListener(e -> dispose());

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(getParent());
    }

    private void preencherCampos() {
        if (produto != null) {
            nomeP.setText(produto.getNome());
            numVend.setText(produto.getTel());
            precoV.setText(produto.getPreco());
            ondeEnc.setText(produto.getOndEnc());
    
            selTipo.setSelectedItem(produto.getTipo());
    
            if ("Sim".equals(produto.getDis())) {
                comb1.setSelected(true);
            } else if ("Não".equals(produto.getDis())) {
                comb2.setSelected(true);
            }
        }
    }

    private boolean validarCampos() {
        if (nomeP.getText().trim().isEmpty() ||
            numVend.getText().trim().isEmpty()) { 
            JOptionPane.showMessageDialog( 
                this, 
                "Preencha os campos obrigatórios.", 
                "Erro", JOptionPane.ERROR_MESSAGE); 
            return false;
        }
        return true;
    }

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

    private void atualizarProduto() {
        if (produto != null) {
            produto.setNome (nomeP.getText().trim());
            produto.setTel (numVend.getText().trim());
            produto.setPreco(precoV.getText().trim());
            produto.setOndEnc (ondeEnc.getText().trim());
            produto.setTipo(selTipo.getSelectedItem().toString());
            produto.setDis(comb1.isSelected() ? "Sim" : "Não");
        }
    }

    public Produto getProduto() {
        return produto;
    }
}