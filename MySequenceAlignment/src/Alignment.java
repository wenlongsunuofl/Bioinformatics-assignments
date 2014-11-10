import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * CECS-660 Introduction to Bioinformatics, Project 1: sequences alignment
 * Alignment.java 
 * @author: wenlong 
 */
public class Alignment {
	
	static double match = 5;       // parameters for alignment                                        
	static double mismatch = -3;
	static double gap = -4;
	static double open = -12;      // Those parameters aredefault, user can change them in parameter.txt file
	static double extend = -4;
	static String matrixName = "PAM250";                     // When protein sequence is used, PAM 250 scoring matrix will be used
	static String input = "";
	static boolean useSubsMat = false; 			              // When protein sequence is used, PAM 250 will be used, this para needs to be true.
	static boolean applyAffine = false;					// Decide to use affine or not
	private static BufferedReader br;
	
	public static void main(String args[]) {
		/*
		 * First set up all the parameters needed
         * This program starts here
		 */
		// Read the configuration file.
		new RealMatrix(matrixName);	                      // In order to read the score in the PAM table later
		Sequences seqs = new Sequences();		
		try {
			readConfig();
		} catch (FileNotFoundException e) {
			System.out.println("Cound not find the configuratin file: 'run.config'");
		} catch (IOException e) {
			System.out.println("Cound read the config file: 'run.config'");
		}
		// Read the sequences from the input file. only the first two are read.
		seqs.readSequences(input);

		// This program gives results based on different combinations 2X2=4: global or local, Affine or Not

		
		//
		ScoringMatrix mat = new ScoringMatrix(seqs.sequences.get(0).length() + 1,
				seqs.sequences.get(1).length() + 1, match, mismatch, gap,
				useSubsMat);

		GlobalLinear nw0 = new GlobalLinear(mat);
		nw0.allignGlobally(seqs.sequences);

		LocalLinear sw0 = new LocalLinear(mat);
		sw0.allignLocally(seqs.sequences);
		
		if (applyAffine){
			// when apply Affine gap penalty
			// the parameters also needed just like the normal one
			ScoringMatrix mat1 = new ScoringMatrix(seqs.sequences.get(0).length() + 1,
					seqs.sequences.get(1).length() + 1, match, mismatch, open,
					extend, useSubsMat);
			
			GlobalAffine nw = new GlobalAffine(mat1);
			nw.allignGlobally(seqs.sequences);
		}
		
		
		System.out.println("\n\n Alignment is finished!");
	}
	private static void readConfig() throws IOException {
		br = new BufferedReader(new FileReader("parameter.txt"));
		String line = "";

		while ((line = br.readLine()) != null) {

			if (line.startsWith("#"))
				continue;
			if (line.startsWith("#Comments"))
				break;

			if (line.startsWith("input-file")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					input = opts1[1].trim();
				}
			}  else if (line.startsWith("substitution-matrix")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					matrixName = opts1[1].trim();
				}
			} else if (line.startsWith("use-substitution-matrix")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					if (opts1[1].trim().equals("yes"))
						useSubsMat = true;
					else
						useSubsMat = false;
				}
			} else if (line.startsWith("use-affine-gap-penalty")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					if (opts1[1].trim().equals("yes"))
						applyAffine = true;
					else
						applyAffine = false;
				}
			} else if (line.startsWith("match")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					match = Double.parseDouble(opts1[1].trim());
				}
			} else if (line.startsWith("mismatch")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					mismatch = Double.parseDouble(opts1[1].trim());
				}
			} else if (line.startsWith("gap")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					gap = Double.parseDouble(opts1[1].trim());
				}
			} else if (line.startsWith("open")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					open = Double.parseDouble(opts1[1].trim());
				}
			} else if (line.startsWith("extend")) {
				String[] opts1 = line.split(":");
				if (opts1.length == 2) {
					extend = Double.parseDouble(opts1[1].trim());
				}
			}
		}
	}
	
}
