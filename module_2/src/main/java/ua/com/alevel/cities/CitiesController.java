package ua.com.alevel.cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CitiesController {
    public static final String pathInput = "module_2/src/main/java/ua/com/alevel/input/inputCities.txt";
    private static List<List<Integer>> passed = new ArrayList<>();

    public static void run() {

        BufferedReader reader;
        List<City> cities = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(pathInput));
            int numOfCities = 0;
            if (reader.ready()) {
                numOfCities = Integer.parseInt(reader.readLine());
                if (numOfCities > 1000) {
                    System.out.println("Too big number of cities");
                    System.exit(0);
                }
            } else {
                System.out.println("Wrong input");
                System.exit(0);
            }
            for (int k = 1; k <= numOfCities; k++) {
                City city = new City();
                city.setId(k);
                city.setName(reader.readLine());
                int numOfNeighbors = Integer.parseInt(reader.readLine());
                city.setNumOfNeighbors(numOfNeighbors);
                for (int i = 0; i < numOfNeighbors; i++) {
                    getMap(reader, city);
                }
                city.setNeighborsIds(getList(city));
                cities.add(city);
            }
            int numOfPaths = Integer.parseInt(reader.readLine());
            for (int l = 0; l < numOfPaths; l++) {
                String[] destination = new String[2];
                int indexOfStartEnd = 0, indexOfNameCityEnd = 0;
                if (reader.ready()) {
                    String str = reader.readLine();
                    for (int i = 0; i < str.length(); i++) {
                        if (Character.isLetter(str.charAt(i))) {
                            destination[indexOfStartEnd] = String.valueOf(str.charAt(i));
                            for (int j = i + 1; j < str.length(); j++) {
                                if (Character.isLetter(str.charAt(j))) {
                                    destination[indexOfStartEnd] += str.charAt(j);
                                    indexOfNameCityEnd++;
                                } else {
                                    break;
                                }
                            }
                            indexOfStartEnd++;
                            i += indexOfNameCityEnd;
                        }
                        indexOfNameCityEnd = 0;
                    }
                }

                List<Integer> passedIds = new ArrayList<>();
                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).getName().equals(destination[0])) {
                        findingPath(cities, cities.get(i), passedIds, destination[1]);
                        break;
                    }
                }

                Map<List<Integer>, Integer> mapPathPrice = new HashMap<>();
                for (List<Integer> integers : passed) {
                    mapPathPrice.put(new ArrayList<>(integers), getPrice(cities, integers));
                }
                List<Integer> values = new ArrayList<>(mapPathPrice.values());
                for (int i = 0; i < values.size(); i++) {
                    for (int j = 0; j < values.size(); j++) {
                        if (values.get(i) > values.get(j)) {
                            values.remove(i);
                            i--;
                            break;
                        }
                    }
                }

                if (values.get(0) <= 200_000) {
                    for (int i = 0; i < mapPathPrice.size(); i++) {
                        if (Objects.equals(mapPathPrice.get(passed.get(i)), values.get(0))) {
                            System.out.print("The cheapest way : ");
                            for (int j = 0; j < passed.get(i).size(); j++) {
                                System.out.print(findById(cities, passed.get(i).get(j)).getName());
                                if (j != passed.get(i).size() - 1) {
                                    System.out.print(" -> ");
                                }
                            }
                            System.out.println("; value = " + values.get(0));
                        }
                    }
                } else {
                    System.out.println("All paths are very expensive, cost more than 200.000");
                }
                passed = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Wrong input");
        }
    }

    public static List<Integer> getList(City city) {
        return city.getNeighbors().keySet().stream().toList();
    }

    public static void findingPath(List<City> cities, City city, List<Integer> passedIds, String finish) {
        List<Integer> neighbors = new ArrayList<>(city.getNeighborsIds());
        for (int j = 0; j < neighbors.size(); j++) {
            for (Integer passedId : passedIds) {
                if (Objects.equals(neighbors.get(j), passedId)) {
                    neighbors.remove(j);
                    j--;
                    break;
                }
            }
        }
        passedIds.add(city.getId());
        if (city.getName().equals(finish)) {
            passed.add(new ArrayList<>(passedIds));
            return;
        }
        for (Integer neighbor : neighbors) {
            findingPath(cities, findById(cities, neighbor), passedIds, finish);
            passedIds.remove(passedIds.size() - 1);
        }
    }

    public static City findById(List<City> cities, int id) {
        for (City city : cities) {
            if (city.getId() == id) {
                return city;
            }
        }
        return new City();
    }

    public static void getMap(BufferedReader bufferedReader, City city) {
        try {
            int indexOfNumberEnd = 0;
            String[] mapField = new String[2];
            int index = 0;
            String str = bufferedReader.readLine();
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i))) {
                    mapField[index] = String.valueOf(str.charAt(i));
                    for (int j = i + 1; j < str.length(); j++) {
                        if (Character.isDigit(str.charAt(j))) {
                            mapField[index] += str.charAt(j);
                            indexOfNumberEnd++;
                        } else {
                            break;
                        }
                    }
                    index++;
                    i += indexOfNumberEnd;
                }
                indexOfNumberEnd = 0;
            }
            city.addMap(Integer.parseInt(mapField[0]), Integer.parseInt(mapField[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPrice(List<City> cities, List<Integer> path) {
        int priceOfPath = 0;
        for (int i = 0; i < path.size(); i++) {
            City city = findById(cities, path.get(i));
            if (i != path.size() - 1) {
                priceOfPath += city.getNeighbors().get(path.get(i + 1));
            }
        }
        return priceOfPath;
    }
}
