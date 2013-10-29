import java.io.*;
public class translator_spanish {

	public static int position = 0 ;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String spanish = "el perro come hueso " ;
		
		String[] englishWords ;
		String[] spanishWords ;
		
	
		String[] phrasewords ;
		
		int num = 0 ;
		
		num = countWords(num, spanish) ;
		
	
		phrasewords = new String[num] ;
		
		int wordsDictionary = 0 ;
		
		wordsDictionary = dictionaryWords(wordsDictionary);

		englishWords = new String[wordsDictionary];
		spanishWords = new String[wordsDictionary];
		
		findDictionaryWords(englishWords, spanishWords);
		
		
		
		putWordsPhrase(phrasewords, spanish);
		
		//System.out.print(num + "\n" + wordsDictionary) ; 
		String english = "" ;
		
		english = translateWords(phrasewords,englishWords, spanishWords, english);
		
		/** System.out.print("\n" + englishWords[3] + "\t" + spanishWords[3]) ; **/
		
		System.out.print("\nTraducion:\n" + english);
		
		
		
	}	/** void main **/
	
	public static int countWords(int c, String phrase){	/** countWords **/
		char cph ;
		char lastc ;
		String word = "" ;
		String tmp = "" ;
		
		lastc = phrase.charAt(phrase.length()-1) ;
		
			for(int cont = 0 ; cont < phrase.length()  ; cont++){	/** for **/
				cph = phrase.charAt(cont);
				if(cph == ' '){
					c += 1 ;
				}
				
				
			}	/** for **/
			
			if( lastc != ' ' ){
				c +=1 ;
			}		
			
			
			return c;
	
	}	/** countWords **/
	
	public static int dictionaryWords(int sumLines){
		
		String fileLines = "" ;
		
		try{
			File fichero = new File("src/dictionary_es");
			FileReader leerfichero = new FileReader(fichero);
			BufferedReader leerbuffer = new BufferedReader(leerfichero);
			
			fileLines = leerbuffer.readLine();
			
			while( fileLines != null){
				sumLines += 1 ;
				fileLines = leerbuffer.readLine();
			}
			
		}catch(Exception e){}
		
		
		return sumLines;		
	} /** **/
	
	
	public static void putWordsPhrase(String[] phrasewords, String phrase){	/** putWordsPhrase **/
		
		char characterphrase ;
		String tmp = ""  ;
		String words = "" ;
		int num = 0 ;
		
		for(int c = 0 ; c < phrase.length() ; c++){	/** for **/
			characterphrase = phrase.charAt(c) ;
				if( characterphrase != ' '){	/** if **/
					tmp = "" + characterphrase ;
					words += tmp ;
					tmp = " ";
				}	/** if **/
				else{	/** else **/
					words += tmp ;
					phrasewords[num] = words ;
					System.out.print(phrasewords[num] + " ");
					words = "" ;
					num += 1 ;
				}	/** else **/
			
		}	/** for **/
		
		char ultimatechar = phrase.charAt(phrase.length()-1) ;
			if( ultimatechar != ' '){
				phrasewords[num] = words ;
				System.out.print(phrasewords[num] + " ");
			}
		
		
	}	/** putWordsPhrase **/
	

	public static String findDictionaryWords(String[] englishWords, String[] spanishWords){	/** putDictionaryWords **/
		String translation = "" ;
		String linefile = ""; 
		int pos ;
		try{
			File f = new File("src/dictionary_es");
			FileReader rf = new FileReader(f);
			BufferedReader  br = new BufferedReader(rf);
			
			linefile =  br.readLine();
			pos = 0 ;	
			
				while(linefile != null){	/** while **/
					
					extractwords(englishWords, spanishWords, linefile, pos);
					pos += 1 ;
					linefile =  br.readLine() ;
					
				}	/** while **/
			
			
		}catch(IOException e){ }
		
		
		return translation;
		
	}	/** putDictionaryWords **/
	
	public static void extractwords(String[] array, String[] arraytwo, String line, int posstring){
		char character ;
		String temporal = " " ;
		String word = " " ;
		
			for(int cont = 0 ; cont < line.length() ; cont++ ){	/** for **/
				character = line.charAt(cont);
					
					if( character != ' '){	/** if **/
						temporal = "" + character ;
						word += temporal ;
						temporal = " " ;
					}	/** if **/
					
					else{	/** else **/
						array[posstring] = word; 
						word = "" ;
					}	/** else **/
				
			}	/** for **/
			
			
			char lastcharacter = line.charAt(line.length()-1);
			
				if(lastcharacter != ' '){
					arraytwo[posstring] = word ;
				}
			
			
			
		
	} /** extractWords **/
	
	public static String translateWords(String[] words, String[] spanishwords, String[] englishwords, String translation){
		String tmpword = "" ;
		for(int pos = 0 ; pos < words.length ; pos++){	/**  translateWords **/
			
			tmpword = words[pos] ;
			
			for( int cont = 0 ; cont < spanishwords.length ; cont++){	/** for**/
				tmpword = spanishwords[pos] ;
					
					if(tmpword == spanishwords[cont]){	/** if **/
						translation += englishwords[cont] + " " ;
						
					}	/** if **/
					else{
						tmpword = "" ;
					}
					
			}	/** for **/
			
		}	/** for **/
		return translation ;
		
	}	/** translateWords **/
	

	
}	/** translator_spanish **/
