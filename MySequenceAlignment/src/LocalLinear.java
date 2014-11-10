import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Local alignment for linear gaps.
 *
 */
public class LocalLinear {
	ScoringMatrix scoreMat;

	public LocalLinear(ScoringMatrix mat) {
		this.scoreMat = mat;
	}

	public void allignLocally(ArrayList<String> sequences) {
		initLocal();
		populateMatrix(sequences);
		//printMatrix();
		try {
			printSequences(backtrack(sequences));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printSequences(String seqs) throws IOException {
		String[] alignedSeqs = seqs.split(";");
		String mToken = "";
		for (int i = 0; i < alignedSeqs[0].length(); i++)
			if (alignedSeqs[0].substring(i, i+1).equals(alignedSeqs[1].substring(i, i+1)))
				mToken += "|";
			else
				mToken += " ";
	
		// users can comment those lines, this code is used to save the file to .txt 				
		System.out.println("\n\nlocally aligned sequences (linear):");
		
		//WriteFile("Globally aligned sequences: "+"\n");
		//output the sequence 60 bases in one row
		
		File file = new File("./output_SmithLinear.txt");
		FileOutputStream fw1 = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fw1));
		bw.write("Locally aligned sequences (linear):"+"\n");
		int temp=0;
		for(int i = 0; i < alignedSeqs[0].length();)
		{
			String as0 = "", mt = "", as1 = "";
			temp +=1;
			for(int j = i; j < i + 60; j++){
				if (j<=alignedSeqs[0].length()-1)
				{
					as0 += alignedSeqs[0].substring(j, j+1);
					mt  += mToken.substring(j, j+1);
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

	private String backtrack(ArrayList<String> sequences) {
		char[] seq0 = sequences.get(0).toUpperCase().toCharArray();
		char[] seq1 = sequences.get(1).toUpperCase().toCharArray();

		int i = this.scoreMat.maxI;
		int j = this.scoreMat.maxJ;

		String s0 = "", s1 = "";

		while (true) {
			if (this.scoreMat.matrix[i][j].pointer == null
					|| this.scoreMat.matrix[i][j].value == 0)
				break;

			if (this.scoreMat.matrix[i][j].pointer.equals("\\")) {
				s0 = seq0[i - 1] + s0;
				s1 = seq1[j - 1] + s1;
				i--;
				j--;
			} else if (this.scoreMat.matrix[i][j].pointer.equals("^")) {
				s0 = seq0[i - 1] + s0;
				s1 = "-" + s1;
				i--;
			} else {
				s0 = "-" + s0;
				s1 = seq1[j - 1] + s1;
				j--;
			}
		}
		return s0 + ";" + s1;
	}

	void initLocal() {
		for (int i = 1; i < this.scoreMat.m; i++) {
			this.scoreMat.matrix[i][0] = new Cell(0, null);
		}
		for (int j = 1; j < this.scoreMat.n; j++) {
			this.scoreMat.matrix[0][j] = new Cell(0, null);
		}
	}

	void populateMatrix(ArrayList<String> sequences) {
		char[] seq0 = sequences.get(0).toUpperCase().toCharArray();
		char[] seq1 = sequences.get(1).toUpperCase().toCharArray();

		for (int i = 1; i < this.scoreMat.m; i++) {
			for (int j = 1; j < this.scoreMat.n; j++) {
				this.scoreMat.matrix[i][j] = populateCell(
						this.scoreMat.matrix[i][j - 1],
						this.scoreMat.matrix[i - 1][j - 1],
						this.scoreMat.matrix[i - 1][j], seq0[i - 1] + "",
						seq1[j - 1] + "");

				if (this.scoreMat.matrix[i][j].value > this.scoreMat.maxValue) {
					this.scoreMat.maxValue = this.scoreMat.matrix[i][j].value;
					this.scoreMat.maxI = i;
					this.scoreMat.maxJ = j;
				}
			}
		}
	}

	private Cell populateCell(Cell cellLeft, Cell cellDiag, Cell cellUp,
			String s0, String s1) {

		double dScore, lScore, uScore;

		if (this.scoreMat.useSubstitutionMatrix)
			dScore = cellDiag.value + RealMatrix.getScoreValue(s0, s1);
		else {
			if (s0.equals(s1)) {
				dScore = cellDiag.value + this.scoreMat.match;
			} else
				dScore = cellDiag.value + this.scoreMat.mismatch;
		}
		lScore = cellLeft.value + this.scoreMat.gap;
		uScore = cellUp.value + this.scoreMat.gap;

		if (dScore <= 0 && lScore <= 0 && uScore <= 0)
			return new Cell(0, null);
		if (dScore >= uScore) {
			if (dScore >= lScore)
				return new Cell(dScore, "\\");
			else
				return new Cell(lScore, "<");
		} else {
			if (uScore >= lScore)
				return new Cell(uScore, "^");
			else
				return new Cell(lScore, "<");
		}
	}

	void printMatrix() {
		System.out.println("\nMatrix:");
		for (int i = 0; i < this.scoreMat.m; i++) {
			for (int j = 0; j < this.scoreMat.n; j++) {
				System.out.print(this.scoreMat.matrix[i][j].value + "\t");
			}
			System.out.print("\n");
		}
	}
}


