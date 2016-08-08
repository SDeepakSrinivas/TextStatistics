import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.jfree.ui.RefineryUtilities;

public class MostFrequent {
	


	   
	public static void main(String[] args) {
		ArrayList<String> lines = new ArrayList<String>();
		
		String path1 = "C:\\Users\\desriram\\workspace\\MostFrequent\\src\\warandpeace.txt";
		printStats(path1);
		String path2 = "C:\\Users\\desriram\\workspace\\MostFrequent\\src\\youth.txt";
		printStats(path2);
	}

	private static void printStats(String path1) {
		p("Statistics for "+ path1+" :: \n");
		
		HashMap<String,Integer> freqWords = getFreqWords(path1);
		p("The 10most frequent words are " + freqWords.toString()+"\n");
		makePieChart(freqWords);
		
		
		int avgSentLengthLetters = avgSentenceLengthLetters(path1);
		p("The avg no of letters in a sentence is "+avgSentLengthLetters+"\n");
		
		int avgSentLengthWords = avgSentenceLengthWords(path1);
		p("The avg no of words in a sentence is " +avgSentLengthWords+"\n");
		
		int countPunc = countPunctuationDensity(path1);
		p("The number of punctuation marks in the text is "+countPunc+"\n");
		
		int countPara = countPara(path1);
		p("The number of words per paragraph is "+ countPara+"\n");
		
		makeBarGraph(avgSentLengthLetters,avgSentLengthWords,countPunc,countPara);
	
		p("\n");
		
	}

	private static void makeBarGraph(int avgSentLengthLetters, int avgSentLengthWords, int countPunc, int countPara) {
		
		 BarChart chart = new BarChart("Text Statistics","", avgSentLengthLetters, avgSentLengthWords, countPunc, countPara);
	      chart.pack( );        
	      RefineryUtilities.centerFrameOnScreen( chart );        
	      chart.setVisible( true ); 
	}

	private static void makePieChart(HashMap<String, Integer> freqWords) {
		PieChart demo = new PieChart("Most Frequent Words",freqWords);
		demo.setSize( 1000 , 1000 );    
	    RefineryUtilities.centerFrameOnScreen( demo );    
	    demo.setVisible(true);
		
	}

	public static int countPara(String path) {
		
		// Getting All the lines from the file
		ArrayList<String> lines = getLinesFromFile(path);
		return countParaUtil(lines);
				
	}
	
	public static int countParaUtil(ArrayList<String> listOfStrings) {
        String prev = "";
        int count  = 0;
        for (String str : listOfStrings) {
                if(str.isEmpty() && !prev.isEmpty()) {
                        count = count + 1;
                } 
                prev = str;
        }
        return count;
	}



	private static int countPunctuationDensity(String path) {
		
		String[] lineSep = {""};
		String[] delims = {};
		
		// Getting All the lines from the file
		ArrayList<String> lines = getLinesFromFile(path);
		
		// Create HashMap
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
		// Removing lines which are empty
		lines.removeAll(Arrays.asList(lineSep));
		for(int i = 0; i < lines.size(); i++) {
			lines.set(i,lines.get(i).toLowerCase());
			for(int j = 0; j < delims.length; j++)
				lines.set(i,lines.get(i).replace(delims[j],""));
		}
		return countPunctuationsUtil(lines);
	}

	private static int avgSentenceLengthLetters(String path) {
		
		String[] lineSep = {""};
		String[] delims = {":","-",",","'","\"","[","]"};
		
		// Getting All the lines from the file
		ArrayList<String> lines = getLinesFromFile(path);
		
		// Create HashMap
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
		// Removing lines which are empty
		lines.removeAll(Arrays.asList(lineSep));
		for(int i = 0; i < lines.size(); i++) {
			lines.set(i,lines.get(i).toLowerCase());
			for(int j = 0; j < delims.length; j++)
				lines.set(i,lines.get(i).replace(delims[j],""));
		}
		
		return avgSentenceLengthUtilLetters(lines);
	}
	
	public static int avgSentenceLengthUtilLetters(ArrayList<String> lines)
	{
		
		int sum = 0;
		int cnt = 0;
		for(int i=0;i<lines.size();i++) 
			for(int j=0;j<lines.get(i).length();j++)
				if((lines.get(i).charAt(j)!='.') && (lines.get(i).charAt(j)!='?'))
					sum++;
				else 
					cnt++;
		return sum/cnt;
	}
	
private static int avgSentenceLengthWords(String path) {
		
		String[] lineSep = {""};
		String[] delims = {":","-",",","'","\"","[","]"};
		
		// Getting All the lines from the file
		ArrayList<String> lines = getLinesFromFile(path);
		
		// Create HashMap
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
		// Removing lines which are empty
		lines.removeAll(Arrays.asList(lineSep));
		for(int i = 0; i < lines.size(); i++) {
			lines.set(i,lines.get(i).toLowerCase());
			for(int j = 0; j < delims.length; j++)
				lines.set(i,lines.get(i).replace(delims[j],""));
		}
		
		return avgSentenceLengthUtilWords(lines);
	}
	
	public static int avgSentenceLengthUtilWords(ArrayList<String> lines)
	{
		
		int sum = 0;
		int cnt = 0;
		for(int i=0;i<lines.size();i++) 
			for(int j=0;j<lines.get(i).length();j++)
				if((lines.get(i).charAt(j)!='.') && (lines.get(i).charAt(j)!='?'))
					sum++;
				else 
					cnt++;
		return getNoOfWords(lines)/cnt;
	}

	public static int countPunctuationsUtil(ArrayList<String> listOfStrings) {

        List<String> newList = new ArrayList<>();
        newList = (List<String>) listOfStrings.clone();
        int count  = 0;
        for (String str : newList) {
                str = str.replaceAll("[a-z0-9]", "");
                str = str.replaceAll(" ", "");
                count = count + str.length();
        }
        return count;
	}

	public static int getNoOfWords(ArrayList<String> listOfStrings) {

        List<String> newList = new ArrayList<>();
        newList = (List<String>) listOfStrings.clone();
        int count  = 0;
        for (String str : newList) {
                str = str.replaceAll("^[a-z0-9]", "");
                count = count + str.split(" ").length; 
        }
        return count;
	}
	
	
	
	private static HashMap<String,Integer> getFreqWords(String string) {
		
		String[] lineSep = {""};
		String[] delims = {":",".","-","!","?",",","'","\"","[","]"};
		
				// Getting All the lines from the file
				ArrayList<String> lines = getLinesFromFile("C:\\Users\\desriram\\workspace\\MostFrequent\\src\\warandpeace.txt");
				
				// Create HashMap
				HashMap<String,Integer> hash = new HashMap<String,Integer>();
				// Removing lines which are empty
				lines.removeAll(Arrays.asList(lineSep));
				for(int i = 0; i < lines.size(); i++) {
					lines.set(i,lines.get(i).toLowerCase());
					for(int j = 0; j < delims.length; j++)
						lines.set(i,lines.get(i).replace(delims[j],""));
				}
				
				return getWordFreqMap(lines);
	
	}

	private static HashMap<String,Integer> getWordFreqMap(ArrayList<String> lines) {
		HashMap<String,Integer> wordFreq = new HashMap<String,Integer>();
		for(int i = 0; i<lines.size(); i++) {
			String[] words = lines.get(i).split(" ");
			for(int j = 0; j<words.length; j++)
				if(!(words[j].equals("")))
					if(wordFreq.containsKey(words[j]))
						wordFreq.put(words[j],wordFreq.get(words[j])+1);
					else
						wordFreq.put(words[j], 1);
					
		}
		
		Map<Integer, String> map = sortByValues(wordFreq); 
		HashMap<String,Integer> freqWords = new HashMap<String,Integer>();
	      Set set2 = map.entrySet();
	      Iterator iterator2 = set2.iterator();
	      int cnt = 0;
	      while(iterator2.hasNext() && cnt<10) {
	           Map.Entry me2 = (Map.Entry)iterator2.next();
	           freqWords.put(me2.getKey()+"",(Integer)me2.getValue());
	           cnt++;
	      }
	      return freqWords;
	}

	private static HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return -1*(((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue()));
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }

	private static void p(Object o) {
		System.out.print(o);
		
	}

	private static ArrayList<String> getLinesFromFile(String path) {

		ArrayList<String> lines = new ArrayList<String>();
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(path);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        lines.add(line);
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    if (inputStream != null) {
		        try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		
		return lines;
	}
}
