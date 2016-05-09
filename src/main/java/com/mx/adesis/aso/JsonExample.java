//package com.mx.adesis.aso;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.swing.text.html.parser.Entity;
//
//import org.sparx.Attribute;
//import org.sparx.Collection;
//import org.sparx.Element;
//import org.sparx.Package;
//import org.sparx.Repository;
//
//public class JsonExample {
//	static List<Package> paquetesAso = new ArrayList<Package>();
//	static Repository r = null;
//	static List<String> namesEntitys = new ArrayList<String>();
//	public static void main(String[] args)  throws Exception{	
//		 r = new Repository();
//		 r.OpenFile("C:\\Users\\MXPCL15063\\Desktop\\design-template.eap");
//		 Collection<Element> elementos =  r.GetElementsByQuery("Simple", "ForeignCurrencyDetail");
//		 Element elemento = buscaEntidadInicial(elementos, "ForeignCurrencyDetail"); 
//		 String finalJSON = "{\""+ elemento.GetName() +"\":{"+getAttributesbyEntity(elemento)+"}";
//		 System.out.println(finalJSON);
//		 
//	}
//
//	private static String getAttributesbyEntity(Element elemento) {
//		String formatoJson = "";
//		namesEntitys.add(elemento.GetName());
//		Collection<Attribute> atributos =  elemento.GetAttributes();
//		System.out.println("** total de elementos :"+atributos.GetCount()+", nombre: "+elemento.GetName()+",tipo:"+elemento.GetType());
//		Iterator iatributos = atributos.iterator();
//		while(iatributos.hasNext()){
//			Attribute atributo = ((Attribute)iatributos.next());
//			if(getAttributeType(atributo.GetType(),atributo.GetStereotype()).equalsIgnoreCase("Subentidad")){
//				if (atributo.GetType().indexOf('<')==-1){
//					System.out.println("iterando entidad: "+atributo.GetName());
////					System.out.println("***"+atributo.GetAttributeGUID());
//					Collection<Element> subElementos =  r.GetElementsByQuery("Simple", atributo.GetType());
//					Element entidadBuscada =  buscaEntidadInicial(subElementos,atributo.GetType());
//					if(entidadBuscada != null){
//						System.out.println("entidad encontrada:"+entidadBuscada.GetName());
//						formatoJson+= getAttributesbyEntity(entidadBuscada);
//					}
//					
//				}
//				
//			}else{
//				formatoJson+=writeSimpleAttribute(atributo)+",";
//			}
//		}
//		if(formatoJson.length() > 0)
//			formatoJson= formatoJson.substring(0,formatoJson.length()-1);
//		formatoJson+=  "}";
//		return formatoJson;
//	}
//	
//	private static int buscaNombre(List<String> coleccionNombres, String nombre)
//	{
//		int bolean = 0;
//	    for(int i=0; i<coleccionNombres.size(); i++)
//	    {
//	     	if(coleccionNombres.get(i).equals(nombre)){
//	     		bolean = 1;
//	     		return bolean;
//	     	}
//	            
//	    }
//	    return bolean;
//	}
//
//	private static int buscaEntidad(Collection<Element> subElementos, String getType) {
//		int isEncontrado = 0;
//		Iterator isubElementos = subElementos.iterator();
//		while(isubElementos.hasNext()){
//			if(((Element ) isubElementos.next()).GetName().equals(getType)){
//				isEncontrado = 1;
//				return isEncontrado ;
//			}
//			
//		}
//		return isEncontrado ;
//	}
//	
//	private static Element buscaEntidadInicial(Collection<Element> subElementos, String getType) {
//		Element elemento = null;
//		Iterator isubElementos = subElementos.iterator();
//		while(isubElementos.hasNext()){
//			elemento = (Element ) isubElementos.next();
//			if(elemento.GetName().equals(getType)){
//				return elemento ;
//			}
//			
//		}
//		return elemento ;
//	}
//
//	private static String writeSimpleAttribute(Attribute atributo) {
//		String attributeJSON = "";
//		int index=0;
//		if(!atributo.GetType().equalsIgnoreCase("undefined")){
//			index =  atributo.GetType().indexOf("[");
//		}
//		
//		if(index > 0){
//			attributeJSON = "\""+atributo.GetName()+"\": "+getExampleTextByAttributeType(atributo.GetType(), atributo.GetStereotype())+"";
//		} else {
//			attributeJSON = "\""+atributo.GetName()+"\": "+getExampleTextByAttributeType(atributo.GetType(), atributo.GetStereotype())+"\"";
//		}
//		
//		return attributeJSON;
//	}
//
//	
//	private static String getAttributeType(String type, String stereotype )
//	{
//		String attType = "Subentidad";
//		if(type.equalsIgnoreCase("String") || type.equalsIgnoreCase("Boolean") 
//			|| type.equalsIgnoreCase("Date") || type.equalsIgnoreCase("BigDecimal")
//			|| type.equalsIgnoreCase("Integer") || type.equalsIgnoreCase("int") 
//			|| type.equalsIgnoreCase("Long" )){
//			attType = "Simple";
//		} 
////		else if (type == "String[]" || type == "enum" || type == "ENUM"){
////			attType = "Complejo";
////		}
//		else if (stereotype.equalsIgnoreCase("enumeration")) {
//			attType = "Complejo";
//		}
//		return attType;
//	}
//	
//	private static String getExampleTextByAttributeType(String type, String stereotype){
//		String exampleText= "";
//		
//		switch (type) {
//			case "string":
//			case "String":
//				exampleText = "\"Sample text";
//				break;
//			case "Boolean":
//			case "boolean":	
//				exampleText = "\"true";
//				break;
//			case "Date":
//				exampleText = "\"100000989775";
//				break;
//			case "DateTime":
//				exampleText = "\"100000989775";
//				break;
//			case "BigDecimal":
//				exampleText = "\"10000.76";
//				break;
//			case "Integer":
//			case "int":	
//				exampleText = "\"108";
//				break;
//			case "Long":
//			case "long":	
//				exampleText = "\"109";
//				break;
//			case "String[]":
//				exampleText = "\"[\"value1\",\"value2\",\"value3\"]";
//				break;
//			case "enum":
//				exampleText = "\"ENUMvalue";
//				break;
//			case "ENUM":
//				exampleText = "\"ENUMvalue";
//				break;
//			default:
//				exampleText = "\"type not found";
//				break;
//		}
//		
//		switch (stereotype) {
//			case "enum":
//				exampleText = "\"ENUMvalue";
//				break;
//			case "ENUM":
//				exampleText = "\"ENUMvalue";
//				break;
//		}
//		
//		return exampleText;
//	}
//	
//	private static void getSubpaquete(Package paquete) {
//		Collection<Package> subPaquetines = paquete.GetPackages();
//		Iterator isubPaquetines = subPaquetines.iterator();
//		 while(isubPaquetines.hasNext()){
//			 Package subPaquete = (Package)isubPaquetines.next();
//			 if(subPaquete.GetName().equals("canonnicals")){
//				 break;
//			 }else{
//				 paquetesAso.add(subPaquete);
//				 getSubpaquete(subPaquete);
//			 }
//			 
//		 }
//	}
//
//	
//	
//
//}
