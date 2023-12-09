package day_3;

import day_3.Coordinate.CoordinateDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Engine {
    private final String ANY_SYMBOLS = "([^\\d.])+";
    private final String GEARS = "(\\*)+";
    private final String ANY_NUMBER = "([\\d])+";

    List<String[]> schematic;

    public Engine(List<String> inputSchematic) {
        schematic = inputSchematic.stream()
                .map(s -> s.split(""))
                .toList();
    }

    public List<Coordinate> getPartLocations(PartType partType) {
        List<Coordinate> coordinates = new ArrayList<>();
        String regex = partType == PartType.ANY_PART ? ANY_SYMBOLS: GEARS;

        for (int i = 0; i < schematic.size(); i++) {
            for (int j = 0; j < schematic.get(i).length; j++) {
                if (anyMatch(regex, schematic.get(i)[j])){
                    coordinates.add(new Coordinate(i,j));
                }
            }
        }

        return coordinates;
    }

    public List<Integer> getAdjacentValues(Coordinate coordinate) {
        List<Integer> adjacentValues = new ArrayList<>();
        List<Coordinate> visitedCoordinates = new ArrayList<>();

        for (int y = coordinate.y()-1; y<=coordinate.y()+1; y++){
            for (int x = coordinate.x()-1; x<=coordinate.x()+1; x++){
                if (!visitedCoordinates.contains(new Coordinate(y, x))) {
                    var nlr = getNumberValue(new Coordinate(y, x));
                    visitedCoordinates.addAll(nlr.visitedCoordinates);
                    adjacentValues.add(nlr.result);
                }
            }
        }

        return adjacentValues;
    }

    private NumberLookupResult getNumberValue(Coordinate coordinate) {
        if(!anyMatch(ANY_NUMBER, schematic.get(coordinate.y())[coordinate.x()])) {
            return new NumberLookupResult(0, List.of());
        }

        List<Coordinate> visitedCoordinates = new ArrayList<>(Collections.singleton(coordinate));
        String number = schematic.get(coordinate.y())[coordinate.x()];

        Coordinate coordinateCursor = coordinate;

        while (hasNumberToDirection(coordinateCursor, CoordinateDirection.LEFT)) {
            coordinateCursor = coordinateCursor.moveLeft();
            visitedCoordinates.add(coordinateCursor);
            number = "%s%s".formatted(getValue(coordinateCursor).toString(), number);
        }
        coordinateCursor = coordinate;
        while (hasNumberToDirection(coordinateCursor, CoordinateDirection.RIGHT)) {
            coordinateCursor = coordinateCursor.moveRight();
            visitedCoordinates.add(coordinateCursor);
            number = "%s%s".formatted(number, getValue(coordinateCursor).toString());
        }

        return new NumberLookupResult(Integer.parseInt(number), visitedCoordinates);
    }

    private boolean hasNumberToDirection(Coordinate originalCoordinate, CoordinateDirection direction) {
        Coordinate coordinate = direction == CoordinateDirection.LEFT ? originalCoordinate.moveLeft(): originalCoordinate.moveRight();
        try {
            return anyMatch(ANY_NUMBER, schematic.get(coordinate.y())[coordinate.x()]);
        } catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    private Integer getValue(Coordinate coordinate) {
        try {
            return Integer.parseInt(schematic.get(coordinate.y())[coordinate.x()]);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    public static boolean anyMatch(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }

    record NumberLookupResult(int result, List<Coordinate> visitedCoordinates){}
}
