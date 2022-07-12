import java.util.ArrayList;

class Piece {
	protected String color;
	protected String name;
	protected PositionPair pos;
	protected boolean active = false;
	
	protected Piece (String c, String s, int v, int h) {
		color = c;
		name = s;
		pos = new PositionPair(v, h);
		active = true;
	}
	
	protected Piece (String c, String s, PositionPair pp) {
		color = c;
		name = s;
		pos = pp;
		active = true;
	}
	protected Piece () {
		
	}
	
	
	public String getName () {
		return name;
	}
	
	public String getColor () {
		return color;
	}
	
	public int getVPos () {
		return pos.getVPos();
	}
	
	public int getHPos () {
		return pos.getHPos();
	}
	
	public void setPos (PositionPair pp) {
		this.pos = pp;
	}
	
	public boolean activePiece () {
		return active;
	}
	
	public ArrayList<PositionPair> getMoves(Board board) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}//end of class
