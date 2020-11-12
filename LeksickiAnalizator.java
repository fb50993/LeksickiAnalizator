import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class  LeksickiAnalizator {
	
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> listaRedova = new ArrayList<>();
		String line;
		
		while((line = br.readLine()) != null) {
			listaRedova.add(line);
		}
		
		for(int i = 0; i<listaRedova.size(); i++) {
			line = listaRedova.get(i);
			if(line.startsWith("//")) {
				continue;
			}
			char[] charArray = line.toCharArray();
			
			for(int j = 0; j < charArray.length; j++) {
				if(charArray.length!=j+1) {
					String token1 = String.valueOf(charArray[j]);
					String token2 = String.valueOf(charArray[j+1]);
					if(token1.equals("/") && token1.equals(token2)) break;
				}
				if(Character.isWhitespace(charArray[j])) continue;
				if(Character.isLetter(charArray[j])) {
					sb.append(charArray[j]);
					if(charArray.length==j+1) {
						sb.append(" ");
					}
					else if(charArray.length!=j+1 && !(Character.isLetter(charArray[j+1]) || Character.isDigit(charArray[j+1]))) {
						sb.append(" ");
					}
				} else if(Character.isDigit(charArray[j])) {
					sb.append(charArray[j]);
					if(charArray.length==j+1) {
						sb.append(" ");
					}
					else if(charArray.length!=j+1 && Character.isDigit(charArray[j+1])) continue;
					else if(charArray.length!=j+1 && !(Character.isDigit(charArray[j+1]))) {
						sb.append(" ");
					}
					
				} else {
					sb.append(charArray[j]);
					sb.append(" ");
				}
			}
			
			String probni = sb.toString();
			String[] nekoPolje = probni.split(" ");
			for(int a = 0; a < nekoPolje.length; a++ ) {
				if(nekoPolje[a].isBlank()) continue;
				else if(nekoPolje[a].equals("=")) System.out.println("OP_PRIDRUZI "+(i+1)+" =");
				else if(nekoPolje[a].equals("+")) System.out.println("OP_PLUS "+(i+1)+" +");
				else if(nekoPolje[a].equals("-")) System.out.println("OP_MINUS "+(i+1)+" -");
				else if(nekoPolje[a].equals("*")) System.out.println("OP_PUTA "+(i+1)+" *");
				else if(nekoPolje[a].equals("/")) System.out.println("OP_DIJELI "+(i+1)+" /");
				else if(nekoPolje[a].equals("(")) System.out.println("L_ZAGRADA "+(i+1)+" (");
				else if(nekoPolje[a].equals(")")) System.out.println("D_ZAGRADA "+(i+1)+" )");
				else if(nekoPolje[a].equals("za")) System.out.println("KR_ZA "+(i+1)+" za");
				else if(nekoPolje[a].equals("az")) System.out.println("KR_AZ "+(i+1)+" az");
				else if(nekoPolje[a].equals("od")) System.out.println("KR_OD "+(i+1)+" od");
				else if(nekoPolje[a].equals("do")) System.out.println("KR_DO "+(i+1)+" do");
				else if(isNumeric(nekoPolje[a])) System.out.println("BROJ "+(i+1)+" "+nekoPolje[a]);
				else System.out.println("IDN "+(i+1)+" "+nekoPolje[a]);
			}
			sb.delete(0, sb.length());	
		}		
	}
	
	public static boolean isNumeric(String str) {
		char[] field = str.toCharArray();
		for(int i = 0; i < field.length; i++) {
			if(!(Character.isDigit(field[i]))) return false;
		}
		return true;
	}
}

