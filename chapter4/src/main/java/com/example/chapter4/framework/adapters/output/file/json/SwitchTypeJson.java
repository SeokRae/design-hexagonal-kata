package com.example.chapter4.framework.adapters.output.file.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public enum SwitchTypeJson {
    LAYER2,
    LAYER3
}
