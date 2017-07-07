package com.vergl.filling.service;

import com.vergl.filling.model.JoinPart;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 13.03.17
 */
public interface JoinPartService {
    JoinPart findByParameterName(String parameterName);
}
