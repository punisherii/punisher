import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class translator_spanish {

	public static char character; // caracter temporal usado para extraer los
									// caracteres de las palabras
	public static String temporal = ""; // string tempral usado para agrupar los
										// caracteres de las palabras contenidas
										// en el dicionario
	public static String word = ""; // string temporal usado para contener todos
									// los caracteres que contiene una palabra
									// del diccionario
	public static int posstring = 0; // entero usado para indicar la posicion de
										// cada palabra del diccionario en los
										// arreglos de cadenas para las palabras
										// en ingles y espan~ol

	public static char lastc; // character que indica el ultimo caracter de la
								// ultima palabra para extraer las palabras de
								// la frase a traducir

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String spanish = "el perro come hueso trabajar empleo hola queso debian Nicaragua ultimo ultimo"
				+ " debian perro hola queso ";

		String translation = ""; // variable que contendra las palabras
									// traducidas

		String[] englishwords; // arreglo de cadenas para las palabras en ingles
		String[] spanishwords; // arreglo de cadenas para las palabras en
								// epan~ol

		String[] phrasewords; // arreglo que contiene las palabras a traducir

		int num = 0; // numero que indica la cantidad de palabras contenidas en
						// la frase a traducir

		num = countwords(num, spanish); // funcion que calcula el numero de
										// palabras que posee la frase a
										// traducir

		phrasewords = new String[num]; // funcio que extrae todas las palabras
										// que contiene la frase en cadenas de
										// carateres de tipo string

		int wordsdictionary = 0; // entero que contiene la cantidad de palabras
									// que posee el diccionario

		wordsdictionary = dictionarywords(wordsdictionary); // funcion para
															// extraer la
															// cantidad de
															// lineas(palabras),
															// que posee el
															// diccionario

		englishwords = new String[wordsdictionary]; // inicializacion del
													// taman~o de los arreglos
													// que contendran las
													// palabras del diccionario
		spanishwords = new String[wordsdictionary];

		findDictionarywords(englishwords, spanishwords); // funcion que busca y
															// extrae las
															// palabras
															// contenidas en el
															// diccionario

		putwordsphrase(phrasewords, spanish); // funcion que extrae todas las
												// palabras de la frase a
												// traducir y la coloca en el
												// arreglo phrasewords

		/*
		 * impresiones usadas para la verificacion del contenido de los arreglos
		 * que contienen palabras
		 * 
		 * System.out.print(num + "\n" + wordsDictionary) ;
		 * System.out.print("\n" + englishwords[englishwords.length - 1] + "\t"
		 * + spanishwords[englishwords.length - 1] + "\n");
		 * System.out.print("\n" + englishwords[5] + "\t" + spanishwords[5]);
		 */
		translatewords(phrasewords, englishwords, spanishwords, translation); // funcion
																				// para
																				// traducir
																				// las
																				// palabras
																				// introducidas
																				// en
																				// la
																				// frase
																				// en
																				// espan~ol
																				// al
																				// ingles

		// System.out.print("\n" + "Traducion:" + translation);
	}

	/** void main **/

	public static int countwords(int c, String phrase) {
		/** countWords **/
		char cph;

		lastc = phrase.charAt(phrase.length() - 1);

		for (int cont = 0; cont < phrase.length(); cont++) {
			/** for **/
			cph = phrase.charAt(cont);
			if (cph == ' ') {
				c += 1;
			}

		}
		/** for **/

		if (lastc != ' ') {
			c += 1;
		}

		return c;

	}

	/** countWords **/

	public static int dictionarywords(int sumLines) {

		String fileLines = "";

		try {
			File fichero = new File("src/dictionary_es");
			FileReader leerfichero = new FileReader(fichero);
			BufferedReader leerbuffer = new BufferedReader(leerfichero);

			fileLines = leerbuffer.readLine();

			while (fileLines != null) {
				sumLines += 1;
				fileLines = leerbuffer.readLine();
			}

		} catch (Exception e) {
			System.out.print("\nno funciona 1");
		}

		return sumLines;
	}

	/** **/

	/**
	 * funcion usada para extraer las palabras de la frase en espan~ol y
	 * colocarla en el arreglo de tipo string
	 * 
	 * @param phrasewords
	 * @param phrase
	 */
	public static void putwordsphrase(String[] phrasewords, String phrase) {
		/** putWordsPhrase **/

		char characterphrase;
		String tmp = "";
		String words = "";
		int num = 0;

		for (int c = 0; c < phrase.length(); c++) {
			/** for **/
			characterphrase = phrase.charAt(c);
			if (characterphrase != ' ') {
				/** if **/
				tmp = "" + characterphrase;
				words += tmp;
				tmp = "";
			}/** if **/
			else {
				/** else **/
				words += tmp;
				phrasewords[num] = words;
				System.out.print(phrasewords[num] + " ");
				words = "";
				num += 1;
			}
			/** else **/

		}
		/** for **/
		/**
		 * si el ultimo caracter es diferente de un espacio en blanco, indica
		 * que es una palabra y es introducida en el arreglo
		 */

		char ultimatechar = phrase.charAt(phrase.length() - 1);
		if (ultimatechar != ' ') {
			phrasewords[num] = words;
			System.out.print(phrasewords[num] + " ");
		}

	}

	/** putWordsPhrase **/

	/** putDictionaryWords **/

	/**
	 * funcion utilizada para llamar a dos funciones encargadas de extraer las
	 * palabras en ingles y espan~ol de cada linea leida del diccionario
	 * 
	 * @param englishwords
	 * @param spanishwords
	 * @return
	 */

	public static String findDictionarywords(String[] englishwords,
			String[] spanishwords) {

		String translation = "";
		String linefile = "";
		int pos;
		try {
			File f = new File("src/dictionary_es");
			FileReader rf = new FileReader(f);
			BufferedReader br = new BufferedReader(rf);

			linefile = br.readLine();
			posstring = 0;

			while (linefile != null) {
				/** while **/

				extractwordsenglish(englishwords, linefile, posstring);
				extractwordspanish(spanishwords, linefile, posstring);
				linefile = br.readLine();
				posstring += 1;

			}
			/** while **/

		} catch (Exception e) {
			System.out.print("\nno funciona 2" + "\n");
		}

		return translation;

	}

	/** putDictionaryWords **/
	/**
	 * funcion extrae las palabras en ingles del diccionario ej: hueso bone
	 * 
	 * @param english
	 * @param line
	 * @param posstring
	 */

	public static void extractwordsenglish(String[] english, String line,
			int posstring) {
		temporal = "";
		word = "";

		for (int cont = 0; cont < line.length(); cont++) {
			/** for **/
			character = line.charAt(cont);

			if (character != ' ') {
				/** if **/
				// temporal = "" + character;
				temporal = String.valueOf(character);
				word += temporal;
			}/** if **/

			else {
				/** else **/
				english[posstring] = word;
				word = "";

			}
			/** else **/

		}
		/** for **/
	}

	/** extractwordsenglish **/

	/** extractwordspanish **/

	/**
	 * funcion que extrae las palabras del diccionario en espan~ol ej: el the
	 * 
	 * @param spanish
	 * @param line
	 * @param posstring
	 */
	public static void extractwordspanish(String[] spanish, String line,
			int posstring) {

		temporal = "";
		word = "";

		for (int cc = line.length() - 1; cc > 0; cc--) {

			character = line.charAt(cc);

			if (character != ' ') {
				// temporal = "" + character;
				temporal = String.valueOf(character);
				word += temporal;
				temporal = "";

			}

			else {
				/**
				 * funcion encontrada en internet que invertia los caracteres de
				 * una palabra
				 * 
				 * 
				 * for (int x=sCadena.length()-1;x>=0;x--) sCadenaInvertida =
				 * sCadenaInvertida + sCadena.charAt(x);
				 * 
				 * temporal = ""; int lon = word.length()-1; for(int pos = lon;
				 * pos >= 0; pos--){ temporal = temporal + word.charAt(pos);
				 * 
				 * }
				 */

				/**
				 * funcion que ordena los caracteres de derecha a izquierda que
				 * durante la extraccion del diccionario quedaron al reves
				 * 
				 * ej: cheese -> eseehc
				 */
				for (int pos = word.length() - 1; pos >= 0; pos--) {
					temporal += word.charAt(pos);
				}

				// spanish[posstring] = word;
				spanish[posstring] = temporal;
				word = "";

			}

		}
		/** for **/

		/** extractwordsspanish **/
	}

	/**
	 * funcion que traduce las palabras en espan~ol a su equivalente
	 * correspondiente en ingles segun la posicion de la palabra encontrada en
	 * espan~ol
	 * 
	 * @param phraseswords
	 * @param spanish
	 * @param english
	 * @param translation
	 */

	public static void translatewords(String[] phraseswords, String[] spanish,
			String[] english, String translation) {

		String palabra;
		String comparar;

		/**
		 * 
		 * funciones para verificar si las palabras extraidas en el diccionario
		 * son correctas
		 * 
		 * for (int contpalabra = 0; contpalabra < phraseswords.length;
		 * contpalabra++) { for (int cont = 0; cont < spanish.length; cont++) {
		 * if (phraseswords[contpalabra].equals(spanish[cont])) { translation +=
		 * english[cont] + " ";
		 * 
		 * 
		 * for (int cont = 0; cont < english.length; cont++) {
		 * System.out.print(spanish[cont] + " ");
		 * 
		 * 
		 * } System.out.print("\n");
		 * 
		 * for (int cont = 0; cont < english.length; cont++) {
		 * System.out.print(english[cont] + " ");
		 * 
		 * 
		 * } System.out.print("\n");
		 * 
		 * for (int cont = 0; cont < phraseswords.length; cont++) {
		 * System.out.print(phraseswords[cont] + " "); }
		 */

		for (int contpalabras = 0; contpalabras < phraseswords.length; contpalabras++) {
			for (int cont = 0; cont < spanish.length; cont++) {
				if (phraseswords[contpalabras].equals(spanish[cont])) {
					translation += english[cont] + " ";
				}

			}
		}

		System.out.print("\nTraduccion:\n" + translation);

	}
	/** translateWords **/

}
