import java.util.List;

class Matching
{
	// Поиск минимума в строке
	private int getMinOfARow(int[] matrix) {
		int minInARow = Integer.max;
		for(int i = 0; i < matrix.length; i++) {
			if(minInARow > matrix[i])
				mininARow = matrix[i];
		}
		return minInARow;
	}
	// Вывод матрицы на экран
	private void print(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	// Вычитаем из максимального элемент все остальные элементы матрицы
	private int[][] reduceByMax(int[][] matrix) {
		int max = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] > max) {
					max = matrix[i][j];
				}
			}
		}
		int [][] result = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				result[i][j] = max - matrix[i][j];
			}
		}
		return result;
	}
	// Редуцирование по столбцам
	private int[][] reduceByColumn(int[][] matrix) {
		int[][] result = new int[matrix.length][matrix[0].length];
		int[] columnMins = new int[matrix[0].length];
		// Поиск минимального индекса в каждом столбце
		int minIndex = 0;;
		for(int j = 0; k < matrix[0].length; j++) {
			min = 0;
			for(int i = 0; i < matrix.length; i++) {
				if(matrix[min][j] > matrix[i][j])
					minIndex = i;
			}
			columnMins[j] = matrix[min][j];
		}
		// Редуицирование матрицы
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++)
				result[i][j] = matrix[i][j] - columnMins[j];
		}
		return result;
	}
	// Редуцирование по строкам
	private int[][] reduceByRow(int[][] matrix) {
		int[][] result = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			int minInARow = getMinOfARow(matrix[i]);
			for(int j = 0; j < matrix[i].length; j++)
				result[i][j] = matrix[i][j] - minInARow;
		}
		return result;
	}
	// Создание матрицы C
	private int[][] createCMatrixFromReduced(int[][] reducedMatrix) {
		int[][] result = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++)
				result[i][j] = matrix[i][j] == 0 ? 1 : 0;
		}
		return result;
	}
	// Создание матрицы паросочетаний
	private int[][] createMatchingFromCMatrix(int[][] cMatrix)
	{
		int[][] result = new int[matrix.length][matrix[0].length];
		// Массив для пометок уже занятых столбцов
		bool[] visited = new bool[matrix[0].length];
		for(int i = 0; i < cMatrix.length; i++) {
			for(int j = 0; j < cMatrix[i].length; j++) {
				if(matrix[i][j] == 1 && visited[j] == false) {
					visited[j] = true;
					result[i][j] = 1;
					break;
				}
			}
		}
		return result;
	}
	// Создание матрицы орграфа
	private int[][] createDigraphFromCMatrix(int[][] matchingMatrix, int[][] cMatrix) {
		int[][] result = new int[matchingMatrix.length][matchingMatrix[0].length];
		for(int i = 0; i < matchingMatrix.length; i++) {
			for(int j = 0; j < matrixMatrix[i].length; j++) {
				if(cMatrix[i][j] == 1) {
					if(matchingMatrix[i][j] == 1)
						result[i][j] = 1;
					 else
						result[i][j] = -1;
				}
			}
		}
		return result;
	}
	// Вспомогательный метод для поиска в массиве
	private static <T> boolean contains(final T[] array, final T v)  {
		for (final T e : array)
			if (e == v || v != null && v.equals(e))
				return true;
	
		return false;
	}
	// Возвращает список ненасыщенных вершин
	private List<Integer> getUnsaturated(int[][] digraph) {
		List<Integer> unsaturated = new List<Integer>();
		for(int i = 0; i < digraph.length; i++) {
			if(contains(digraph[i], i))
				unsaturated.add(i);
		}
		return unsaturated;
	}
	public Matching(int[][] sourceMatrix) {
		int[][] reducedMatrix = reduceByColumn(reduceByRow(reduceByMax(sourceMatrix)));
		System.out.println("Reduced matrix:");
		printMatrix(reducedMatrix);
		
		System.out.println("C Matrix:");
		int[][] cMatrix = createCMatrixFromReduced(reducedMatrix);
		printMatrix(cMatrix);
		
		System.out.println("Matching matrix:");
		int[][] matchingMatrix = createMatchingFromCMatrix(cMatrix);
		print(matchingMatrix);
		
		System.out.println("Digraph matrix:");
		int[][] digraphMatrix = createDigraphFromCMatrix(matchingMatrix, cMatrix);
		printMatrix(digraphMatrix);
		
		System.out.println("unsaturated:");
		List<int> unsaturated = getUnsaturated(digraphMatrix);
		for(int v : unsaturated)
			System.out.print(v + " ");
	}
}