package dao;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import dto.MapNode;
import entity.Light;

/**
 * @filename MapInfo.java
 * @author Reece
 * @description  读取xml文件中保存的地图信息
 * @date 2015年10月15日下午11:06:52
 * @version 1.0
 */
public class MapData implements DataInterface{
	
	public Element mapReader(){
		SAXReader reader = new SAXReader();
		try {
			//得到document对象
			Document doc = reader.read("data/mapInfo.xml");
			//得到根节点
			Element root = doc.getRootElement();
			return root;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public List<MapNode> loadMap() {
		List<MapNode> MapNodes= new ArrayList<MapNode>();
		//得到mapInfo节点
		Element mapInfo = mapReader().element("mapNode");
		//得到子节点
		@SuppressWarnings("unchecked")
		List<Element> mapNode = mapInfo.elements("node"); 
		for(Element node : mapNode){
			//生成节点列表
			MapNode tree = new MapNode();
			tree.setPoint(new Point(Integer.parseInt(node.attributeValue("x")),
					Integer.parseInt(node.attributeValue("y"))));
			tree.setFaceChild(Integer.parseInt(node.attributeValue("faceChild")));
			tree.setRightChild(Integer.parseInt(node.attributeValue("rightChild")));
			tree.setType(Integer.parseInt(node.attributeValue("type")));
			tree.setCode(Integer.parseInt(node.attributeValue("code")));
			MapNodes.add(tree);
		}
		return MapNodes;
	}
	public List<Light> loadLight(){
		List<Light> lightList = new ArrayList<Light>();
		
		Element partInfo = mapReader().element("mapLight");
		@SuppressWarnings("unchecked")
		List<Element> parts = partInfo.elements("part");
		
		for(Element part:parts){
			Element lightEW = part.element("lightW");
			Element lightEH = part.element("lightH");
			Light light = new Light();
			light.setNodeCodeHA(Integer.parseInt(lightEH.attributeValue("nodeA")));
			light.setNodeCodeHB(Integer.parseInt(lightEH.attributeValue("nodeB")));
			light.setNodeCodeWA(Integer.parseInt(lightEW.attributeValue("nodeA")));
			light.setNodeCodeWB(Integer.parseInt(lightEW.attributeValue("nodeB")));
			lightList.add(light);
		}
		return lightList;
	}
}
