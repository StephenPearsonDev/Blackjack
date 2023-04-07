package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.Renderable;

public class RenderController {
	
	private static List<Renderable> renderObjects = new ArrayList<>();
	private static int numberOfRenderObjects;
	
	public RenderController() {
		
	
	}
	
	public void addRenderObjectToList(Renderable r) {
		if(!renderObjects.contains(r)) {
			renderObjects.add(r);
			numberOfRenderObjects++;
		}
	}
	
	public void addListOfRenderObjectsToList(List<Renderable> rs) {
		for(Renderable r : rs) {
			if(!renderObjects.contains(r)) {
				renderObjects.add(r);
				numberOfRenderObjects++;
			}
		}
	}
	
	public List<Renderable> getRenderObjects() {
		return renderObjects;
	}
	
	public int getNumberOfRenderObjects() {
		return numberOfRenderObjects;
	}
	
	
	
	

}
