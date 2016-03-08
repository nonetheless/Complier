package lex;

public class Key {
	int state;
	String mark;
	
	public Key(int x, String y) {
        this.state = x;
        this.mark = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return (state == key.state && (mark.compareTo(key.mark)==0));
    }

    @Override
    public int hashCode() {
        int result = state;
        result = 31 * result + state*mark.charAt(0);
        return result;
    }

	

}
