package Util;

import star.starNote;
import star.starShape;

public class MahattnUtil {
	public static int MahattnDistance(starShape s1, starShape s2) {
		return Math.abs(s1.getStarNote().getX()/5-s2.getStarNote().getX()/5)+Math.abs(s1.getStarNote().getY()/5-s2.getStarNote().getY()/5);
	}
	public static int MahattnDistance(starNote s1, starNote s2) {
		return Math.abs(s1.getX()/5-s2.getX()/5)+Math.abs(s1.getY()/5-s2.getY()/5);
	}
	public static int OneStepDistance(starShape s1, starShape s2) {
		return (int)Math.sqrt(
				(s1.getStarNote().getX()/5-s2.getStarNote().getX()/5)*
				(s1.getStarNote().getX()/5-s2.getStarNote().getX()/5)
				+
				(s1.getStarNote().getY()/5-s2.getStarNote().getY()/5)*
				(s1.getStarNote().getY()/5-s2.getStarNote().getY()/5)
				);
	}
	public static int OneStepDistance(starNote s1, starNote s2) {
		return (int)Math.sqrt(
				(s1.getX()/5-s2.getX()/5)*
				(s1.getX()/5-s2.getX()/5)
				+
				(s1.getY()/5-s2.getY()/5)*
				(s1.getY()/5-s2.getY()/5)
				);
	}

}
