/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PolicyEditorGUI.java
 *
 * Created on 14 avr. 2012, 11:12:02
 */

package policyManager;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import rbacTools.editor.PolicyEditor;


/**
 *
 * @author obendavi
 */
public class PolicyManagerGUI extends javax.swing.JFrame {
	
	
	private PolicyManager policyManager;
	
	public PolicyManagerGUI(PolicyManager pm) {
        initComponents();
        policyManager = pm;
    }
	
	public void updateComboBox(Vector<String> data,JComboBox comboBox) {
		comboBox.setModel(new DefaultComboBoxModel(data));
	}

	public void output(String s) {
		textAreaOutput.setText(textAreaOutput.getText() + "\n" + s); 
	}
	

    /**
	 * @return the policyManager
	 */
	public PolicyManager getPolicyManager() {
		return policyManager;
	}

	/**
	 * @return the buttonApplyAction
	 */
	public javax.swing.JButton getButtonApplyAction() {
		return buttonApplyAction;
	}

	/**
	 * @return the buttonaddElement
	 */
	public javax.swing.JButton getButtonaddElement() {
		return buttonaddElement;
	}

	/**
	 * @return the buttonaddPermOp
	 */
	public javax.swing.JButton getButtonaddPermOp() {
		return buttonaddPermOp;
	}

	/**
	 * @return the buttonaddPermOpOb
	 */
	public javax.swing.JButton getButtonaddPermOpOb() {
		return buttonaddPermOpOb;
	}

	/**
	 * @return the buttonaddRolePerm
	 */
	public javax.swing.JButton getButtonaddRolePerm() {
		return buttonaddRolePerm;
	}

	/**
	 * @return the buttonaddUserRole
	 */
	public javax.swing.JButton getButtonaddUserRole() {
		return buttonaddUserRole;
	}

	/**
	 * @return the buttonremoeveRolePerm
	 */
	public javax.swing.JButton getButtonremoeveRolePerm() {
		return buttonremoeveRolePerm;
	}

	/**
	 * @return the buttonremoveElement
	 */
	public javax.swing.JButton getButtonremoveElement() {
		return buttonremoveElement;
	}

	/**
	 * @return the buttonremovePermOp
	 */
	public javax.swing.JButton getButtonremovePermOp() {
		return buttonremovePermOp;
	}

	/**
	 * @return the buttonremovePermOpOb
	 */
	public javax.swing.JButton getButtonremovePermOpOb() {
		return buttonremovePermOpOb;
	}

	/**
	 * @return the buttonremoveUserRole
	 */
	public javax.swing.JButton getButtonremoveUserRole() {
		return buttonremoveUserRole;
	}

	/**
	 * @return the buttonupdateOpOb
	 */
	public javax.swing.JButton getButtonupdateOpOb() {
		return buttonupdateOpOb;
	}

	/**
	 * @return the buttonupdateType
	 */
	public javax.swing.JButton getButtonupdateType() {
		return buttonupdateType;
	}

	/**
	 * @return the comboBoxAction
	 */
	public javax.swing.JComboBox getComboBoxAction() {
		return comboBoxAction;
	}

	/**
	 * @return the comboBoxDisplayElt
	 */
	public javax.swing.JComboBox getComboBoxDisplayElt() {
		return comboBoxDisplayElt;
	}

	/**
	 * @return the comboBoxOb
	 */
	public javax.swing.JComboBox getComboBoxOb() {
		return comboBoxOb;
	}

	/**
	 * @return the comboBoxOb1
	 */
	public javax.swing.JComboBox getComboBoxOb1() {
		return comboBoxOb1;
	}

	/**
	 * @return the comboBoxOp
	 */
	public javax.swing.JComboBox getComboBoxOpPerm() {
		return comboBoxOpPerm;
	}

	/**
	 * @return the comboBoxOp1
	 */
	public javax.swing.JComboBox getComboBoxOpPerm1() {
		return comboBoxOpPerm1;
	}

	/**
	 * @return the comboBoxOp2
	 */
	public javax.swing.JComboBox getComboBoxOp2() {
		return comboBoxOp2;
	}

	/**
	 * @return the comboBoxPerm
	 */
	public javax.swing.JComboBox getComboBoxPerm() {
		return comboBoxPerm;
	}

	/**
	 * @return the comboBoxPerm1
	 */
	public javax.swing.JComboBox getComboBoxPerm1() {
		return comboBoxPerm1;
	}

	/**
	 * @return the comboBoxPerm2
	 */
	public javax.swing.JComboBox getComboBoxPerm2() {
		return comboBoxPerm2;
	}

	/**
	 * @return the comboBoxPerm3
	 */
	public javax.swing.JComboBox getComboBoxPerm3() {
		return comboBoxPerm3;
	}

	/**
	 * @return the comboBoxPerm4
	 */
	public javax.swing.JComboBox getComboBoxPerm4() {
		return comboBoxPerm4;
	}

	/**
	 * @return the comboBoxPerm5
	 */
	public javax.swing.JComboBox getComboBoxPerm5() {
		return comboBoxPerm5;
	}

	/**
	 * @return the comboBoxRole
	 */
	public javax.swing.JComboBox getComboBoxRole() {
		return comboBoxRole;
	}

	/**
	 * @return the comboBoxRole1
	 */
	public javax.swing.JComboBox getComboBoxRole1() {
		return comboBoxRole1;
	}

	/**
	 * @return the comboBoxRole2
	 */
	public javax.swing.JComboBox getComboBoxRole2() {
		return comboBoxRole2;
	}

	/**
	 * @return the comboBoxRole3
	 */
	public javax.swing.JComboBox getComboBoxRole3() {
		return comboBoxRole3;
	}

	/**
	 * @return the comboBoxTypElt
	 */
	public javax.swing.JComboBox getComboBoxTypElt() {
		return comboBoxTypElt;
	}

	/**
	 * @return the comboBoxTypElt1
	 */
	public javax.swing.JComboBox getComboBoxTypElt1() {
		return comboBoxTypElt1;
	}

	/**
	 * @return the comboBoxUser
	 */
	public javax.swing.JComboBox getComboBoxUser() {
		return comboBoxUser;
	}

	/**
	 * @return the comboBoxUser1
	 */
	public javax.swing.JComboBox getComboBoxUser1() {
		return comboBoxUser1;
	}

	/**
	 * @return the jComboBox2
	 */
	public javax.swing.JComboBox getjComboBox2() {
		return jComboBox2;
	}

	/**
	 * @return the jComboBox3
	 */
	public javax.swing.JComboBox getjComboBox3() {
		return jComboBox3;
	}

	/**
	 * @return the jLabel1
	 */
	public javax.swing.JLabel getjLabel1() {
		return jLabel1;
	}

	/**
	 * @return the jLabel10
	 */
	public javax.swing.JLabel getjLabel10() {
		return jLabel10;
	}

	/**
	 * @return the jLabel11
	 */
	public javax.swing.JLabel getjLabel11() {
		return jLabel11;
	}

	/**
	 * @return the jLabel12
	 */
	public javax.swing.JLabel getjLabel12() {
		return jLabel12;
	}

	/**
	 * @return the jLabel13
	 */
	public javax.swing.JLabel getjLabel13() {
		return jLabel13;
	}

	/**
	 * @return the jLabel14
	 */
	public javax.swing.JLabel getjLabel14() {
		return jLabel14;
	}

	/**
	 * @return the jLabel2
	 */
	public javax.swing.JLabel getjLabel2() {
		return jLabel2;
	}

	/**
	 * @return the jLabel3
	 */
	public javax.swing.JLabel getjLabel3() {
		return jLabel3;
	}

	/**
	 * @return the jLabel4
	 */
	public javax.swing.JLabel getjLabel4() {
		return jLabel4;
	}

	/**
	 * @return the jLabel5
	 */
	public javax.swing.JLabel getjLabel5() {
		return jLabel5;
	}

	/**
	 * @return the jLabel6
	 */
	public javax.swing.JLabel getjLabel6() {
		return jLabel6;
	}

	/**
	 * @return the jLabel7
	 */
	public javax.swing.JLabel getjLabel7() {
		return jLabel7;
	}

	/**
	 * @return the jLabel8
	 */
	public javax.swing.JLabel getjLabel8() {
		return jLabel8;
	}

	/**
	 * @return the jLabel9
	 */
	public javax.swing.JLabel getjLabel9() {
		return jLabel9;
	}

	/**
	 * @return the jMenu1
	 */
	public javax.swing.JMenu getjMenu1() {
		return jMenu1;
	}

	/**
	 * @return the jMenu2
	 */
	public javax.swing.JMenu getjMenu2() {
		return jMenu2;
	}

	/**
	 * @return the jMenuBar1
	 */
	public javax.swing.JMenuBar getjMenuBar1() {
		return jMenuBar1;
	}

	/**
	 * @return the jPanel1
	 */
	public javax.swing.JPanel getjPanel1() {
		return jPanel1;
	}

	/**
	 * @return the jPanel10
	 */
	public javax.swing.JPanel getjPanel10() {
		return jPanel10;
	}

	/**
	 * @return the jPanel11
	 */
	public javax.swing.JPanel getjPanel11() {
		return jPanel11;
	}

	/**
	 * @return the jPanel12
	 */
	public javax.swing.JPanel getjPanel12() {
		return jPanel12;
	}

	/**
	 * @return the jPanel13
	 */
	public javax.swing.JPanel getjPanel13() {
		return jPanel13;
	}

	/**
	 * @return the jPanel14
	 */
	public javax.swing.JPanel getjPanel14() {
		return jPanel14;
	}

	/**
	 * @return the jPanel15
	 */
	public javax.swing.JPanel getjPanel15() {
		return jPanel15;
	}

	/**
	 * @return the jPanel2
	 */
	public javax.swing.JPanel getjPanel2() {
		return jPanel2;
	}

	/**
	 * @return the jPanel3
	 */
	public javax.swing.JPanel getjPanel3() {
		return jPanel3;
	}

	/**
	 * @return the jPanel4
	 */
	public javax.swing.JPanel getjPanel4() {
		return jPanel4;
	}

	/**
	 * @return the jPanel5
	 */
	public javax.swing.JPanel getjPanel5() {
		return jPanel5;
	}

	/**
	 * @return the jPanel6
	 */
	public javax.swing.JPanel getjPanel6() {
		return jPanel6;
	}

	/**
	 * @return the jPanel7
	 */
	public javax.swing.JPanel getjPanel7() {
		return jPanel7;
	}

	/**
	 * @return the jPanel8
	 */
	public javax.swing.JPanel getjPanel8() {
		return jPanel8;
	}

	/**
	 * @return the jPanel9
	 */
	public javax.swing.JPanel getjPanel9() {
		return jPanel9;
	}

	/**
	 * @return the jScrollPane1
	 */
	public javax.swing.JScrollPane getjScrollPane1() {
		return jScrollPane1;
	}

	/**
	 * @return the jSpinner1
	 */
	public javax.swing.JSpinner getjSpinner1() {
		return jSpinner1;
	}

	/**
	 * @return the jSpinner2
	 */
	public javax.swing.JSpinner getjSpinner2() {
		return jSpinner2;
	}

	/**
	 * @return the jSpinner3
	 */
	public javax.swing.JSpinner getjSpinner3() {
		return jSpinner3;
	}

	/**
	 * @return the jSpinner4
	 */
	public javax.swing.JSpinner getjSpinner4() {
		return jSpinner4;
	}

	/**
	 * @return the jSpinner5
	 */
	public javax.swing.JSpinner getjSpinner5() {
		return jSpinner5;
	}

	/**
	 * @return the jSpinner6
	 */
	public javax.swing.JSpinner getjSpinner6() {
		return jSpinner6;
	}

	/**
	 * @return the jSplitPane1
	 */
	public javax.swing.JSplitPane getjSplitPane1() {
		return jSplitPane1;
	}

	/**
	 * @return the jTabbedPane1
	 */
	public javax.swing.JTabbedPane getjTabbedPane1() {
		return jTabbedPane1;
	}

	/**
	 * @return the textAreaOutput
	 */
	public javax.swing.JTextArea getTextAreaOutput() {
		return textAreaOutput;
	}

	/**
	 * @return the textFieldEltName
	 */
	public javax.swing.JTextField getTextFieldEltName() {
		return textFieldEltName;
	}

	/**
	 * @return the textFieldOpName
	 */
	public javax.swing.JTextField getTextFieldOpName() {
		return textFieldOpName;
	}

	/** Creates new form PolicyEditorGUI */
    public PolicyManagerGUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboBoxTypElt = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textFieldEltName = new javax.swing.JTextField();
        buttonaddElement = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        comboBoxTypElt1 = new javax.swing.JComboBox();
        buttonupdateType = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboBoxDisplayElt = new javax.swing.JComboBox();
        buttonremoveElement = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jSpinner5 = new javax.swing.JSpinner();
        jSpinner6 = new javax.swing.JSpinner();
        comboBoxAction = new javax.swing.JComboBox();
        buttonApplyAction = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        comboBoxUser = new javax.swing.JComboBox();
        comboBoxRole = new javax.swing.JComboBox();
        buttonaddUserRole = new javax.swing.JButton();
        comboBoxUser1 = new javax.swing.JComboBox();
        buttonremoveUserRole = new javax.swing.JButton();
        comboBoxRole1 = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        comboBoxRole2 = new javax.swing.JComboBox();
        comboBoxPerm = new javax.swing.JComboBox();
        comboBoxRole3 = new javax.swing.JComboBox();
        buttonaddRolePerm = new javax.swing.JButton();
        buttonremoeveRolePerm = new javax.swing.JButton();
        comboBoxPerm1 = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        comboBoxPerm2 = new javax.swing.JComboBox();
        textFieldOpName = new javax.swing.JTextField();
        comboBoxPerm3 = new javax.swing.JComboBox();
        comboBoxOpPerm = new javax.swing.JComboBox();
        buttonaddPermOp = new javax.swing.JButton();
        buttonremovePermOp = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        comboBoxPerm4 = new javax.swing.JComboBox();
        comboBoxOpPerm1 = new javax.swing.JComboBox();
        comboBoxPerm5 = new javax.swing.JComboBox();
        comboBoxOp2 = new javax.swing.JComboBox();
        buttonaddPermOpOb = new javax.swing.JButton();
        buttonremovePermOpOb = new javax.swing.JButton();
        comboBoxOb = new javax.swing.JComboBox();
        comboBoxOb1 = new javax.swing.JComboBox();
        buttonupdateOpOb = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaOutput = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PolicyEditor");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("add element");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Select type : ");

        comboBoxTypElt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxTypElt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(comboBoxTypElt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("set name : ");

        textFieldEltName.setText("jTextField1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(textFieldEltName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(textFieldEltName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        buttonaddElement.setText("add");
        buttonaddElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddElementActionPerformed(evt);
            }
        });

        jLabel8.setText("remove element");

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Select Type : ");

        comboBoxTypElt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonupdateType.setText("up");
        buttonupdateType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonupdateTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxTypElt1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonupdateType)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9)
                .addComponent(comboBoxTypElt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonupdateType))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Select Element : ");

        comboBoxDisplayElt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxDisplayElt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(comboBoxDisplayElt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buttonremoveElement.setText("remove");
        buttonremoveElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonremoveElementActionPerformed(evt);
            }
        });

        comboBoxAction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonApplyAction.setText("applyAction");
        buttonApplyAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonApplyActionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonaddElement))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonremoveElement))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(96, 96, 96)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(comboBoxAction, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonApplyAction)))
                .addGap(128, 128, 128))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buttonaddElement)
                    .addComponent(jLabel8)
                    .addComponent(buttonremoveElement)
                    .addComponent(comboBoxAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonApplyAction))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("assign user role");

        comboBoxUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonaddUserRole.setText("add");
        buttonaddUserRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddUserRoleActionPerformed(evt);
            }
        });

        comboBoxUser1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonremoveUserRole.setText("remove");
        buttonremoveUserRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonremoveUserRoleActionPerformed(evt);
            }
        });

        comboBoxRole1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboBoxUser1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxRole1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonaddUserRole)
                    .addComponent(buttonremoveUserRole))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonaddUserRole)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxUser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonremoveUserRole)
                    .addComponent(comboBoxRole1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("role perm");

        comboBoxRole2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxPerm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxRole3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonaddRolePerm.setText("add");
        buttonaddRolePerm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddRolePermActionPerformed(evt);
            }
        });

        buttonremoeveRolePerm.setText("remove");
        buttonremoeveRolePerm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonremoeveRolePermActionPerformed(evt);
            }
        });

        comboBoxPerm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(66, 66, 66)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboBoxRole3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxRole2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(comboBoxPerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(buttonaddRolePerm))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(comboBoxPerm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonremoeveRolePerm)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxRole2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonaddRolePerm)
                        .addComponent(comboBoxPerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxRole3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonremoeveRolePerm)
                    .addComponent(comboBoxPerm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setText("perm op");

        comboBoxPerm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        textFieldOpName.setText("jTextField2");

        comboBoxPerm3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxOpPerm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonaddPermOp.setText("add");
        buttonaddPermOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddPermOpActionPerformed(evt);
            }
        });

        buttonremovePermOp.setText("remove");
        buttonremovePermOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonremovePermOpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(comboBoxPerm3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxPerm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(textFieldOpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonaddPermOp))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(comboBoxOpPerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonremovePermOp)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxPerm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFieldOpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonaddPermOp)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxPerm3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxOpPerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonremovePermOp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setText("op object");

        comboBoxPerm4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxOpPerm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxPerm5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxOp2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonaddPermOpOb.setText("add");
        buttonaddPermOpOb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddPermOpObActionPerformed(evt);
            }
        });

        buttonremovePermOpOb.setText("remove");
        buttonremovePermOpOb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonremovePermOpObActionPerformed(evt);
            }
        });

        comboBoxOb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxOb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonupdateOpOb.setText("update");
        buttonupdateOpOb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonupdateOpObActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxPerm4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxPerm5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(buttonupdateOpOb)
                        .addGap(20, 20, 20)
                        .addComponent(comboBoxOp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxOb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(comboBoxOpPerm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonaddPermOpOb)
                    .addComponent(buttonremovePermOpOb))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxPerm4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonaddPermOpOb)
                        .addComponent(comboBoxOpPerm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxPerm5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonupdateOpOb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonremovePermOpOb)
                        .addComponent(comboBoxOb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxOp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jSplitPane1.setRightComponent(jPanel1);

        textAreaOutput.setColumns(20);
        textAreaOutput.setRows(5);
        jScrollPane1.setViewportView(textAreaOutput);

        jTabbedPane1.addTab("Output", jScrollPane1);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1241, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1241, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonaddRolePermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddRolePermActionPerformed
       policyManager.getPolicyEditor().addRolePermissionAssignment(comboBoxRole2.getSelectedItem().toString(), comboBoxPerm.getSelectedItem().toString());
       policyManager.updateGUI();
    }//GEN-LAST:event_buttonaddRolePermActionPerformed

    private void buttonaddElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddElementActionPerformed
      String type =comboBoxTypElt.getSelectedItem().toString();
    	if (type == "User"){
    		policyManager.getPolicyEditor().addUser(textFieldEltName.getText());
    	}
    	if (type == "Role"){
    		policyManager.getPolicyEditor().addRole(textFieldEltName.getText());
    	}
    	if (type == "Permission"){
    		policyManager.getPolicyEditor().addPermission(textFieldEltName.getText());
    	}
    	if (type == "Resource"){
    		policyManager.getPolicyEditor().addObject(textFieldEltName.getText());
    	}
    	if (type == "Session"){
    		policyManager.getPolicyEditor().addSession(textFieldEltName.getText());
    	}
    	policyManager.updateGUI();
    }//GEN-LAST:event_buttonaddElementActionPerformed

    private void buttonremoveElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonremoveElementActionPerformed
    	String type =comboBoxTypElt1.getSelectedItem().toString();
    	if (type == "User"){
    		policyManager.getPolicyEditor().addUser(comboBoxDisplayElt.getSelectedItem().toString());
    	}
    	if (type == "Role"){
    		policyManager.getPolicyEditor().addRole(comboBoxDisplayElt.getSelectedItem().toString());
    	}
    	if (type == "Permission"){
    		policyManager.getPolicyEditor().addPermission(comboBoxDisplayElt.getSelectedItem().toString());
    	}
    	if (type == "Resource"){
    		policyManager.getPolicyEditor().addObject(comboBoxDisplayElt.getSelectedItem().toString());
    	}
    	if (type == "Session"){
    		policyManager.getPolicyEditor().addSession(comboBoxDisplayElt.getSelectedItem().toString());
    	}
    	policyManager.updateGUI();
    }//GEN-LAST:event_buttonremoveElementActionPerformed

    private void buttonupdateTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonupdateTypeActionPerformed
    	String type =comboBoxTypElt1.getSelectedItem().toString();
    	if (type == "User"){
    		updateComboBox(policyManager.set2Vector(policyManager.getPolicyEditor().getUsersStringSet()), comboBoxDisplayElt);
    	}
    	if (type == "Role"){
    		updateComboBox(policyManager.set2Vector(policyManager.getPolicyEditor().getRolesStringSet()), comboBoxDisplayElt);
    	}
    	if (type == "Permission"){
    		updateComboBox(policyManager.set2Vector(policyManager.getPolicyEditor().getPermissionsStringSet()), comboBoxDisplayElt);
    	}
    	if (type == "Resource"){
    		updateComboBox(policyManager.set2Vector(policyManager.getPolicyEditor().getResourcesStringSet()), comboBoxDisplayElt);
    	}
    	if (type == "Session"){
    		updateComboBox(policyManager.set2Vector(policyManager.getPolicyEditor().getSessionsStringSet()), comboBoxDisplayElt);
    	}
    	policyManager.updateGUI();
    }//GEN-LAST:event_buttonupdateTypeActionPerformed

    private void buttonApplyActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonApplyActionActionPerformed
    	String type =comboBoxAction.getSelectedItem().toString();
    	if (type == "transform"){
    		policyManager.transform();
    	}
    	if (type == "generate"){
    		policyManager.generate(Integer.parseInt(jSpinner1.getValue().toString()),Integer.parseInt(jSpinner2.getValue().toString()),Integer.parseInt(jSpinner3.getValue().toString()),Integer.parseInt(jSpinner4.getValue().toString()),Integer.parseInt(jSpinner5.getValue().toString()),Integer.parseInt(jSpinner6.getValue().toString()));
    	}
    	if (type == "check"){
    		policyManager.check();
    	}
    	if (type == "display"){
    		policyManager.display();
    	}
    	if (type == "addUsers"){
    		policyManager.addUsers((Integer.parseInt(jSpinner1.getValue().toString())));
    	}
    	policyManager.updateGUI();
    }//GEN-LAST:event_buttonApplyActionActionPerformed

    private void buttonaddUserRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddUserRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonaddUserRoleActionPerformed

    private void buttonremoveUserRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonremoveUserRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonremoveUserRoleActionPerformed

    private void buttonremoeveRolePermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonremoeveRolePermActionPerformed
    	policyManager.getPolicyEditor().removeRolePermissionAssignment(comboBoxRole3.getSelectedItem().toString(), comboBoxPerm1.getSelectedItem().toString());
    }//GEN-LAST:event_buttonremoeveRolePermActionPerformed

    private void buttonaddPermOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddPermOpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonaddPermOpActionPerformed

    private void buttonremovePermOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonremovePermOpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonremovePermOpActionPerformed

    private void buttonaddPermOpObActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddPermOpObActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonaddPermOpObActionPerformed

    private void buttonremovePermOpObActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonremovePermOpObActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonremovePermOpObActionPerformed

    private void buttonupdateOpObActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonupdateOpObActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonupdateOpObActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PolicyManagerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonApplyAction;
    private javax.swing.JButton buttonaddElement;
    private javax.swing.JButton buttonaddPermOp;
    private javax.swing.JButton buttonaddPermOpOb;
    private javax.swing.JButton buttonaddRolePerm;
    private javax.swing.JButton buttonaddUserRole;
    private javax.swing.JButton buttonremoeveRolePerm;
    private javax.swing.JButton buttonremoveElement;
    private javax.swing.JButton buttonremovePermOp;
    private javax.swing.JButton buttonremovePermOpOb;
    private javax.swing.JButton buttonremoveUserRole;
    private javax.swing.JButton buttonupdateOpOb;
    private javax.swing.JButton buttonupdateType;
    private javax.swing.JComboBox comboBoxAction;
    private javax.swing.JComboBox comboBoxDisplayElt;
    private javax.swing.JComboBox comboBoxOb;
    private javax.swing.JComboBox comboBoxOb1;
    private javax.swing.JComboBox comboBoxOpPerm;
    private javax.swing.JComboBox comboBoxOpPerm1;
    private javax.swing.JComboBox comboBoxOp2;
    private javax.swing.JComboBox comboBoxPerm;
    private javax.swing.JComboBox comboBoxPerm1;
    private javax.swing.JComboBox comboBoxPerm2;
    private javax.swing.JComboBox comboBoxPerm3;
    private javax.swing.JComboBox comboBoxPerm4;
    private javax.swing.JComboBox comboBoxPerm5;
    private javax.swing.JComboBox comboBoxRole;
    private javax.swing.JComboBox comboBoxRole1;
    private javax.swing.JComboBox comboBoxRole2;
    private javax.swing.JComboBox comboBoxRole3;
    private javax.swing.JComboBox comboBoxTypElt;
    private javax.swing.JComboBox comboBoxTypElt1;
    private javax.swing.JComboBox comboBoxUser;
    private javax.swing.JComboBox comboBoxUser1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea textAreaOutput;
    private javax.swing.JTextField textFieldEltName;
    private javax.swing.JTextField textFieldOpName;
    // End of variables declaration//GEN-END:variables

}
