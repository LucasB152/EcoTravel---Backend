package be.ecotravel.back.service;


import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoogleDestinationServiceTests {

    @Test
    void testStartAndEnd() {

        GoogleDestinationService destinationService = new GoogleDestinationService();

        String gareCoordinates = "50.62544217155316,5.56696585592688";
        String helmoCoordinates = "50.62271940445531,5.575098513599215";

        double distance = destinationService.fetchDistanceFromAPI(gareCoordinates, new ArrayList<>(), helmoCoordinates);
        Assert.isTrue(distance == 814);
    }

    @Test
    void testWithStepsBetween() {

        GoogleDestinationService destinationService = new GoogleDestinationService();

        String gareCoordinates = "50.62544217155316,5.56696585592688";
        String helmoCoordinates = "50.62271940445531,5.575098513599215";

        String mcDonaldsCoordinates = "50.64309844560166,5.571948005605735";
        String mediaCiteCoordinates = "50.63269306724099,5.5795080424649734";

        List<String> steps = new ArrayList<>();
        steps.add(mcDonaldsCoordinates);
        steps.add(mediaCiteCoordinates);

        double distance = destinationService.fetchDistanceFromAPI(gareCoordinates, steps, helmoCoordinates);

        Assert.isTrue(distance == 6909);
    }

}
