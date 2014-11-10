import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *  Global alignment with Affine gap penalty.
 *
 */
public class GlobalAffine {
	// Scoring matrix.
	ScoringMatrix scoreMat;
	Cell maxCell = new Cell(0, null);

	public GlobalAffine(ScoringMatrix mat) {
		this.scoreMat = mat;
	}

	/**
	 * Initialize the score matrix for global alignment.
	 * @param sequences
	 */
	public void allignGlobally(ArrayList<String> sequences) {
		initGlobal();
		populateMatrix(sequences);
		// printMatrix();
		try {
			printSequences(backtrack(sequences));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Print sequences after alignment.
	 * @param seqs
	 */
	private void printSequences(String seqs) throws IOException {
		String[] alignedSeqs = seqs.split(";");
		String mToken = "";
		for (int i = 0; i < alignedSeqs[0].length(); i++)
			if (alignedSeqs[0].substring(i, i+1).equals(alignedSeqs[1].substring(i, i+1)))
				mToken += "|";
			else
				mToken += " ";
	
		// users can comment those lines, this code is used to save the file to .txt 				
		System.out.println("\n\nGlobally aligned sequences (Affine):");
		
		//WriteFile("Globally aligned sequences: "+"\n");
		//output the sequence 60 bases in one row
		
		File file = new File("./output_NeedlemanAffine.txt");
		FileOutputStream fw1 = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fw1));
		bw.write("Globally aligned sequences (Affine):"+"\n");
		int temp=0;
		for(int i = 0; i < alignedSeqs[0].length();)
		{
			String as0 = "", mt = "", as1 = "";
			temp +=1;
			for(int j = i; j < i + 60; j++){
				if (j<=alignedSeqs[0].length()-1)
				{
					as0 += alignedSeqs[0].substring(j, j+1);
					
					mt += mToken.substring(j, j+1);
					as1 += alignedSeqs[1].substring(j, j+1);
				}
			}
			if( 60*temp<=alignedSeqs[0].length())
			{
				as0 += (" "+String.valueOf(60*temp));
				mt  += "  ";
				as1 += "  ";
			} else
			{
				as0 += (" "+String.valueOf(alignedSeqs[0].length()));
				mt  += "  ";
				as1 += "  ";
			}
			System.out.println("\n" + as0 + "\n" + mt + "\n" + as1 + "\n\n");
			bw.write("\n" + as0 + "\n" + mt + "\n" + as1 + "\n\n");
			bw.newLine();
			//WriteFile("\n" + mt + "\n");
			//WriteFile("\n" + as1 + "\n\n");
			i += 60;
		}
		bw.close();
	}

	/**
	 * Backtrack from the last cell.
	 * @param sequences
	 * @return
	 */
	private String backtrack(ArrayList<String> sequences) {
		char[] seq0 = sequences.get(0).toUpperCase().toCharArray();
		char[] seq1 = sequences.get(1).toUpperCase().toCharArray();

		int i = this.scoreMat.m - 1;
		int j = this.scoreMat.n - 1;

		String s0 = "", s1 = "";

		while (true) {
			if (this.maxCell.pointer == null)
				break;

			if (this.maxCell.pointer.contains("\\")) {
				s0 = seq0[i - 1] + s0;
				s1 = seq1[j - 1] + s1;
				i--;
				j--;
			} else if (this.maxCell.pointer.contains("^")) {
				s0 = seq0[i - 1] + s0;
				s1 = "-" + s1;
				i--;
			} else {
				s0 = "-" + s0;
				s1 = seq1[j - 1] + s1;
				j--;
			}
			if (this.maxCell.pointer.contains("m"))
				this.maxCell = this.scoreMat.matrix[i][j];
			else if (this.maxCell.pointer.contains("i"))
				this.maxCell = this.scoreMat.I[i][j];
			else if (this.maxCell.pointer.contains("d"))
				this.maxCell = this.scoreMat.D[i][j];
		}
		return s0 + ";" + s1;
	}

	/**
	 * Initialize the scoring matrix with affine gaps values.
	 */
	void initGlobal() {
		for (int i = 1; i < this.scoreMat.m; i++) {
			for (int j = 1; j < this.scoreMat.n; j++) {
				this.scoreMat.matrix[i][j] = new Cell(0, null);
			}
		}
		for (int i = 1; i < this.scoreMat.m; i++) {
			this.scoreMat.matrix[i][0] = new Cell(0, "m^");
			this.scoreMat.I[i][0] = new Cell((this.scoreMat.d + this.scoreMat.e
					* (i)), "i^");
			this.scoreMat.D[i][0] = new Cell(Double.NEGATIVE_INFINITY, null);
		}
		for (int j = 1; j < this.scoreMat.n; j++) {
			this.scoreMat.matrix[0][j] = new Cell(0, "m<");
			this.scoreMat.I[0][j] = new Cell(Double.NEGATIVE_INFINITY, null);
			this.scoreMat.D[0][j] = new Cell((this.scoreMat.d + this.scoreMat.e
					* (j)), "d<");
		}
	}

	/**
	 * Populate scoring matrix, Ix, Iy
	 * @param sequences
	 */
	void populateMatrix(ArrayList<String> sequences) {
		char[] seq0 = sequences.get(0).toUpperCase().toCharArray();
		char[] seq1 = sequences.get(1).toUpperCase().toCharArray();

		for (int i = 1; i < this.scoreMat.m; i++) {
			for (int j = 1; j < this.scoreMat.n; j++) {
				this.scoreMat.I[i][j] = populateCellI(
						this.scoreMat.matrix[i - 1][j],
						this.scoreMat.I[i - 1][j]);

				this.scoreMat.D[i][j] = populateCellD(
						this.scoreMat.matrix[i][j - 1],
						this.scoreMat.I[i][j - 1]);

				this.scoreMat.matrix[i][j] = populateCell(
						this.scoreMat.D[i - 1][j - 1],
						this.scoreMat.matrix[i - 1][j - 1],
						this.scoreMat.I[i - 1][j - 1], seq0[i - 1] + "",
						seq1[j - 1] + "");
			}
		}

		int i = this.scoreMat.m - 1;
		int j = this.scoreMat.n - 1;

		if (this.scoreMat.I[i][j].value > this.scoreMat.D[i][j].value
				&& this.scoreMat.I[i][j].value > this.scoreMat.matrix[i][j].value) {
			this.maxCell = this.scoreMat.I[i][j];
			this.scoreMat.maxI = i;
			this.scoreMat.maxJ = j;
		} else if (this.scoreMat.D[i][j].value > this.scoreMat.matrix[i][j].value) {
			this.maxCell = this.scoreMat.D[i][j];
			this.scoreMat.maxI = i;
			this.scoreMat.maxJ = j;
		} else {
			this.maxCell = this.scoreMat.matrix[i][j];
			this.scoreMat.maxI = i;
			this.scoreMat.maxJ = j;
		}
	}

	private Cell populateCellD(Cell M, Cell D) {
		double m = M.value - this.scoreMat.d;
		double d = D.value - this.scoreMat.e;

		if (m > d)
			return new Cell(m, "m<");
		return new Cell(d, "d<");

	}

	private Cell populateCellI(Cell M, Cell I) {
		double m = M.value - this.scoreMat.d;
		double i = I.value - this.scoreMat.e;

		if (m > i)
			return new Cell(m, "m^");
		return new Cell(i, "i^");
	}

	private Cell populateCell(Cell D, Cell M, Cell I, String s0, String s1) {

		double dScore, lScore, uScore;
		double s, d, m, i;

		if (this.scoreMat.useSubstitutionMatrix)
			s = RealMatrix.getScoreValue(s0, s1);
		else {
			if (s0.equals(s1)) {
				s = this.scoreMat.match;
			} else
				s = this.scoreMat.mismatch;
		}

		uScore = D.value + s;
		dScore = M.value + s;
		lScore = I.value + s;

		if (dScore >= uScore) {
			if (dScore >= lScore)
				return new Cell(dScore, "m\\");
			else
				return new Cell(lScore, "i\\");
		} else {
			if (uScore >= lScore)
				return new Cell(uScore, "d\\");
			else
				return new Cell(lScore, "i\\");
		}
	}

	/**
	 * Print scoring matrix.
	 */
	void printMatrix() {
		System.out.println("\nMatrix:");
		for (int i = 0; i < this.scoreMat.m; i++) {
			for (int j = 0; j < this.scoreMat.n; j++) {
				System.out.print(this.scoreMat.matrix[i][j].pointer + "\t");
			}
			System.out.print("\n");
		}
	}
}
