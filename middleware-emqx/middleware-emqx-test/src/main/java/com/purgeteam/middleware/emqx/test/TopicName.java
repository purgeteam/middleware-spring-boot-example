package com.purgeteam.middleware.emqx.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author purgeyao
 * @since 1.0
 */
@Getter
@ToString
@AllArgsConstructor
public enum TopicName {
    /**
     * listenDefault
     */
    ROLL_CALL_DEFAULT(1, "listenDefault");

    private final Integer key;

    private final String value;
}
