package com.dden.todo.actuator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Operation
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */

@AllArgsConstructor
@Data
public class Operation {
    private String name;
    private boolean successful;
}
