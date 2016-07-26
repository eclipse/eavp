package org.eclipse.eavp.viz.service.color;

public class ColorProvider {

	private static final int[] RED = {255,0,0};
	private static final int[] GREEN = {0,255,0};
	private static final int[] BLUE = {0,0,255};
	private static final int[] CYAN = {32,174,255};
	private static final int[] WHITE = {255,255,255};
	private static final int[] GREY = {100,100,100};
	private static final int[] PURPLE = {131,0,157};
	private static final int[] MAROON = {128,0,0};
	private static final int[] MAGENTA = {255,0,255};
	private static final int[] NAVY = {0,0,128};
	private static final int[] SKYBLUE = {135,206,250};
	private static final int[] SPRINGGREEN = {0,255,127};
	private static final int[] YELLOW = {255,255,0};
	private static final int[] OLIVEOIL = {142,142,56};
	private static final int[] TEAL = {56,142,142};
	private static final int[] SALMON = {198,113,113};
	private static final int[] ORANGE = {255,140,0};
	private static final int[] CHARTREUSE = {127,255,0};
	private static final int[] VIOLET = {255,62,150};

	private static final int[][] COLORS = {
			GREEN, VIOLET, BLUE, ORANGE, CHARTREUSE, SALMON, GREY, CYAN, 
			OLIVEOIL, MAROON, PURPLE, WHITE, SKYBLUE, SPRINGGREEN, TEAL, 
			YELLOW, NAVY, MAGENTA
	};
	
	private static int count = 0;
	
	public static int[] getNextColor() {
		if (count >= COLORS.length) {
			count = 0;
		}
		int[] nextColor = COLORS[count];
		count++;
		return nextColor;
	}
	
	
	public static int[] getHighlightedColor() {
		return RED;
	}
}
