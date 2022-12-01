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

		boolean visited[][] = new boolean[width][height];

		if (isOutOfBounds(x,y)) 
			return;

		if (visited[x][y])
			return;

		visited[x][y] = true;    
		Color cellColor = cellRenderer.getCellColor(x, y);
		if (cellColor != currentColor)
			return;

		this.fill(x,y);

		recursiveFloodFill(x+1, y, currentColor);
		recursiveFloodFill(x-1, y, currentColor);
		recursiveFloodFill(x, y+1, currentColor);
		recursiveFloodFill(x, y-1, currentColor);
	}

	public void queueFloodFill(int x, int y, Color currentColor) throws InterruptedException {

		boolean visited[][] = new boolean[this.width][this.height];

		LinkedList<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));

		while (queue.isEmpty() == false) {
			Point currentPoint = queue.remove();

			if (isOutOfBounds(currentPoint.x, currentPoint.y)) 
				continue;


			if (visited[currentPoint.x][currentPoint.y])
				continue;

			visited[currentPoint.x][currentPoint.y] = true;    
			Color cellColor = cellRenderer.getCellColor(currentPoint.x, currentPoint.y);
			if (cellColor != currentColor)
				continue;

			this.fill(currentPoint.x, currentPoint.y);

			queue.add(new Point(currentPoint.x + 1, currentPoint.y)); // direita
			queue.add(new Point(currentPoint.x - 1, currentPoint.y)); // esquerda
			queue.add(new Point(currentPoint.x, currentPoint.y + 1)); // acima
			queue.add(new Point(currentPoint.x, currentPoint.y - 1)); // abaixo
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