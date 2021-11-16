//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Connections {
//
//	public List<List<Route>> TSPPro(List<City> cityList)
//	{
//		TSPPro tspPro = new TSPPro(cityList);
//		List<City> shortestPath = tspPro.calculateShortestPath();
//		List<List<Route>> routeList2 = new ArrayList<>();
//		routeList2.add(getRouteList(shortestPath));
//		return routeList2;
//	}
//	
//	private List<Route> getRouteList(List<City> cityList)
//	{
//		List<Route> routeList = new ArrayList<>();
//		
//		int prev = 0;
//		
//		for(int i=1;i<cityList.size();i++)
//		{
//			Route route = new Route();
//			
//			route.setSrc(cityList.get(prev));
//			route.setDest(cityList.get(i));
//			route.setDist(TSPPro.getEuclideanDistance(cityList.get(prev), cityList.get(i)));
//			routeList.add(route);
//		}
//		
//		return routeList;
// 	}
//	
//	public List<List<Route>> TSPNearestNeighbour(List<City> cityList)
//	{
//		List<List<Route>> routeList2 = new ArrayList<>();
//		routeList2.add(solveTSPNN(cityList));
//		return routeList2;
//	}
//	
//	public List<Route> solveTSPNN(List<City> cityList)
//	{
//		int x1,x2,y1,y2;
//		float currentDistance = Float.MAX_VALUE;
//		City minCity = null;
//		float minDistance = Float.MAX_VALUE;
//		
//		x1 = cityList.get(0).getX();
//		y1 = cityList.get(0).getY();
//		int counter = 0;
//		
//		List<City> resultCityList = new ArrayList<>();
//		
//		while(cityList.size() > 0)
//		{
//				
//				for(City city: cityList)
//				{
//						x2 = (int)city.getX();
//						y2 = (int)city.getY();
//						currentDistance = (float)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
//						if(currentDistance < minDistance)
//						{
//							minDistance = currentDistance;
//							minCity = city;
//						}
//				}
//				
//				resultCityList.add(minCity);
//				
//				cityList.remove(minCity);
//				
//				x1 = (int)minCity.getX();
//				y1 = (int)minCity.getY();
//				
//				minDistance = Integer.MAX_VALUE;
//		}
//		
//		return getRouteList(resultCityList);
//	}
//	
//	public List<List<Route>> Clustering(List<City> cityList)
//	{
//		int maxX = cityList.get(0).getX();
//		int maxY = cityList.get(0).getY();
//		int minX = cityList.get(0).getX();
//		int minY = cityList.get(0).getY();
//		
//		for(int i=1;i<cityList.size();i++)
//		{
//			maxX = Math.max(maxX, cityList.get(i).getX());
//			maxY = Math.max(maxY, cityList.get(i).getY());
//			minX = Math.min(minX, cityList.get(i).getX());
//			minX = Math.min(minY, cityList.get(i).getY());
//		}
//		
//		List<List<Route>> routeList2 = new ArrayList<>();
//		
//		routeList2.add(solveTSPNN(getCluster(cityList, minX, (maxY+minY)/2, (maxX+minX)/2, maxY)));
//		routeList2.add(solveTSPNN(getCluster(cityList, (maxX+minX)/2, (maxY+minY)/2, maxX, maxY)));
//		routeList2.add(solveTSPNN(getCluster(cityList, minX, minY, (maxX+minX)/2, (maxY+minY)/2)));
//		routeList2.add(solveTSPNN(getCluster(cityList, (maxX+minX)/2, minY, maxX, (maxY+minY)/2)));
//		
//		return routeList2;
//	}
//	
//	private List<City> getCluster(List<City> cityList, int minX, int minY, int maxX, int maxY)
//	{
//		List<City> cluster = new ArrayList<>();
//		for(City city: cityList)
//		{
//			if(city.getX() <= maxX && city.getX() >= minX && city.getY() <= maxY && city.getY() >= minY)
//			{
//				cluster.add(city);
//			}
//		}
//		return cluster;
//	}
//}
