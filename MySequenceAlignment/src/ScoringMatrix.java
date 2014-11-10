public class ScoringMatrix {
	Cell[][] matrix;
	Cell[][] D;
	Cell[][] I;

	int m, n;
	double match, mismatch, gap;
	double d, e;
	boolean useSubstitutionMatrix = false;

	int maxI = 0, maxJ = 0;
	double maxValue = 0;

	public ScoringMatrix(int m, int n, double mch, double mmch, double gp,
			boolean useSubMat) {
		matrix = new Cell[m][n];

		this.m = m;
		this.n = n;
		this.match = mch;
		this.mismatch = mmch;
		this.gap = gp;

		this.useSubstitutionMatrix = useSubMat;
		matrix[0][0] = new Cell(0, null);
	}

	public ScoringMatrix(int m, int n, double mch, double mmch, double md,
			double me, boolean useSubMat) {
		matrix = new Cell[m][n];
		I = new Cell[m][n];
		D = new Cell[m][n];
		this.m = m;
		this.n = n;
		this.match = mch;
		this.mismatch = mmch;
		this.d = md;
		this.e = me;

		this.useSubstitutionMatrix = useSubMat;
		matrix[0][0] = new Cell(0, null);
		D[0][0] = new Cell(0, null);
		I[0][0] = new Cell(0, null);
	}

	/*void printMatrixLinear() {
		System.out.println("\nMatrix:");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j].value + "\t");
			}
			System.out.print("\n");
		}
	}*/
}
/**
 * Class to hold the matrices cell information.
 *
 */
class Cell {
	// Value to store the cell values, pointer to the backtrack.
	double value = 0;
	// Possible values for pointer are: \, ^, < and (m, i, d in the front of \, ^, < for affine)
	String pointer = "";

	public Cell(double val, String ptr) {
		this.value = val;
		this.pointer = ptr;
	}
}

