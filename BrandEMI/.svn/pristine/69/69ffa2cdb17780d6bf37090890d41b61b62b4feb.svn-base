package com.awl.tch.brandemi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.awl.tch.bean.MenuObject;
import com.awl.tch.brandemi.model.ChildProduct;
import com.awl.tch.brandemi.model.MenuNode;
import com.awl.tch.brandemi.service.MenuServiceImpl;

public class MenuList{

	private static final Logger logger = LoggerFactory.getLogger(MenuList.class);

	private static ArrayList<Node> menuList = new ArrayList<Node>();

	private static class Node{
		private int previousId=-1;
		private int nextId=-1;
		private int currentId;
		private String displayName;
		private String headerName;

		public Node(int previousId, int nextId, int currentId, String displayName, String headerName) {
			super();
			this.previousId = previousId;
			this.nextId = nextId;
			this.currentId = currentId;
			this.displayName = displayName;
			this.headerName = headerName;
		}
		public Node(int currentId) {
			super();
			this.currentId = currentId;
		}
		public Node(int currentId, int nextId) {
			// TODO Auto-generated constructor stub
			this.currentId = currentId;
			this.nextId = nextId;
		}
		public int getCurrentId() {
			return currentId;
		}
		public String getDisplayName() {
			return displayName;
		}
		public String getHeaderName() {
			return headerName;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + currentId;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			boolean flag = true;
			if (currentId != other.currentId)
				flag = false;
			if(other.nextId!=-1 && nextId != other.nextId)
				flag = false;
			return flag;
		}
		@Override
		public String toString() {
			return "Node [" + previousId + "|" + currentId  + "|" + nextId
					+ "|" + displayName + "|" + headerName + "]";
		}


	}
	
	private static class ParentNode{
		public Integer parentId;
		public Integer schemeId; 
		public Integer manufacturerId;
		public Integer categoryId;
		public ParentNode(Integer parentId, Integer schemeId, Integer manufacturerId) {
			super();
			this.parentId = parentId;
			this.schemeId = schemeId;
			this.manufacturerId = manufacturerId;
		}
		
		public ParentNode(Integer parentId, Integer schemeId, Integer manufacturerId, Integer categoryId) {
			super();
			this.parentId = parentId;
			this.schemeId = schemeId;
			this.manufacturerId = manufacturerId;
			this.categoryId = categoryId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
			result = prime * result + ((manufacturerId == null) ? 0 : manufacturerId.hashCode());
			result = prime * result + ((schemeId == null) ? 0 : schemeId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ParentNode other = (ParentNode) obj;
			if (categoryId == null) {
				if (other.categoryId != null)
					return false;
			} else if (!categoryId.equals(other.categoryId))
				return false;
			if (manufacturerId == null) {
				if (other.manufacturerId != null)
					return false;
			} else if (!manufacturerId.equals(other.manufacturerId))
				return false;
			if (schemeId == null) {
				if (other.schemeId != null)
					return false;
			} else if (!schemeId.equals(other.schemeId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ParentNode [parentId=" + parentId + ", schemeId=" + schemeId + ", manufacturerId=" + manufacturerId
					+ ", categoryId=" + categoryId + "]";
		}

		
	}
	public static boolean add(int previousId, int nextId, int currentId, String displayName, String headerName)
	{
		Node n = new Node(previousId, nextId, currentId, displayName, headerName);
		return menuList.add(n);
	}
	public static void display()
	{
		for(Node n : menuList)
		{
			logger.debug(""+n);
		}
	}

	public static void getMenu(HashSet<ChildProduct> midProducts, List<MenuObject> menuObjectList, int nextId, LinkedList<Integer> tempQueue, int parentScreenId )
	{
		
		Stack<Node> tempStack  = new Stack<Node>();
		
		int parentIds[] = new int[tempQueue.size()];
		for(int i=0;i<parentIds.length;i++)
		{
			parentIds[i] = tempQueue.removeFirst();
		}
		List<ParentNode> parentNodes = new ArrayList<>();
		for(int parentId : parentIds)
		{
			MenuObject schemeObj = find(menuObjectList, parentId);
			MenuObject manufacturerObject =  find(menuObjectList, schemeObj.getCurrentTableCode());
			ParentNode pn = new ParentNode(parentId, schemeObj.getId() ,  manufacturerObject.getId());
			parentNodes.add(pn);
		}
		
		Iterator<ChildProduct> childProducts = midProducts.iterator();
		while(childProducts.hasNext())
		{
			ChildProduct childProduct = childProducts.next();
			Node tempNode = new Node(childProduct.getChildId());
			int indexOfTempNode = menuList.indexOf(tempNode);
			Node node = null;
			if(indexOfTempNode!=-1)
			{	
				node = menuList.get(indexOfTempNode);
				tempStack.push(node);
				while(node!=null && node.previousId != 0)
				{
					tempNode  = new Node(node.previousId,node.currentId);
					Node previousNode = menuList.get(menuList.indexOf(tempNode));
					tempStack.push(previousNode);
					node = previousNode;
				}
				int screenId = parentScreenId;
				MenuObject dupObject = null;
				while(tempStack.size() > 0)
				{
					Node n = tempStack.pop();
					int currentId = -2 ;
					if(!tempQueue.isEmpty())
						currentId = tempQueue.removeFirst();
					/*if(firstMenuCurrentId == 0)
						firstMenuCurrentId = parentId;*/
					MenuObject menuObj = new MenuObject();
					if(n.previousId == 0)
					{
						ParentNode parentNode = new ParentNode(null,childProduct.getSchemeId() ,childProduct.getManufacturerId());
						ParentNode actualNode = parentNodes.get(parentNodes.indexOf(parentNode));
						menuObj.setTableNumber(screenId);
						menuObj.setCurrentTableCode(actualNode.parentId);
					}
					else
					{
						menuObj.setTableNumber(++screenId);
						if(dupObject!=null)
							menuObj.setCurrentTableCode(dupObject.getNextTableCode());
						else
							menuObj.setCurrentTableCode(currentId);
					}
					menuObj.setId(n.getCurrentId());
					dupObject = getDuplicate(menuObjectList, menuObj.getId());
					if(dupObject==null)
					{
						nextId++;
						tempQueue.add(nextId);
					}
					menuObj.setDisplayName(n.getDisplayName());
					menuObj.setHeaderName(n.getHeaderName());
					
					menuObj.setNextTableCode(nextId);
					if(n.previousId == 0)
						menuObj.setCategoryId(childProduct.getCategoryId());
					
					if(dupObject==null)
						menuObjectList.add(menuObj);
					
				}
			}

		}
		
	}
	
	private static MenuObject find( List<MenuObject> menuObjectList , int id)
	{
		for(MenuObject menuObject : menuObjectList)
		{
			if(menuObject.getNextTableCode().equals(id))
				return menuObject;
		}
		return null;
		
	}
	
	public static MenuObject findManufacturer( List<MenuObject> menuObjectList , int id)
	{
		MenuObject obj = find(menuObjectList , id);
		if(obj!=null)
			obj = find(menuObjectList , obj.getCurrentTableCode());
		return obj;
		
	}
	
	private static MenuObject getDuplicate( List<MenuObject> menuObjectList , int id)
	{
		for(MenuObject menuObject : menuObjectList)
		{
			if(menuObject.getId().equals(id))
				return menuObject;
		}
		return null;
		
	}
	public static void getMenu(List<ChildProduct> midProducts, List<MenuObject> menuObjectList, int nextId, LinkedList<Integer> tempQueue,
			int parentScreenId, Map<Integer, List<MenuNode>> productHeirarchy) {
		// TODO Auto-generated method stub
		Stack<MenuNode> tempStack  = new Stack<MenuNode>();
		int parentIds[] = new int[tempQueue.size()];
		for(int i=0;i<parentIds.length;i++)
		{
			parentIds[i] = tempQueue.removeFirst();
		}
		tempQueue = null;
		List<ParentNode> parentNodes = new ArrayList<>();
		for(int parentId : parentIds)
		{
			MenuObject schemeObj = find(menuObjectList, parentId);
			MenuObject manufacturerObject =  find(menuObjectList, schemeObj.getCurrentTableCode());
			MenuObject categoryObject =  find(menuObjectList, manufacturerObject.getCurrentTableCode());
			ParentNode pn = new ParentNode(parentId, schemeObj.getId() ,  manufacturerObject.getId() , categoryObject.getId());
			parentNodes.add(pn);
		}
		Iterator<ChildProduct> childProducts = midProducts.iterator();
		HashMap<ParentNode, HashMap<MenuNode, MenuObject>>  parentMap = new HashMap<ParentNode, HashMap<MenuNode, MenuObject>>();
		while(childProducts.hasNext())
		{
			
			ChildProduct childProduct = childProducts.next();
			List<MenuNode> list = productHeirarchy.get(childProduct.getChildId());
			MenuNode tempNode = new MenuNode(-1, childProduct.getChildId(), null, null);
			int indexOfChild = list.indexOf(tempNode);
			MenuNode node = null;
			if(indexOfChild!=-1)
			{
				node = list.get(indexOfChild);
				tempStack.push(node);
				while(node!=null && node.getPreviousId() != 0)
				{
					tempNode  = new MenuNode(-1, node.getPreviousId(), null, null);
					MenuNode previousNode = list.get(list.indexOf(tempNode));
					tempStack.push(previousNode);
					node = previousNode;
					//logger.info("Parent:"+node);
				}
			}
			
			int screenId = parentScreenId;
			
			ParentNode parentNode  = new ParentNode(null,childProduct.getSchemeId() ,childProduct.getManufacturerId(),childProduct.getCategoryId());
			int indexOfParent = parentNodes.indexOf(parentNode);
			ParentNode actualNode = null ; 
			if(indexOfParent != -1)
			{
				actualNode = parentNodes.get(indexOfParent);
			}
			else
				continue;
			
			HashMap<MenuNode, MenuObject> menuObjectMap = null;
			MenuObject previousAlreadyExistObj = null;
			MenuObject previousObj = null;
			while(tempStack.size() > 0)
			{
				
				MenuNode n = tempStack.pop();
				
				menuObjectMap = parentMap.get(parentNode);
				if(menuObjectMap==null)
				{
					menuObjectMap = new HashMap<>();
					parentMap.put(parentNode, menuObjectMap);
				}
				else
				{
					//logger.debug("MenuObjectMap for the parent of this child is :"+menuObjectMap);
				}
				MenuObject menuObj = null;
				if(n.getPreviousId() == 0)
				{
					menuObj = menuObjectMap.get(n);
					if(menuObj==null)
					{
						menuObj = new MenuObject();
						menuObj.setTableNumber(screenId);
						menuObj.setCurrentTableCode(actualNode.parentId);
					}
					else
					{
						previousAlreadyExistObj = menuObj;
						continue;
					}
				}
				else
				{
					menuObj = menuObjectMap.get(n);
					if(menuObj==null)
					{
						menuObj = new MenuObject();
						if(previousObj!=null)
						{
							menuObj.setTableNumber(previousObj.getTableNumber()+1);
							menuObj.setCurrentTableCode(previousObj.getNextTableCode());
						}
						else
						{
							if(previousAlreadyExistObj!=null)
							{	
								menuObj.setTableNumber(previousAlreadyExistObj.getTableNumber()+1);
								menuObj.setCurrentTableCode(previousAlreadyExistObj.getNextTableCode());
							}
						}
					}
					else
					{
						previousAlreadyExistObj = menuObj;
						continue;
					}
				}
				
				nextId++;
				menuObj.setId(n.getCurrentId());
				menuObj.setDisplayName(n.getDisplayName());
				menuObj.setHeaderName(n.getHeaderName());
				
				menuObj.setNextTableCode(nextId);
				if(n.getPreviousId() == 0)
					menuObj.setCategoryId(childProduct.getCategoryId());
				
				menuObjectList.add(menuObj);
				menuObjectMap.put(n, menuObj);
				previousObj = menuObj;
				
			}
		}
		
		//logger.info("All products :"+parentMap);
	}
	
	

}

