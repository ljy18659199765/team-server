package com.wolf.framework.service.parameter;

import java.util.Map;

/**
 * 参数处理类
 *
 * @author aladdin
 */
public interface ParametersHandler {

    public FieldHandler getFieldHandler(final String fieldName);

    public boolean containsField(String fieldName);

    public Map<String, FieldHandler> getFieldHandlerMap();
}