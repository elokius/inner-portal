package com.vergl.filling.service;

import com.vergl.filling.model.WherePart;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 13.03.17
 */
public interface WherePartService {
    WherePart findByParameterName(String parameterName);
}
