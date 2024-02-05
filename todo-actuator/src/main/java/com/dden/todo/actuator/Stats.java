package com.dden.todo.actuator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Stats
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class Stats {
    private long count;
    private long completed;
}
