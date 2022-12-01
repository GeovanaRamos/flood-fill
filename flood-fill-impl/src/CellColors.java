import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CellColors {
	private final Map<Point, Color> cellColors = new HashMap<Point, Color>();

	public CellColors(int rows, int cols) {

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				cellColors.put(new Point(r,c), getRandomColor());
			}
		}
	}

	private Color getRandomColor(){
		Random rand = new Random();
		Color color;

		int clrNum = rand.nextInt(3);

		if(clrNum == 0) 
			color = Color.RED;
		else if(clrNum == 1) 
			color = Color.BLUE;
		else 
			color = Color.YELLOW;


		return color;
	}

	public Map<Point, Color>  getCellColors() {
		return cellColors;
	}

}
