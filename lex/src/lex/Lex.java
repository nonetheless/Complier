package lex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Lex {
	ArrayList<StringTokenizer> list=new ArrayList<StringTokenizer>();
	StringBuilder lexlist=new StringBuilder();
	//读取txt中的数据
	public void readfile(File file) throws FileNotFoundException{	
		Scanner scn=new Scanner(file);
		while(scn.hasNext()){
			String temp=scn.nextLine();
			StringTokenizer s=new StringTokenizer(temp);
			list.add(s);			
		}
		
	}
	public void analysis() throws IOException{
		for(StringTokenizer s:list){//每行为一次循环
			StringBuilder b=new StringBuilder();
			while(s.hasMoreTokens()){
				String string=s.nextToken();
				StringReader sr=new StringReader(string);
				StringBuilder record=new StringBuilder();//output
				boolean isnum=false;
				boolean isstr=false;
				int i=sr.read();
				while(i!=-1){
					Character c=(char)i;
					switch(c){
					case 'A':
					case 'B':
					case 'C':
					case 'D':
					case 'E':
					case 'F':
					case 'G':
					case 'H':	
					case 'I':
					case 'J':
					case 'K':
					case 'L':
					case 'M':
					case 'N':
					case 'O':
					case 'P':
					case 'Q':
					case 'R':
					case 'S':
					case 'T':
					case 'U':
					case 'V':						
					case 'W':
					case 'X':
					case 'Y':
					case 'Z':
					case 'a':
					case 'b':						
					case 'c':
					case 'd':						
					case 'e':						
					case 'f':						
					case 'g':
					case 'h':	
					case 'i':						
					case 'j':
					case 'k':
					case 'l':						
					case 'm':
					case 'n':						
					case 'o':						
					case 'p':
					case 'q':
					case 'r':
					case 's':						
					case 't':						
					case 'u':						
					case 'v':						
					case 'w':
					case 'x':
					case 'y':
					case 'z': 
						if(isstr){
							record.append(c);
						}
						else if(isnum){
							System.out.println("wrong id");
						}
						else{
							isstr=true;
							record.append(c);
						}
						break;
					case '(':
						
					case '{':
						if(record.length()==0){
							b.append(c+" ");
						}
						else{
							String temp2=record.toString();
							if(temp2.compareTo("if")==0){
								b.append("IF");
								b.append(" ");
							}
							else if(temp2.compareTo("else")==0){
								b.append("ELSE ");
							}
                            else if(temp2.compareTo("int")==0){
                            	b.append("INT ");
							}
                            else{
                            	if(isnum){
                            		b.append("NUM ");
                            	}
                            	else if(isstr){
                            		b.append("ID ");
                            	}
                            }
							b.append(c+" ");
							record.delete(0, record.length());
							isstr=false;
							isnum=false;
						}
						break;
					case ')':
					case '}':
						if(record.length()==0){
							b.append(c+" ");
						}
						else{
							String temp2=record.toString();
							if(temp2.compareTo("if")==0){
								b.append("IF");
								b.append(" ");
							}
							else if(temp2.compareTo("else")==0){
								b.append("ELSE ");
							}
                            else if(temp2.compareTo("int")==0){
                            	b.append("INT ");
							}
                            else{
                            	if(isnum){
                            		b.append("NUM ");
                            	}
                            	else if(isstr){
                            		b.append("ID ");
                            	}
                            }
							b.append(c+" ");
							record.delete(0, record.length());
							isstr=false;
							isnum=false;
						}
						break;
					case '=':
					case '>':
					case '<':
					case '!':
						if(record.length()==0){
							b.append(c+" ");
						}
						else{
							String temp2=record.toString();
							if(temp2.compareTo("if")==0){
								b.append("IF");
								b.append(" ");
							}
							else if(temp2.compareTo("else")==0){
								b.append("ELSE ");
							}
                            else if(temp2.compareTo("int")==0){
                            	b.append("INT ");
							}
                            else{
                            	if(isnum){
                            		b.append("NUM ");
                            	}
                            	else if(isstr){
                            		b.append("ID ");
                            	}
                            }
							b.append(c+" ");
							record.delete(0, record.length());
							isstr=false;
							isnum=false;
						}
						break;
					case '+':
					case '-':
					case '*':
					case '/':
						if(record.length()==0){
							b.append(c+" ");
						}
						else{
							String temp2=record.toString();
							if(temp2.compareTo("if")==0){
								b.append("IF");
								b.append(" ");
							}
							else if(temp2.compareTo("else")==0){
								b.append("ELSE ");
							}
                            else if(temp2.compareTo("int")==0){
                            	b.append("INT ");
							}
                            else{
                            	if(isnum){
                            		b.append("NUM ");
                            	}
                            	else if(isstr){
                            		b.append("ID ");
                            	}
                            }
							b.append(c+" ");
							record.delete(0, record.length());
							isstr=false;
							isnum=false;
						}
						break;
					case ';':
						if(record.length()==0){
							b.append("; ");
						}
						else{
							String temp2=record.toString();
							if(temp2.compareTo("if")==0){
								b.append("IF");
								b.append(" ");
							}
							else if(temp2.compareTo("else")==0){
								b.append("ELSE ");
							}
                            else if(temp2.compareTo("int")==0){
                            	b.append("INT ");
							}
                            else{
                            	if(isnum){
                            		b.append("NUM ");
                            	}
                            	else if(isstr){
                            		b.append("ID ");
                            	}
                            }
							b.append("; ");
							record.delete(0, record.length());
							isstr=false;
							isnum=false;
						}
						break;
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						if(isnum){
							record.append(c);
						}
						else if(isstr){
							record.append(c);
						}
						else{
							isnum=true;
							record.append(c);
						}
						break;
					default:
						System.out.println("unrecognized character");
	
					}		
					i=sr.read();
				}
				
		        if(record.length()!=0){
		        	String temp3=record.toString();
					if(temp3.compareTo("if")==0){
						b.append("IF");
						b.append(" ");
					}
					else if(temp3.compareTo("else")==0){
						b.append("ELSE ");
					}
	                else if(temp3.compareTo("int")==0){
	                	b.append("INT ");
					}
	                else{
	                	if(isnum){
	                		b.append("NUM ");
	                	}
	                	else if(isstr){
	                		b.append("ID ");
	                	}
	                }
					record.delete(0, record.length());
					isstr=false;
					isnum=false;
			    }
			}
			System.out.println(b.toString());
			lexlist.append(b.toString());
			lexlist.append(" ");
		}
	}
	public static void main(String[] args){
		Lex l=new Lex();
		File f=new File("program.txt");
		try{
			l.readfile(f);
		}catch(FileNotFoundException e){
			System.out.println("file not found");
		}
		try{
			l.analysis();
			
		}catch(IOException e){
			
		}
		
	}

}
