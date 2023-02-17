package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.RenderObject;

public class RenderController {
	
	private static List<RenderObject> renderObjects = new ArrayList<>();
	private static int numberOfRenderObjects;
	
	public RenderController() {
		
	
	}
	
	public void addRenderObjectToList(RenderObject r) {
		if(!renderObjects.contains(r)) {
			renderObjects.add(r);
			numberOfRenderObjects++;
		}
	}
	
	public void addListOfRenderObjectsToList(List<RenderObject> rs) {
		for(RenderObject r : rs) {
			if(!renderObjects.contains(r)) {
				renderObjects.add(r);
				numberOfRenderObjects++;
			}
		}
	}
	
	public List<RenderObject> getRenderObjects() {
		return renderObjects;
	}
	
	public int getNumberOfRenderObjects() {
		return numberOfRenderObjects;
	}
	
	
	
	

}
