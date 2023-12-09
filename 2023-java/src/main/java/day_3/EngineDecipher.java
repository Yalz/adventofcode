package day_3;

import java.util.List;

import static day_3.PartType.ANY_PART;
import static day_3.PartType.GEAR;

public class EngineDecipher {
    public static int getPartCount(Engine engine) {
        return engine.getPartLocations(ANY_PART).stream()
                .map(engine::getAdjacentValues)
                .flatMap(List::stream)
                .reduce(0, Integer::sum);
    }

    public static int getGearProduct(Engine engine) {
        return engine.getPartLocations(GEAR).stream()
                .map(engine::getAdjacentValues)
                .map(adjacentValues -> adjacentValues.stream().filter(val -> val > 0).toList())
                .filter(list -> list.size() == 2)
                .map(adjacentValues -> adjacentValues.stream().reduce(1, (i1, i2) -> i1 * i2))
                .reduce(0, Integer::sum);
    }
}
