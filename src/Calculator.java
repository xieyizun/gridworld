package xyz_shixun;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Calculator extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * num1 and num1 is the two numbers that need to be calculated
	 * sign is the calculator operation
	 * result is the result after calculating.
	 */
	private JPanel contentPane;
	private JTextField num1;
	private JTextField num2;
	private JTextField sign;
	private JTextField equal;
	private JTextField result;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * because swing is not thread safe, this method is to 
		 * guarantee that swing is thread safe.
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Calculator() {
		/*
		 * the following code is to define the layout of the frame.
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,400, 200);
		setTitle("Easy Calculator");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new GridLayout(2,5,10,10));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,5,10,10));
		contentPane.add(panel);
		/*
		 * the following code is to define num1,num2,sign, equal and result text field
		 * and add them to the JPanel element panel
		 */
		num1 = new JTextField();
		num2 = new JTextField();
		
		sign = new JTextField();
		sign.setFont(new Font("ok", Font.PLAIN, 25));
		sign.setHorizontalAlignment(JTextField.CENTER);
		sign.setEditable(false);
		
		equal = new JTextField("=");
		equal.setFont(new Font("ok", Font.PLAIN, 25));
		equal.setHorizontalAlignment(JTextField.CENTER);
		equal.setEditable(false);
		
		result = new JTextField();
		result.setEditable(false);
		
		panel.add(num1);
		panel.add(sign);
		panel.add(num2);
		panel.add(equal);
		panel.add(result);
		
		/*
		 * the following code is to define the four calculating operators, including 
		 * add +, subtraction -, multiply * and division / and add them to panel, too.
		 */
		JButton add = new JButton("+");
		add.setFont(new Font("ok", Font.PLAIN, 20));
		panel.add(add);
		
		JButton subtraction = new JButton("-");
		subtraction.setFont(new Font("ok", Font.PLAIN, 20));
		panel.add(subtraction);
		
		JButton mult = new JButton("*");
		mult.setFont(new Font("ok", Font.PLAIN, 20));
		panel.add(mult);
		
		JButton division = new JButton("/");
		division.setFont(new Font("ok", Font.PLAIN, 20));
		panel.add(division);
		
		JButton ok = new JButton("OK");
		ok.setFont(new Font("ok", Font.PLAIN, 15));
		panel.add(ok);
		
		/*
		 * the following code is to add ActionListener to the operations add, subtraction
		 * multiply, division and okay button, so that when we click the button, it can the
		 * according calculating operation. 
		 */
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_add_operation();
			}
			
		});
		subtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_subtraction_operation();
			}
		});
		mult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mult_operation();
			}		
		});
		division.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_division_operation();
			}			
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_ok_operation();
			}
			
		});
	}
	/*
	 * the following code is to define methods that be invoked by the above actionListeners
	 * and perform the according calculation operations.
	 */
	protected void do_add_operation() {	
		sign.setText("+");
	}
	protected void do_subtraction_operation() {	
		sign.setText("-");
	}
	protected void do_mult_operation() {
		sign.setText("*");
	
	}
	protected void do_division_operation() {
		sign.setText("/");
	}
	protected void do_ok_operation(){
		/*
		 * the if statement is to deal with the illegal operations and warn the users.
		 */
		if ("".equals(num1.getText()) || "".equals(num2.getText()) || "".equals(sign.getText()) || (("/".equals(sign.getText()) && ("0".equals(num2.getText()))))) {
			JOptionPane.showMessageDialog(this, "Input Error", "Please input correctly", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		double _num1 = Double.parseDouble(num1.getText());
		double _num2 = Double.parseDouble(num2.getText());
		double _result = 0;
		String _sign = sign.getText();
		if (_sign.equals("+")) {
		    _result = _num1 + _num2;
		} else if (_sign.equals("-")) {
		    _result = _num1 - _num2;
		} else if (_sign.equals("*")) {
		    _result = _num1 * _num2;
		} else if (_sign.equals("/")) {
		    _result = _num1 / _num2;
		}
		result.setText(Double.toString(_result));
	}
}
