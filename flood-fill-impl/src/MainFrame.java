import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static final int ROWS = 20;
	public static final int COLS = 20;
	JButton currentBtn;
	String currentMethod;

	public MainFrame() {

		setTitle("Flood Fill Implementations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		//Title section
		contentPane.add(Box.createRigidArea(new Dimension(10,50)));
		JLabel lblQuestion = new JLabel("Clique em uma célula");
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblQuestion);
		contentPane.add(Box.createRigidArea(new Dimension(10,200)));


		//Canvas section
		JPanel captionsPane = new JPanel();
		JLabel lblRecursive = new JLabel("Recursivo");
		JLabel lblQueue = new JLabel("Iterativo - Fila");
		JLabel lblStack = new JLabel("Iterativo - Pilha");
		captionsPane.add(lblRecursive);
		captionsPane.add(Box.createHorizontalStrut(200));
		captionsPane.add(lblQueue);
		captionsPane.add(Box.createHorizontalStrut(200));
		captionsPane.add(lblStack);
		contentPane.add(captionsPane);
		
		// Tables
		JPanel tablesPane = new JPanel();
		CellColors cellColors = new CellColors(ROWS, COLS);
		
		// Table 1		
		JTable table = new JTable(ROWS,COLS);
		ColoringCellRenderer cellRenderer = new ColoringCellRenderer(cellColors.getCellColors());
		Canvas canvas = new Canvas(ROWS, COLS, cellRenderer, table);
		
		// Table 2
		JTable table2 = new JTable(ROWS,COLS);
		ColoringCellRenderer cellRenderer2 = new ColoringCellRenderer(cellColors.getCellColors());
		Canvas canvas2 = new Canvas(ROWS, COLS, cellRenderer2, table2);
		
		// Table 3
		JTable table3 = new JTable(ROWS,COLS);
		ColoringCellRenderer cellRenderer3 = new ColoringCellRenderer(cellColors.getCellColors());
		Canvas canvas3 = new Canvas(ROWS, COLS, cellRenderer3, table3);
		
		TableColumnModel columnModel = table.getColumnModel();
		TableColumnModel columnModel2 = table2.getColumnModel();
		TableColumnModel columnModel3 = table3.getColumnModel();
		for (int c=0; c<COLS; c++){
			TableColumn column = columnModel.getColumn(c);
			column.setCellRenderer(cellRenderer);
			column.setMaxWidth(10);
			TableColumn column2 = columnModel2.getColumn(c);
			column2.setCellRenderer(cellRenderer2);
			column2.setMaxWidth(10);
			TableColumn column3 = columnModel3.getColumn(c);
			column3.setCellRenderer(cellRenderer3);
			column3.setMaxWidth(10);
		}
		
		tablesPane.add(table);
		tablesPane.add(Box.createHorizontalStrut(10));
		tablesPane.add(table2);
		tablesPane.add(Box.createHorizontalStrut(10));
		tablesPane.add(table3);

		contentPane.add(tablesPane);


		//Mouse listener for table
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				
				floodFill(canvas, canvas2, canvas3, cellRenderer, row, col);
			}
		});		
		
		//Mouse listener for table
		table2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table2.rowAtPoint(evt.getPoint());
				int col = table2.columnAtPoint(evt.getPoint());
				
				floodFill(canvas, canvas2, canvas3, cellRenderer2, row, col);
			}
		});		
		
		//Mouse listener for table
		table3.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table3.rowAtPoint(evt.getPoint());
				int col = table3.columnAtPoint(evt.getPoint());
				
				floodFill(canvas, canvas2, canvas3, cellRenderer3, row, col);
			}
		});		

	}
	
	private void floodFill(Canvas canvas, Canvas canvas2, Canvas canvas3, ColoringCellRenderer cellRenderer, int row, int col) {

		Color currentColor = cellRenderer.getCellColor(row, col);  

		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("1");
					canvas3.recursiveFloodFill(row, col, currentColor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("2");
					canvas2.queueFloodFill(row, col, currentColor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("3");
					canvas.stackFloodFill(row, col, currentColor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	
	}







}

