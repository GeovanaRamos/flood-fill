import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JTable;


class Canvas{ 
	private int width;
	private int height;
	private ColoringCellRenderer cellRenderer;
	private JTable table;
	private Color newColor = Color.BLACK;

	public Canvas(int rows, int cols, ColoringCellRenderer cellRenderer, JTable table) {
		this.height = rows;
		this.width = cols;
		this.cellRenderer = cellRenderer;
		this.table = table;
	}

	private void fill(int x, int y) throws InterruptedException {
		cellRenderer.setCellColor(x, y, newColor);
		table.repaint();
		Thread.sleep(200);
	}
	
	private boolean isOutOfBounds(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return true;
					
		return false;
	}


	public void recursiveFloodFill(int x, int y, Color currentColor) throws InterruptedException {

		boolean visited[][] = new boolean[width][height];	// 1 instrução

		if (isOutOfBounds(x,y)) 							// 1 instrução
			return;						// 1 instrução

		if (visited[x][y])									// 1 instrução
			return;						// 1 instrução

		visited[x][y] = true;    							// 1 instrução
		Color cellColor = cellRenderer.getCellColor(x, y);	// 1 instrução
		if (cellColor != currentColor)						// 1 instrução
			return;						// 1 instrução

		this.fill(x,y);										// 1 instrução

		recursiveFloodFill(x+1, y, currentColor); 		// (row - x - 1) * 21 * col instruções
		recursiveFloodFill(x-1, y, currentColor); 		// x * 21 * col instruções
		recursiveFloodFill(x, y+1, currentColor); 		// (col - y - 1) * 21 instruções
		recursiveFloodFill(x, y-1, currentColor); 		// y * 21 instruções

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Pior caso (preencher todos os elementos do canvas)

		// T(n) = 7 + [(row - x - 1) * 7 * col] + [x * 7 * col] + [(col - y - 1) * 7] + [y * 7]
		// T(n) = 7 + [7rowcol - 7xcol - 7col] + [7xcol] + [7col - 7y - 21] + [7y]
		// T(n) = 7 + 7rowcol - 7
		// T(n) = 7 + row * col

		// Nessa implementação os canvas são quadrados. Tomando a altura dos mesmos como "n" temos:
		// O(n²)
		// Complexidade Quadrática

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Melhor caso (preencher apenas um elemento do canto)
		// Obs.: Quando fora do canvas, 3 instruções são executadas
		//		 Quando cellColor != currentColor, 7 instruções são executadas

		// T(n) =  7   +     7     +    3      +     7     +    3
		//        fill   sameColor  outOfBound   sameColor  outOfBound
		// T(n) = 17 instruções
		//
		// O(1)
		// Complexidade Constante
	}

	public void queueFloodFill(int x, int y, Color currentColor) throws InterruptedException {


		boolean visited[][] = new boolean[this.width][this.height]; // 1 instrução

		LinkedList<Point> queue = new LinkedList<Point>();				// 1 instrução
		queue.add(new Point(x, y));									// 1 instrução

		// O bloco executa n vezes
		// Onde n = número de nós a serem preenchidos
		while (queue.isEmpty() == false) {							// 1 instrução
			Point currentPoint = queue.remove();					// 1 instrução

			if (isOutOfBounds(currentPoint.x, currentPoint.y)) 		// 1 instrução
				continue;				// 1 instrução


			if (visited[currentPoint.x][currentPoint.y])			// 1 instrução
				continue;				// 1 instrução

			visited[currentPoint.x][currentPoint.y] = true;    		// 1 instrução
			Color cellColor = cellRenderer
					.getCellColor(currentPoint.x, currentPoint.y); 	// 1 instrução
			if (cellColor != currentColor)							// 1 instrução
				continue;				// 1 instrução

			this.fill(currentPoint.x, currentPoint.y);				// 1 instrução

			queue.add(new Point(currentPoint.x + 1, currentPoint.y)); // 1 instrução
			queue.add(new Point(currentPoint.x - 1, currentPoint.y)); // 1 instrução
			queue.add(new Point(currentPoint.x, currentPoint.y + 1)); // 1 instrução
			queue.add(new Point(currentPoint.x, currentPoint.y - 1)); // 1 instrução

			//Total do bloco while = 12 instruções * nós a serem preenchidos

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Pior caso (preencher todos os elementos do canvas)

		// T(n) = 3 + 12 * (row * col)

		// Nessa implementação os canvas são quadrados. Tomando a altura dos mesmos como "n" temos:
		// O(n²)
		// Complexidade Quadrática

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Melhor caso (preencher apenas um elemento do canto)
		// Obs.: Quando fora do canvas, 4 instruções são executadas
		//		 Quando cellColor != currentColor, 8 instruções são executadas

		// T(n) =   3      +    8     +    4     +     4     +      8     +     4     +      8
		//    inicializacao    fill    buildQueue   outOfBound   sameColor  outOfBound   sameColor
		// T(n) = 39 instruções
		//
		// O(1)
		// Complexidade Constante

		}

	}
	
	public void stackFloodFill(int x, int y, Color currentColor) throws InterruptedException {

		boolean visited[][] = new boolean[this.width][this.height];

		Stack<Point> stack = new Stack<Point>();
		stack.push(new Point(x, y));

		while (stack.isEmpty() == false) {
			Point currentPoint = stack.pop();

			if (isOutOfBounds(currentPoint.x, currentPoint.y)) 
				continue;


			if (visited[currentPoint.x][currentPoint.y])
				continue;

			visited[currentPoint.x][currentPoint.y] = true;    
			Color cellColor = cellRenderer.getCellColor(currentPoint.x, currentPoint.y);
			if (cellColor != currentColor)
				continue;

			this.fill(currentPoint.x, currentPoint.y);

			stack.push(new Point(currentPoint.x + 1, currentPoint.y)); 
			stack.push(new Point(currentPoint.x - 1, currentPoint.y)); 
			stack.push(new Point(currentPoint.x, currentPoint.y + 1)); 
			stack.push(new Point(currentPoint.x, currentPoint.y - 1)); 
		}

	}
}