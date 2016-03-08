package lex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Syntax {
	Stack<Integer> state =new Stack<Integer>();
	Stack<String> mark = new Stack<String>();
	int point = 0;
	String[] list;
	Map<Key,Integer> map = new HashMap<Key,Integer>();
	
	public void setMap(){
		map.put(new Key(0,"IF"), 2);//s2
		map.put(new Key(0,"INT"), 3);//s3
		map.put(new Key(0,"ID"), 4);//s4
		map.put(new Key(0,"S"), 1);//s1
		map.put(new Key(1,"IF"), 2);//s2
		map.put(new Key(1,"INT"), 3);//s3
		map.put(new Key(1,"ID"), 4);//s4
		map.put(new Key(1,"$"), 0);//end,action
		map.put(new Key(1,"S"), 26);//26
		map.put(new Key(2,"("), 5);//s5
		map.put(new Key(3,"ID"), 6);//s6
		map.put(new Key(4,"="), 7);//s7
		map.put(new Key(5,"NUM"), 9);//s14
		map.put(new Key(5,"ID"), 10);//s15
		map.put(new Key(5,"B"), 8);//s8
		map.put(new Key(6,"="), 13);//13
		map.put(new Key(7,"NUM"), 14);//14
		map.put(new Key(7,"ID"), 15);//s15
		map.put(new Key(7,"E"), 16);//s16
		map.put(new Key(8,")"),22);//s22
		map.put(new Key(8,">"), 11);//s11
		map.put(new Key(8,"<"), 11);//s11
		map.put(new Key(9,")"), 36);//r6
		map.put(new Key(9,">"), 36);//r6
		map.put(new Key(9,"<"), 36);//r6
		map.put(new Key(10,")"), 37);
		map.put(new Key(10,">"), 37);//r7
		map.put(new Key(10,"<"), 37);//r7
		map.put(new Key(11,"NUM"), 9);//s9
		map.put(new Key(11,"ID"), 10);//s10
		map.put(new Key(11,"B"), 12);//s22
		map.put(new Key(12,")"), 35);//r5
		map.put(new Key(12,">"), 35);//
		map.put(new Key(12,"<"), 35);
		map.put(new Key(13,"NUM"), 14);
		map.put(new Key(13,"ID"), 15);
		map.put(new Key(13,"E"), 17);
		map.put(new Key(14,"+"), 40);
		map.put(new Key(14,"-"), 40);
		map.put(new Key(14,"*"), 40);
		map.put(new Key(14,"/"), 40);
		map.put(new Key(14,";"), 40);
		map.put(new Key(15,"+"), 41);
		map.put(new Key(15,"-"), 41);
		map.put(new Key(15,"*"), 41);
		map.put(new Key(15,"/"), 41);
		map.put(new Key(15,";"), 41);
		map.put(new Key(16,"+"), 18);
		map.put(new Key(16,"-"), 18);
		map.put(new Key(16,"*"), 19);
		map.put(new Key(16,"/"), 19);
		map.put(new Key(16,";"), 28);
		map.put(new Key(17,"+"), 18);
		map.put(new Key(17,"-"), 18);
		map.put(new Key(17,"*"), 19);
		map.put(new Key(17,"/"), 19);
		map.put(new Key(17,";"), 27);
		map.put(new Key(18,"NUM"), 14);
		map.put(new Key(18,"ID"), 15);
		map.put(new Key(18,"E"), 20);
		map.put(new Key(19,"NUM"), 14);
		map.put(new Key(19,"ID"), 15);
		map.put(new Key(19,"E"), 21);
		map.put(new Key(20,"+"), 38);
		map.put(new Key(20,"-"), 38);
		map.put(new Key(20,"*"), 19);
		map.put(new Key(20,"/"), 19);
		map.put(new Key(20,";"), 38);
		map.put(new Key(21,"+"), 39);
		map.put(new Key(21,"-"), 39);
		map.put(new Key(21,"*"), 39);
		map.put(new Key(21,"/"), 39);
		map.put(new Key(21,";"), 39);
		map.put(new Key(22,"{"), 23);
		map.put(new Key(23,"IF"), 2);
		map.put(new Key(23,"INT"), 3);
		map.put(new Key(23,"ID"), 4);
		map.put(new Key(23,"S"), 24);
		map.put(new Key(24,"IF"), 2);
		map.put(new Key(24,"INT"), 3);
		map.put(new Key(24,"ID"), 4);
		map.put(new Key(24,"}"), 25);
		map.put(new Key(24,"S"), 26);
		map.put(new Key(25,"IF"), 31);
		map.put(new Key(25,"INT"), 31);
		map.put(new Key(25,"ID"), 31);
		map.put(new Key(25,"}"), 31);
		map.put(new Key(25,"$"), 31);
		map.put(new Key(26,"IF"), 34);
		map.put(new Key(26,"INT"), 34);
		map.put(new Key(26,"ID"), 34);
		map.put(new Key(26,"}"), 34);
		map.put(new Key(26,"$"), 34);
		map.put(new Key(26,"S"), 26);
		map.put(new Key(27,"IF"), 32);
		map.put(new Key(27,"INT"), 32);
		map.put(new Key(27,"ID"), 32);
		map.put(new Key(27,"}"), 32);
		map.put(new Key(27,"$"), 32);
		map.put(new Key(28,"IF"), 33);
		map.put(new Key(28,"INT"), 33);
		map.put(new Key(28,"ID"), 33);
		map.put(new Key(28,"}"), 33);
		map.put(new Key(28,"$"), 33);
	}
	
	public void dealString(StringBuilder a){
		a.append("$");
		
		list= a.toString().split("\\s+");
	}
	
	public void analysis(StringBuilder lexlist) {
		dealString(lexlist);
		setMap();
		mark.push("$");
		state.push(0);
		while((list.length>point)&&(!mark.empty())&&(state.peek()!=null)){
			String temp=list[point];
			int s1= state.peek();
			action(s1,temp);		
		}
		
	}
	
	
	private void action(int s1, String temp) {
		int x = map.get(new Key(s1,temp));
		switch(x){
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
			//状态跳转
			state.push(x);
			mark.push(temp);
			point++;
			show();
			System.out.println("GOTO:s"+x);
			System.out.println("**********");
			break;
		case 31:
			//r1
			StringBuilder tem1 = new StringBuilder() ;
			for(int i=0;i<7;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			int y = state.peek();
			Integer goTo = map.get(new Key(y,"S"));
			state.push(goTo);
			mark.push("S");
			show();
			System.out.println("规约:S->IF (B){S}");
			System.out.println("**********");
			break;
		case 32:
			tem1 = new StringBuilder() ;
			for(int i=0;i<5;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"S"));
			state.push(goTo);
			mark.push("S");
			show();
			System.out.println("规约:S->INT ID =E");
			System.out.println("**********");
			break;
		case 33:
			tem1 = new StringBuilder() ;
			for(int i=0;i<4;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"S"));
			state.push(goTo);
			mark.push("S");
			show();
			System.out.println("规约:S->ID=E");
			System.out.println("**********");
			break;
		case 34:
			tem1 = new StringBuilder() ;
			for(int i=0;i<2;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"S"));
			state.push(goTo);
			mark.push("S");
			show();
			System.out.println("规约:S->SS");
			System.out.println("**********");
			break;
		case 35:
			tem1 = new StringBuilder() ;
			for(int i=0;i<3;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"B"));
			state.push(goTo);
			mark.push("B");
			show();
			System.out.println("规约:B>B 或者B<B");
			System.out.println("**********");
			break;
		case 36:
			tem1 = new StringBuilder() ;
			for(int i=0;i<1;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"B"));
			state.push(goTo);
			mark.push("B");
			show();
			System.out.println("规约:B->NUM");
			System.out.println("**********");
			break;
		case 37:
			tem1 = new StringBuilder() ;
			for(int i=0;i<1;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"B"));
			state.push(goTo);
			mark.push("B");
			show();
			System.out.println("规约:B->ID");
			System.out.println("**********");
			break;
		case 38:
			tem1 = new StringBuilder() ;
			for(int i=0;i<3;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"E"));
			state.push(goTo);
			mark.push("E");
			show();
			System.out.println("规约:E->E+E 或者E->E-E");
			System.out.println("**********");
			break;
		case 39:
			tem1 = new StringBuilder() ;
			for(int i=0;i<3;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"E"));
			state.push(goTo);
			mark.push("E");
			show();
			System.out.println("规约:E->E*E 或者E->E/E");
			System.out.println("**********");
			break;
		case 40:
			tem1 = new StringBuilder() ;
			for(int i=0;i<1;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"E"));
			state.push(goTo);
			mark.push("E");
			show();
			System.out.println("规约:E->NUM");
			System.out.println("**********");
			break;
		case 41:
			tem1 = new StringBuilder() ;
			for(int i=0;i<1;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"E"));
			state.push(goTo);
			mark.push("E");
			show();
			System.out.println("规约:E->ID");
			System.out.println("**********");
			break;
		case 0:
			tem1 = new StringBuilder() ;
			for(int i=0;i<1;i++){
				tem1.append(mark.pop());
				state.pop();
				tem1.append(" ");
			}
			//根据状态跳转
			y = state.peek();
			goTo = map.get(new Key(y,"S'"));
			state.push(goTo);
			mark.push("S'");
			System.out.println("**********");
			System.out.println("accept");
			System.out.println("规约：S'->S");
			System.out.println("**********");
			break;
			
		}
		
		
	}
	
	public void show(){
		System.out.println("************");
		System.out.print("栈：");
		for(int x1:state){
			System.out.print(x1);
			System.out.print(" ");
		}
		System.out.println();
		System.out.print("符号：");
		for(String x1:mark){
			System.out.print(x1);
			System.out.print(" ");
		}
		System.out.println();
		System.out.print("指针:");
		for(int i=point;i<list.length;i++){
			System.out.print(list[i]);
			System.out.print(" ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		Lex l=new Lex();
		Syntax s = new Syntax();
		File f=new File("program.txt");
		try{
			l.readfile(f);
		}catch(FileNotFoundException e){
			System.out.println("file not found");
		}
		try{
			l.analysis();
			s.analysis(l.lexlist);
			
		}catch(IOException e){
			
		}
		
	}

}
