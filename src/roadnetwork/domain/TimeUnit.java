/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author antonio
 */
public interface TimeUnit {

    /**
     * Map of time unit multiplier in seconds
     */
    static final Map<String, Integer> TIME_UNIT_IN_SECONDS = Arrays.stream(new Object[][]{
        //To add a new entry just use {"Time Unit Key", "Value to Multiply"}
        {"s", 1},
        {"m", 60},
        {"h", 3600},
    }).collect(Collectors.toMap(kv -> (String) kv[0], kv -> (Integer) kv[1]));
}
