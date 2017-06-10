/*
 * The purpose of this class is to create a more java, less boilerplate code
 * way to create, alter, delete, and otherwise process random access files
 * without importing and utilizing multiple classes. Most of the functionality
 * will resemble that of data structures that exist in RAM, with get/set methods
 * for the lines of the random access file, and a save method that will apply 
 * changes made to data structures to the random access files they are linked
 * with.
 */
package com.tsugame.helperClasses;

import java.io.*;
import java.nio.file.*;
import javax.swing.JOptionPane;
import java.util.HashMap;
import static java.nio.file.StandardOpenOption.*;

public class RAFProcessor 
{
	
	/*************
	 * Variables *
	 *************/
	
	HashMap<String, Path> mapPaths = new HashMap<String, Path>();
	HashMap<String, BufferedOutputStream> mapBOS = new HashMap<String, BufferedOutputStream>();//this should initialize a buffered output stream of every commonly used type
	
	
	/****************
	 * Constructors *
	 ****************/
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other

}
