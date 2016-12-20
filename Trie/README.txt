Trie

This program reads a text file from standard input and outputs the ten most frequent words in the file.

How to use:

	Compile the program in the command line with:
		java Trie.java
	
	Run the program and pipe in a text file with:
		java Trie < textFile.txt


Known bugs:
The program recognizes the ‘enter’ character as a unique character, so the enter character is listed as one of the top ten most frequent words.