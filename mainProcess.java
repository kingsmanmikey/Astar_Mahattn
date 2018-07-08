package controller;

import java.util.ArrayList;

import Util.MahattnUtil;
import javafx.scene.paint.Color;
import star.starShape;

public class mainProcess extends Thread{
	private static int calculateNewF(starShape ss,starShape minFpoint) {
		int G = MahattnUtil.OneStepDistance(ss, minFpoint) + minFpoint.getStarNote().G;
		if(ss.getStarNote().H < 0 ) ss.getStarNote().H = MahattnUtil.MahattnDistance(ss, mainControl.desShape);
		return G + ss.getStarNote().H;
	}
	private static void findRoute(starShape s) {
		starShape now = s;
		int cnt = 0;
		while(now.getStarNote().getSup() != null) {
			if(cnt != 0)mainControl.colorUpdate(now, Color.ALICEBLUE);
			now = now.getStarNote().getSup().shape;
			
			cnt ++;
		}
	}

	
	public void run() {
		long begin = System.currentTimeMillis();
		mainControl.openList.add(mainControl.startPoint);

		while(!mainControl.openList.isEmpty()) {
			/**
			 * Find the note with the min F in the open list
			 */
			starShape minFpoint = null;
			for(starShape ss : mainControl.openList) {
				if(minFpoint == null) {
					minFpoint = ss;
					continue;
				}
				if(ss.getStarNote().getF()<minFpoint.getStarNote().getF()) {
					minFpoint = ss;
				}
			}
			/**
			 * Check if it finds the destination successfully
			 */
			if(minFpoint.getStarNote().getF()==0) break;
			/**
			 * check this note's neighbors
			 */
			ArrayList<starShape> minsNeighbors = minFpoint.getNeighbor();
			for(starShape ss : minsNeighbors) {
				int newF = calculateNewF(ss, minFpoint);
				if(mainControl.openList.contains(ss)) {
					/*
					 * if new F less than old F
					 * set this note's sup note as minFpoint
					 * refresh its F
					 */
					if(newF < ss.getStarNote().getF()) {
						ss.getStarNote().setSup(minFpoint.getStarNote());
						ss.getStarNote().G = MahattnUtil.OneStepDistance(ss, minFpoint) + minFpoint.getStarNote().G;
					}
				}else if(mainControl.closeList.contains(ss)) {
					continue;
				}else {
					/*
					 * set ss's sup as minFpoint
					 * calculate F
					 * put ss into openlist
					 */
					ss.getStarNote().setSup(minFpoint.getStarNote());
					ss.getStarNote().G = MahattnUtil.OneStepDistance(ss, minFpoint) + minFpoint.getStarNote().G;
					ss.getStarNote().H = MahattnUtil.MahattnDistance(ss, mainControl.desShape);
					mainControl.openList.add(ss);
				}	
			}
			mainControl.closeList.add(minFpoint);
			mainControl.openList.remove(minFpoint);
		}
		
		findRoute(mainControl.desShape);
		long end = System.currentTimeMillis();
		System.out.println(end - begin +"ms");
	}
}
